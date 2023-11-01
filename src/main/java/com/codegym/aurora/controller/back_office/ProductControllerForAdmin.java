package com.codegym.aurora.controller.back_office;

import com.codegym.aurora.payload.request.ProductCreateRequestDto;
import com.codegym.aurora.payload.response.ProductInAdminResponseDTO;
import com.codegym.aurora.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class ProductControllerForAdmin {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductInAdminResponseDTO>> getProductsPage(
            @PageableDefault(size = 5) Pageable pageable){

        Page<ProductInAdminResponseDTO> productsPage= productService.getProductsPageInAdmin(pageable);
        return new ResponseEntity<>(productsPage, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> createProduct(@ModelAttribute @Validated ProductCreateRequestDto productRequestDTO,
                                                BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return productService.createProduct(productRequestDTO);
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateProduct(@RequestBody @Validated ProductCreateRequestDto productRequestDTO,
                                                BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return productService.updateProduct(productRequestDTO);
        }

    }

//    @DeleteMapping("/{productId}")
//    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable Long id){
//        ResponseDTO responseDTO = productService.deleteByProductId(id);
//        return new ResponseEntity<>(responseDTO,responseDTO.getStatus());
//    }
}
