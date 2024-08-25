package com.yh.erp.application;

import com.yh.erp.domain.model.product.dto.ProductCreateDTO;
import com.yh.erp.domain.model.product.dto.ProductDTO;

public interface ProductService {

    ProductDTO createProduct(ProductCreateDTO dto) throws Exception;

}
