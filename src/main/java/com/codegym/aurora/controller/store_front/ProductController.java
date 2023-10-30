package com.codegym.aurora.controller.store_front;


import com.codegym.aurora.entity.Product;
import com.codegym.aurora.payload.request.ProductRequestDTO;
import com.codegym.aurora.payload.response.PageProductResponseDTO;
import com.codegym.aurora.payload.response.ProductResponseDTO;
import com.codegym.aurora.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("")
    public ResponseEntity<Page<PageProductResponseDTO>> getPageProducts(@PageableDefault Pageable pageable) {
        Page<PageProductResponseDTO> pageProductResponseDTOS = productService.getProductsPage(pageable);
        return new ResponseEntity<>(pageProductResponseDTOS, HttpStatus.OK);

    }

    @GetMapping("/search")
    public ResponseEntity<Page<PageProductResponseDTO>> getPageSearchProducts(
            @RequestParam(name = "keyword") String keyword,
            @PageableDefault Pageable pageable,
            @RequestParam(name = "sortOrder",required = false) String sortOrder) {
        Page<PageProductResponseDTO> searchPageResponseDTOS = productService.searchProductsByName(keyword, pageable,sortOrder);
        return new ResponseEntity<>(searchPageResponseDTOS, HttpStatus.OK);

    }

    @GetMapping("/sort/ascending")
    public ResponseEntity<Page<PageProductResponseDTO>> getProductsSortedAscending(
            @PageableDefault Pageable pageable) {
        Page<PageProductResponseDTO> sortPageResponseDTOS = productService.getProductsDTOSortedAscending(pageable);
        return new ResponseEntity<>(sortPageResponseDTOS, HttpStatus.OK);

    }

    @GetMapping("/sort/descending")
    public ResponseEntity<Page<PageProductResponseDTO>> getProductsSortedDescending(@PageableDefault Pageable pageable) {
        Page<PageProductResponseDTO> sortPageResponseDTOS = productService.getProductsDTOSortedDescending(pageable);
        return new ResponseEntity<>(sortPageResponseDTOS, HttpStatus.OK);

    }

    @GetMapping("/sub-category")
    public ResponseEntity<Page<PageProductResponseDTO>> getPageProductsBySubCategory(
            @RequestParam(name = "id") long subCategoryId,
            @PageableDefault Pageable pageable,
            @RequestParam(name = "sortOrder",required = false) String sortOrder) {
        Page<PageProductResponseDTO> pageProductsResponseDTOS = productService.findProductsBySubCategoryId(subCategoryId, pageable,sortOrder);
        return new ResponseEntity<>(pageProductsResponseDTOS, HttpStatus.OK);

    }

    @PostMapping("/new-product")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDTO productRequestDTO) throws IOException {
        boolean status = productService.addProduct(productRequestDTO);
        if(status){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/other-product")
    public ResponseEntity<List<ProductResponseDTO>> getOtherProducts() {
        List<ProductResponseDTO> productResponseDTOS = productService.getOtherProducts();
        return new ResponseEntity<>(productResponseDTOS, HttpStatus.OK);

    }
}
