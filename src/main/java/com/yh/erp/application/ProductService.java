package com.yh.erp.application;

import com.yh.erp.domain.model.product.dto.ProductCreateDTO;
import com.yh.erp.domain.model.product.dto.ProductDTO;
import com.yh.erp.domain.model.product.dto.ProductSearchReqDTO;

import java.util.List;

public interface ProductService {

    ProductDTO getProduct(Long id);

    List<ProductDTO> getProducts(ProductSearchReqDTO dto);

    ProductDTO createProduct(ProductCreateDTO dto) throws Exception;

}
