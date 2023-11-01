package com.codegym.aurora.service.impl;


import com.codegym.aurora.converter.ProductConverter;
import com.codegym.aurora.entity.Product;
import com.codegym.aurora.entity.ProductImage;
import com.codegym.aurora.entity.SubCategory;
import com.codegym.aurora.payload.request.ProductCreateRequestDto;
import com.codegym.aurora.payload.request.ProductRequestDTO;
import com.codegym.aurora.payload.response.PageProductResponseDTO;
import com.codegym.aurora.payload.response.ProductCreateResponseDTO;
import com.codegym.aurora.payload.response.ProductInAdminResponseDTO;
import com.codegym.aurora.payload.response.ProductResponseDTO;
import com.codegym.aurora.repository.ProductRepository;
import com.codegym.aurora.repository.SubCategoryRepository;
import com.codegym.aurora.service.ImageService;
import com.codegym.aurora.service.ProductImageService;
import com.codegym.aurora.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final ProductConverter productConverter;

    private final ImageService imageService;

    private final ProductImageService productImageService;
    private final SubCategoryRepository subCategoryRepository;

    @Override
    public Page<PageProductResponseDTO> getProductsPage(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(),9,Sort.by("quantitySold").descending());
        Page<Product> productPage = productRepository.findAll(pageable);
        Page<PageProductResponseDTO> pageProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
        return pageProductResponseDTOS;
    }

    @Override
    public Page<PageProductResponseDTO> searchProductsByName(String keyWord, Pageable pageable, String sortOrder) {
        Page<PageProductResponseDTO> pageSearchProductResponseDTOS = null;
        if (sortOrder != null) {
            if (sortOrder.equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(pageable.getPageNumber(), 9, Sort.by("price").ascending());
                Page<Product> productPage = productRepository.findProductsByNameOrProducerContainingIgnoreCase(keyWord, pageable);
                pageSearchProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
            } else if (sortOrder.equalsIgnoreCase("desc")) {
                pageable = PageRequest.of(pageable.getPageNumber(), 9, Sort.by("price").descending());
                Page<Product> productPage = productRepository.findProductsByNameOrProducerContainingIgnoreCase(keyWord, pageable);
                pageSearchProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
            }
        } else {
            pageable = PageRequest.of(pageable.getPageNumber(),9,Sort.by("quantitySold").descending());
            Page<Product> productPage = productRepository.findProductsByNameOrProducerContainingIgnoreCase(keyWord, pageable);
            pageSearchProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
        }
        return pageSearchProductResponseDTOS;

    }

    @Override
    public Page<PageProductResponseDTO> findProductsBySubCategoryId(long subCategoryId, Pageable pageable,String sortOrder) {
        Page<PageProductResponseDTO> pageProductResponseDTOS = null;
        if (sortOrder != null) {
            if (sortOrder.equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(pageable.getPageNumber(), 9, Sort.by("price").ascending());
                Page<Product> productPage = productRepository.findProductsBySubCategoryId(subCategoryId, pageable);
                pageProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
            } else if (sortOrder.equalsIgnoreCase("desc")) {
                pageable = PageRequest.of(pageable.getPageNumber(), 9, Sort.by("price").descending());
                Page<Product> productPage = productRepository.findProductsBySubCategoryId(subCategoryId, pageable);
                pageProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
            }
        } else {
            pageable = PageRequest.of(pageable.getPageNumber(),9,Sort.by("quantitySold").descending());
            Page<Product> productPage = productRepository.findProductsBySubCategoryId(subCategoryId, pageable);
            pageProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
        }
        return pageProductResponseDTOS;
    }

    @Override
    public Page<PageProductResponseDTO> getProductsDTOSortedAscending(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 9, Sort.by("price").ascending());
        Page<Product> products = productRepository.findAll(pageable);
        Page<PageProductResponseDTO> pageProductResponseDTOS = productConverter.convertPageEntityToDtoPage(products);
        return pageProductResponseDTOS;
    }

    @Override
    public Page<PageProductResponseDTO> getProductsDTOSortedDescending(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 9, Sort.by("price").descending());
        Page<Product> products = productRepository.findAll(pageable);
        Page<PageProductResponseDTO> pageProductResponseDTOS = productConverter.convertPageEntityToDtoPage(products);
        return pageProductResponseDTOS;
    }

    @Override
    public boolean addProduct(ProductRequestDTO productRequestDTO) throws IOException {
        try {
            String productImageUlr = imageService.save(productRequestDTO.getImage());
            List<String> productUrlList = imageService.save(productRequestDTO.getProductImageList());
            Product product = new Product();
            List<ProductImage> productImageList = productImageService.saveListImage(productUrlList,product);
            product.setImageUrl(productImageUlr);
            product.setName(productRequestDTO.getName());
            product.setDescription(productRequestDTO.getDescription());
            product.setPrice(productRequestDTO.getPrice());
            product.setQuantity(productRequestDTO.getQuantity());
            product.setWeight(productRequestDTO.getWeight());
            product.setProductImageUrlList(productImageList);
            product.setAuthor(productRequestDTO.getAuthor());
            product.setInclude(productRequestDTO.getInclude());
            product.setProducer(productRequestDTO.getProducer());
            productRepository.save(product);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public ResponseEntity<Object> createProduct(ProductCreateRequestDto productRequestDTO) {

        List<MultipartFile> productImageFileList = productRequestDTO.getProductImageList();
        try{
            if (productImageFileList.isEmpty()){
                return new ResponseEntity<>("Product image is null!", HttpStatus.BAD_REQUEST);
            }

            SubCategory subCategory = subCategoryRepository
                    .findById(productRequestDTO.getSubCategoryId()).orElse(null);
            if (subCategory == null){
                return new ResponseEntity<>("Add product failed, beacause subCategory is null!", HttpStatus.BAD_REQUEST);

            }
            Product product = new Product();
            List<String> productUrlList = imageService.save(productRequestDTO.getProductImageList());
            String productImageUlr = imageService.save(productImageFileList.get(0));
            List<ProductImage> productImageList = productImageService.saveListImage(productUrlList,product);

            product.setName(productRequestDTO.getName());
            product.setPrice(productRequestDTO.getPrice());
            product.setWeight(productRequestDTO.getWeight());
            product.setQuantity(productRequestDTO.getQuantity());
            product.setDescription(productRequestDTO.getDescription());
            product.setImageUrl(productImageUlr);
            product.setAuthor(productRequestDTO.getAuthor());
            product.setInclude(productRequestDTO.getInclude());
            product.setProducer(productRequestDTO.getProducer());
            product.setCreateDay(LocalDate.now());
            product.setHeight(productRequestDTO.getHeight());
            product.setImageName(productImageUlr);
            product.setIsActivated(true);
            product.setIsDelete(false);
            product.setSubCategory(subCategory);
            product.setProductImageUrlList(productImageList);
            productRepository.save(product);

            ProductCreateResponseDTO productResponseDTO = productConverter
                    .convertEntityToCreateResponse(product);
            productResponseDTO.setProductImageUrlList(imageService.getImageUrls(productUrlList));
            productResponseDTO.setMainImageUrl(imageService.getImageUrl(productImageUlr));
            return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
        }catch (Exception e){

            e.printStackTrace();
            return new ResponseEntity<>("Create product failed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<Object> updateProduct(ProductCreateRequestDto productRequestDTO) {

        return null;
    }

    @Override
    public List<ProductResponseDTO> getOtherProducts() {
        List<Product> randomProducts = productRepository.findRandomProducts(4);
        List<ProductResponseDTO> productResponseDTOS = productConverter.convertProductListEntityToDTO(randomProducts);
        return productResponseDTOS;
    }

    @Override
    public Page<ProductInAdminResponseDTO> getProductsPageInAdmin(Pageable pageable) {
        Page<Product> productPage = productRepository.findAllByIsDeleteIsFalse(pageable);
        Page<ProductInAdminResponseDTO> productInAdminResponseDTOPage = productConverter.convert(productPage);
        return productInAdminResponseDTOPage;
    }

    @Override
    public ResponseEntity<Object> deleteByProductId(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null){
            return new ResponseEntity<>("Product not found!", HttpStatus.BAD_REQUEST);
        }

        //Chưa viết xong

        return null;
    }

}