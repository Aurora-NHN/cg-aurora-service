package com.codegym.aurora.service.impl;

import com.codegym.aurora.converter.CategoryConverter;
import com.codegym.aurora.entity.Category;
import com.codegym.aurora.entity.Product;
import com.codegym.aurora.entity.SubCategory;
import com.codegym.aurora.payload.request.CategoryRequestDTO;
import com.codegym.aurora.payload.response.CategoryResponseDTO;
import com.codegym.aurora.payload.response.CategoryResponseDTOForAdmin;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.repository.CategoryRepository;
import com.codegym.aurora.repository.ProductRepository;
import com.codegym.aurora.repository.SubCategoryRepository;
import com.codegym.aurora.service.CategoryService;
import com.codegym.aurora.service.ImageService;
import com.codegym.aurora.util.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;
    private final ProductRepository productRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ImageService imageService;

    @Transactional
    @Override
    public List<CategoryResponseDTO> findListCategoryResponseDTO() {
        List<Category> categoryList = categoryRepository.findAllByIsDeleteIsFalseAndActiveIsTrue();
        List<CategoryResponseDTO> categoryResponseDTOList = categoryConverter.convertCategoryEntityToDTO(categoryList);
        return categoryResponseDTOList;
    }

    @Transactional
    @Override
    public ResponseDTO findAll() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Category> categories = categoryRepository.findAllByIsDeleteIsFalse();
        List<CategoryResponseDTOForAdmin> categoryResponseDTOForAdminList = categories.stream()
                .map(categoryConverter::convertEntityToCategoryResponseDTOForAdmin)
                .collect(Collectors.toList());
        responseDTO.setMessage(Constant.GET_CATEGORIES_ACTIVE_SUCCESS);
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setData(categoryResponseDTOForAdminList);
        return responseDTO;
    }

    @Transactional
    @Override
    public ResponseDTO create(CategoryRequestDTO categoryRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String categoryName = categoryRequestDTO.getName().trim();
        if (isCategoryNameAlreadyExists(categoryName)) {
            responseDTO.setMessage("A category with the same name already exists");
            responseDTO.setStatus(HttpStatus.BAD_REQUEST);
            return responseDTO;
        }

        Category category = categoryConverter.convertCategoryRequestToEntity(categoryRequestDTO);
        category.setActive(true);
        category.setIsDelete(false);
        String thumb = null;
        try {
            if (categoryRequestDTO.getThumbFile() != null) {
                thumb = imageService.save(categoryRequestDTO.getThumbFile());
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        category.setThumb(thumb);
        categoryRepository.save(category);
        responseDTO.setMessage(Constant.CREATE_SUCCESS);
        responseDTO.setStatus(HttpStatus.CREATED);

        CategoryResponseDTOForAdmin responseDTOForAdmin = categoryConverter.convertEntityToCategoryResponseDTOForAdmin(category);
        responseDTOForAdmin.setThumbUrl(imageService.getImageUrl(thumb));
        responseDTO.setData(responseDTOForAdmin);
        return responseDTO;
    }

    @Transactional
    @Override
    public ResponseDTO update(CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryRepository.findById(categoryRequestDTO.getId()).orElse(null);
        if (category == null) return new ResponseDTO("Update error!", HttpStatus.BAD_REQUEST, "Update error!");

        category.setActive(categoryRequestDTO.isActive());
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        categoryRepository.save(category);

        return new ResponseDTO(
                Constant.UPDATE_SUCCESS,
                HttpStatus.OK,
                categoryConverter.convertEntityToCategoryResponseDTOForAdmin(category));
    }


    @Override
    @Transactional
    public ResponseDTO deleteById(Long categoryId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null)
            return new ResponseDTO("Category not found!", HttpStatus.BAD_REQUEST, "Category not found!");

        List<SubCategory> subCategories = category.getSubCategoryList();

        subCategories.forEach(subCategory -> {
            subCategory.setIsDelete(true);
            subCategory.getProducts().forEach(product -> product.setDelete(true));
        });

        category.setIsDelete(true);
        categoryRepository.save(category);
        responseDTO.setMessage(Constant.DELETE_SUCCESS);
        responseDTO.setStatus(HttpStatus.OK);
        return responseDTO;
    }

    @Transactional
    @Override
    public ResponseDTO findCategoryByIdAndDeteteFalse(Long categoryId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Category category = categoryRepository.findCategoryByIdAndDeletedFalse(categoryId);
        responseDTO.setMessage(Constant.GET_CATEGORIE_BY_ID_SUCCESS);
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setData(categoryConverter.convertEntityToCategoryResponseDTOForAdmin(category));
        return responseDTO;
    }

    @Transactional
    public List<Product> getAllProductsBySubCategories(List<SubCategory> subCategories) {
        List<Product> allProducts = new ArrayList<>();
        for (SubCategory subCategory : subCategories) {
            List<Product> products = subCategory.getProducts();
            allProducts.addAll(products);
        }
        return allProducts;
    }


    private boolean isCategoryNameAlreadyExists(String name) {
        Category existingCategory = categoryRepository.findCategoryByName(name);
        return existingCategory != null;
    }
}
