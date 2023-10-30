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

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;
    private final ProductRepository productRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ImageService imageService;
    public List<CategoryResponseDTO> findListCategoryResponseDTO(){
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryResponseDTO> categoryResponseDTOList = categoryConverter.convertCategoryEntityToDTO(categoryList);
        return categoryResponseDTOList;
    }

    @Override
    public ResponseDTO findAll() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDTOForAdmin> categoryResponseDTOForAdminList = categories.stream()
                .map(categoryConverter::convertEntityToCategoryResponseDTOForAdmin)
                .collect(Collectors.toList());
        responseDTO.setMessage(Constant.GET_CATEGORIES_ACTIVE_SUCCESS);
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setData(categoryResponseDTOForAdminList);
        return responseDTO;
    }

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
        category.setDelete(false);
        String thumb = null;
        try {
            thumb = imageService.save(categoryRequestDTO.getThumb());
        }catch (IOException e){
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

    @Override
    public ResponseDTO update(CategoryRequestDTO categoryRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        Category category = categoryRepository.save(categoryConverter.convertCategoryRequestToEntity(categoryRequestDTO));
        responseDTO.setMessage(Constant.UPDATE_SUCCESS);
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setData(categoryConverter.convertEntityToCategoryResponseDTOForAdmin(category));
        return responseDTO;
    }

    @Override
    public ResponseDTO deleteById(Long categoryId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Category category = categoryRepository.findCategoryByActiveTrue(categoryId);
        List<SubCategory> subCategories = category.getSubCategoryList();
        List<Product> allProducts = getAllProductsBySubCategories(subCategories);

        category.setDelete(true);
        category.setActive(false);

        if (!subCategories.isEmpty()){
            subCategories.forEach(subCategory -> {
                subCategory.setDelete(true);
                subCategory.setActivated(false);
            });
            if (!allProducts.isEmpty()){
                allProducts.forEach(product -> {
                    product.setDelete(true);
                    product.setActivated(false);
                });
            }
        }
        subCategoryRepository.saveAll(subCategories);
        productRepository.saveAll(allProducts);
        categoryRepository.save(category);
        responseDTO.setMessage(Constant.DELETE_SUCCESS);
        responseDTO.setStatus(HttpStatus.OK);
        return responseDTO;
    }

    @Override
    public ResponseDTO activeById(Long categoryId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Category category = categoryRepository.findCategoryByActiveFalseAndDeletedFalse(categoryId);
        List<SubCategory> subCategories = category.getSubCategoryList();
        List<Product> allProducts = getAllProductsBySubCategories(subCategories);

        category.setActive(true);
        if (!subCategories.isEmpty()){
            subCategories.forEach(subCategory -> subCategory.setActivated(true));
            if (!allProducts.isEmpty()){
                allProducts.forEach(product -> product.setActivated(true));
            }
        }
        subCategoryRepository.saveAll(subCategories);
        productRepository.saveAll(allProducts);
        categoryRepository.save(category);
        responseDTO.setMessage(Constant.ACTIVE_SUCCESS);
        responseDTO.setStatus(HttpStatus.OK);
        return responseDTO;
    }

    @Override
    public ResponseDTO unactiveById(Long categoryId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Category category = categoryRepository.findCategoryByActiveTrueAndDeletedFalse(categoryId);
        List<SubCategory> subCategories = category.getSubCategoryList();
        List<Product> allProducts = getAllProductsBySubCategories(subCategories);
        category.setActive(false);
        if (!subCategories.isEmpty()){
            subCategories.forEach(subCategory -> subCategory.setActivated(false));
            if (!allProducts.isEmpty()){
                allProducts.forEach(product -> product.setActivated(false));
            }
        }
        subCategoryRepository.saveAll(subCategories);
        productRepository.saveAll(allProducts);
        categoryRepository.save(category);
        responseDTO.setMessage(Constant.UNACTIVE_SUCCESS);
        responseDTO.setStatus(HttpStatus.OK);
        return responseDTO;
    }

    @Override
    public ResponseDTO findCategoryByIdAndDeteteFalse(Long categoryId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Category category = categoryRepository.findCategoryByIdAndDeletedFalse(categoryId);
        responseDTO.setMessage(Constant.GET_CATEGORIE_BY_ID_SUCCESS);
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setData(categoryConverter.convertEntityToCategoryResponseDTOForAdmin(category));
        return responseDTO;
    }
    public  List<Product> getAllProductsBySubCategories(List<SubCategory> subCategories){
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
