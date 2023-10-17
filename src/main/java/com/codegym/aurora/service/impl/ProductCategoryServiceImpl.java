package com.codegym.aurora.service.impl;

import com.codegym.aurora.converter.CategoryConverter;
import com.codegym.aurora.entity.ProductCategory;
import com.codegym.aurora.entity.Product;
import com.codegym.aurora.entity.SubCategory;
import com.codegym.aurora.payload.request.CategoryRequestDTO;
import com.codegym.aurora.payload.response.CategoryResponseDTO;
import com.codegym.aurora.payload.response.CategoryResponseDTOForAdmin;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.repository.CategoryRepository;
import com.codegym.aurora.repository.ProductRepository;
import com.codegym.aurora.repository.SubCategoryRepository;
import com.codegym.aurora.service.ProductCategoryService;
import com.codegym.aurora.util.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;
    private final ProductRepository productRepository;
    private final SubCategoryRepository subCategoryRepository;
    public List<CategoryResponseDTO> findListCategoryResponseDTO(){
        List<ProductCategory> productCategoryList = categoryRepository.findAll();
        List<CategoryResponseDTO> categoryResponseDTOList = categoryConverter.convertCategoryEntityToDTO(productCategoryList);
        return categoryResponseDTOList;
    }

    @Override
    public ResponseDTO findByActiveTrue() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<ProductCategory> categories = categoryRepository.findAllByActivatedTrue().orElseThrow();
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
        ProductCategory productCategory = categoryConverter.convertCategoryRequestToEntity(categoryRequestDTO);
        productCategory.setActivated(true);
        productCategory.setDelete(false);
        categoryRepository.save(productCategory);
        responseDTO.setMessage(Constant.CREATE_SUCCESS);
        responseDTO.setStatus(HttpStatus.CREATED);
        responseDTO.setData(categoryConverter.convertEntityToCategoryResponseDTOForAdmin(productCategory));
        return responseDTO;
    }

    @Override
    public ResponseDTO update(CategoryRequestDTO categoryRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        ProductCategory productCategory = categoryRepository.save(categoryConverter.convertCategoryRequestToEntity(categoryRequestDTO));
        responseDTO.setMessage(Constant.UPDATE_SUCCESS);
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setData(categoryConverter.convertEntityToCategoryResponseDTOForAdmin(productCategory));
        return responseDTO;
    }

    @Override
    public ResponseDTO deleteById(Long categoryId) {
        ResponseDTO responseDTO = new ResponseDTO();
        ProductCategory productCategory = categoryRepository.findCategoryByActivatedTrue(categoryId);
        List<SubCategory> subCategories = productCategory.getSubCategoryList();
        List<Product> allProducts = getAllProductsBySubCategories(subCategories);

        productCategory.setDelete(true);
        productCategory.setActivated(false);

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
        categoryRepository.save(productCategory);
        responseDTO.setMessage(Constant.DELETE_SUCCESS);
        responseDTO.setStatus(HttpStatus.OK);
        return responseDTO;
    }

    @Override
    public ResponseDTO activeById(Long categoryId) {
        ResponseDTO responseDTO = new ResponseDTO();
        ProductCategory productCategory = categoryRepository.findCategoryByActivatedFalseAndDeletedFalse(categoryId);
        List<SubCategory> subCategories = productCategory.getSubCategoryList();
        List<Product> allProducts = getAllProductsBySubCategories(subCategories);

        productCategory.setActivated(true);
        if (!subCategories.isEmpty()){
            subCategories.forEach(subCategory -> subCategory.setActivated(true));
            if (!allProducts.isEmpty()){
                allProducts.forEach(product -> product.setActivated(true));
            }
        }
        subCategoryRepository.saveAll(subCategories);
        productRepository.saveAll(allProducts);
        categoryRepository.save(productCategory);
        responseDTO.setMessage(Constant.ACTIVE_SUCCESS);
        responseDTO.setStatus(HttpStatus.OK);
        return responseDTO;
    }

    @Override
    public ResponseDTO unactiveById(Long categoryId) {
        ResponseDTO responseDTO = new ResponseDTO();
        ProductCategory productCategory = categoryRepository.findCategoryByActivatedTrueAndDeletedFalse(categoryId);
        List<SubCategory> subCategories = productCategory.getSubCategoryList();
        List<Product> allProducts = getAllProductsBySubCategories(subCategories);
        productCategory.setActivated(false);
        if (!subCategories.isEmpty()){
            subCategories.forEach(subCategory -> subCategory.setActivated(false));
            if (!allProducts.isEmpty()){
                allProducts.forEach(product -> product.setActivated(false));
            }
        }
        subCategoryRepository.saveAll(subCategories);
        productRepository.saveAll(allProducts);
        categoryRepository.save(productCategory);
        responseDTO.setMessage(Constant.UNACTIVE_SUCCESS);
        responseDTO.setStatus(HttpStatus.OK);
        return responseDTO;
    }

    @Override
    public ResponseDTO findCategoryByIdAndDeteteFalse(Long categoryId) {
        ResponseDTO responseDTO = new ResponseDTO();
        ProductCategory productCategory = categoryRepository.findCategoryByIdAndDeletedFalse(categoryId);
        responseDTO.setMessage(Constant.GET_CATEGORIE_BY_ID_SUCCESS);
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setData(categoryConverter.convertEntityToCategoryResponseDTOForAdmin(productCategory));
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
        ProductCategory existingProductCategory = categoryRepository.findCategoryByName(name);
        return existingProductCategory != null;
    }
}
