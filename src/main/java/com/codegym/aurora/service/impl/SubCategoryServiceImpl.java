package com.codegym.aurora.service.impl;

import com.codegym.aurora.converter.SubCategoryConverter;
import com.codegym.aurora.entity.Product;
import com.codegym.aurora.entity.SubCategory;
import com.codegym.aurora.payload.request.SubCategoryRequestDTO;
import com.codegym.aurora.payload.request.SubCategoryRequestDtoForCreate;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.payload.response.SubCategoryResponseDTO;
import com.codegym.aurora.repository.ProductRepository;
import com.codegym.aurora.repository.SubCategoryRepository;
import com.codegym.aurora.service.ImageService;
import com.codegym.aurora.service.SubCategoryService;
import com.codegym.aurora.util.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final SubCategoryConverter subCategoryConverter;
    private final ProductRepository productRepository;
    private final ImageService imageService;

    private ResponseDTO createResponseDTO(HttpStatus status, String message, Object data) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(status);
        responseDTO.setMessage(message);
        responseDTO.setData(data);
        return responseDTO;
    }

    private void updateSubCategoryActivation(SubCategory subCategory, boolean activated) {
        List<Product> productList = subCategory.getProducts();
        subCategory.setActive(activated);

        if (!productList.isEmpty()) {
            productList.forEach(product -> {
                product.setActivated(activated);
            });
            productRepository.saveAll(productList);
        }

        subCategoryRepository.save(subCategory);
    }

    @Override
    public ResponseDTO findAll(Pageable pageable) {
        Page<SubCategory> subCategoryList = subCategoryRepository.findAllByIsDeleteIsFalse(pageable);
        List<SubCategoryResponseDTO> subCategoryResponseDTOList = subCategoryList.stream()
                .map(subCategoryConverter::convertEntityToSubCategoryResponeDto)
                .collect(Collectors.toList());

        Page<SubCategoryResponseDTO> responseDTOPage = new PageImpl<>(
                subCategoryResponseDTOList,
                subCategoryList.getPageable(),
                subCategoryList.getTotalElements());

        return createResponseDTO(HttpStatus.OK, Constant.GET_CATEGORIES_ACTIVE_SUCCESS, responseDTOPage);
    }

    @Override
    public ResponseDTO create(SubCategoryRequestDtoForCreate subCategoryRequestDtoForCreate) {
        SubCategory subCategory = subCategoryConverter.
                convertSubCategoryRequestDtoForCreateToEntity(subCategoryRequestDtoForCreate);
        String subCategoryName = subCategoryRequestDtoForCreate.getName().trim();
        if (isSubCategoryNameAlreadyExists(subCategoryName)) {
            return createResponseDTO(
                    HttpStatus.BAD_REQUEST,
                    "A sub category with the same name already exists",
                    null);
        }

//        Thumb file for subCategory
//        MultipartFile thumbFile = subCategoryRequestDtoForCreate.getThumbFile();
//        if (thumbFile != null){
//            try {
//                String thumb = imageService.save(thumbFile);
//            } catch (IOException e) {
//                return new ResponseDTO("Upload thumb error!",HttpStatus.SERVICE_UNAVAILABLE, "Upload thumb error!")
//            }
//        }

        subCategory.setIsDelete(false);
        subCategoryRepository.save(subCategory);

        return createResponseDTO(
                HttpStatus.CREATED,
                Constant.CREATE_SUCCESS,
                subCategoryConverter.convertEntityToSubCategoryResponeDto(subCategory));
    }

    @Override
    public ResponseDTO update(SubCategoryRequestDTO subCategoryRequestDTO) {
        SubCategory subCategory = subCategoryRepository.save(subCategoryConverter.
                convertSubCategoryRequestDtoToEntity(subCategoryRequestDTO));
        return createResponseDTO(
                HttpStatus.OK,
                Constant.UPDATE_SUCCESS,
                subCategoryConverter.convertEntityToSubCategoryResponeDto(subCategory));
    }

    @Override
    public ResponseDTO deleteById(Long subCategoryId) {
        SubCategory subCategory = subCategoryRepository.findSubCategoryByActivatedTrue(subCategoryId);
        if (subCategory == null) {
            return createResponseDTO(
                    HttpStatus.NOT_FOUND,
                    "No sub category found with ID = " + subCategoryId,
                    null);
        }
        List<Product> productList = subCategory.getProducts();
        subCategory.setActive(false);
        subCategory.setIsDelete(true);
        subCategoryRepository.save(subCategory);

        if (!productList.isEmpty()) {
            productList.forEach(product -> {
                product.setActivated(false);
                product.setDelete(true);
            });
            productRepository.saveAll(productList);
        }
        return createResponseDTO(HttpStatus.OK, Constant.DELETE_SUCCESS, null);
    }

    @Override
    public ResponseDTO findSubCategoryByIdDeleteFalse(Long subCategoryId) {
        SubCategory subCategory = subCategoryRepository.findSubCategoryByIdAndDeletedFalse(subCategoryId);
        return createResponseDTO(HttpStatus.OK,
                Constant.GET_SUB_CATEGORIE_BY_ID_SUCCESS,
                subCategoryConverter.convertEntityToSubCategoryResponeDto(subCategory));
    }

    private boolean isSubCategoryNameAlreadyExists(String name) {
        SubCategory existingSubCategory = subCategoryRepository.findSubCategoryByNameAndActivatedTrue(name);
        return existingSubCategory != null;
    }

}

