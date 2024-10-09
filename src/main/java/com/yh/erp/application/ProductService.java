package com.yh.erp.application;

import com.yh.erp.domain.model.product.dto.ProductCreateDTO;
import com.yh.erp.domain.model.product.dto.ProductDTO;
import com.yh.erp.domain.model.product.dto.ProductSearchReqDTO;
import com.yh.erp.domain.model.product.dto.ProductUpdateDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    ProductDTO getProduct(Long id);

    List<ProductDTO> getProducts(ProductSearchReqDTO dto);

    ProductDTO createProduct(ProductCreateDTO dto, MultipartFile mainImage) throws Exception;

    ProductDTO updateProduct(Long id, ProductUpdateDTO dto, MultipartFile mainImage) throws Exception;

    void deleteProduct(Long id);

}
