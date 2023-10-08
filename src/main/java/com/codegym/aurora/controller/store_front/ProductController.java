package com.codegym.aurora.controller.store_front;


import com.codegym.aurora.payload.response.PageProductResponseDTO;
import com.codegym.aurora.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
            @PageableDefault Pageable pageable) {
        Page<PageProductResponseDTO> pageProductsResponseDTOS = productService.findProductsBySubCategoryId(subCategoryId, pageable);
        return new ResponseEntity<>(pageProductsResponseDTOS, HttpStatus.OK);

    }

}
