package com.codegym.aurora.controller.back_office;

import com.codegym.aurora.payload.request.ProductRequestDTO;
import com.codegym.aurora.payload.response.ProductInAdminResponseDTO;
import com.codegym.aurora.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class ProductControllerForAdmin {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductInAdminResponseDTO>> getProductsPage(
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            Pageable pageable){

        Page<ProductInAdminResponseDTO> productsPage= productService.getProductsPageInAdmin(size, pageable);
        return new ResponseEntity<>(productsPage, HttpStatus.OK);
    }

    @PostMapping("/new-product")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDTO productRequestDTO) throws IOException {
        boolean status = productService.addProduct(productRequestDTO);
        if(status){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
