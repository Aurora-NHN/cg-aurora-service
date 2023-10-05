package com.codegym.aurora.controller.store_front;


import com.codegym.aurora.payload.response.PageProductResponseDTO;
import com.codegym.aurora.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("")
    public ResponseEntity<Page<PageProductResponseDTO>> getPageProducts(@PageableDefault Pageable pageable) {
        Page<PageProductResponseDTO> pageProductResponseDTOS = productService.getProductsPage(pageable);
        if (pageProductResponseDTOS.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(pageProductResponseDTOS);
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
