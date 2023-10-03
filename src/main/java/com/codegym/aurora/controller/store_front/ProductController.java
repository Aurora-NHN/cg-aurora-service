package com.codegym.aurora.controller.store_front;


import com.codegym.aurora.payload.response.HomeProductResponseDTO;
import com.codegym.aurora.payload.response.PageProductResponseDTO;
import com.codegym.aurora.payload.response.ProductDetailResponseDTO;
import com.codegym.aurora.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<HomeProductResponseDTO>> getPageProductHome(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<HomeProductResponseDTO> homeProductResponseDTOS = productService.getProductsPage(page, size);
        if (homeProductResponseDTOS.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(homeProductResponseDTOS);
        }
    }

    @GetMapping("product/{productId}")
    public ResponseEntity<ProductDetailResponseDTO> getProductDetail(
            @PathVariable Long productId) {
        ProductDetailResponseDTO productDetailResponseDTO = productService.getProductDetail(productId);

        if (productDetailResponseDTO != null) {
            return new ResponseEntity<>(productDetailResponseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PageProductResponseDTO>> getPageSearchProducts(
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<PageProductResponseDTO> searchPageResponseDTOS = productService.searchProductsByName(keyword, page, size);

        if (searchPageResponseDTOS.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(searchPageResponseDTOS);
        }
    }
}
