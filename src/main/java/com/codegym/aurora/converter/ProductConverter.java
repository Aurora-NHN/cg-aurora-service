package com.codegym.aurora.converter;


import com.codegym.aurora.entity.Product;
import com.codegym.aurora.payload.request.ProductRequestDTO;
import com.codegym.aurora.payload.request.ProductRequestInCartLineDTO;
import com.codegym.aurora.payload.response.PageProductResponseDTO;
import com.codegym.aurora.payload.response.ProductInAdminResponseDTO;
import com.codegym.aurora.payload.response.ProductResponseDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductConverter {

    Page<PageProductResponseDTO> convertPageEntityToDtoPage(Page<Product> products);

    PageProductResponseDTO convertProductEntityToDTO(Product product);

    List<Product> convertProductListDTOToEntity(List<ProductRequestDTO> productRequestDTOS);

    ProductResponseDTO convertProductEntityInCartLineToDTO(Product product);

    Product convertProductDTOToEntity(ProductRequestInCartLineDTO productRequestInCartLineDTO);
    List<ProductResponseDTO> convertProductListEntityToDTO(List<Product> productList);

    ProductInAdminResponseDTO  convert(Product product);
    Page<ProductInAdminResponseDTO> convert(Page<Product> products);
    List<ProductInAdminResponseDTO> convertProductsToReponseList(List<Product> productList);
}
