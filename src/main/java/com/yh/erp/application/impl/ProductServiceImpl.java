package com.yh.erp.application.impl;

import com.yh.erp.application.ProductService;
import com.yh.erp.domain.model.product.Product;
import com.yh.erp.domain.model.product.ProductRepository;
import com.yh.erp.domain.model.product.dto.ProductCreateDTO;
import com.yh.erp.domain.model.product.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductDTO createProduct(ProductCreateDTO dto) {

        Product product = Product.builder()
            .name(dto.getName())
            .size(dto.getSize())
            .price(dto.getPrice())
            .identifyNumber(dto.getIdentifyNumber())
//                .mainImageFullPath(null)
            .build();

        productRepository.save(product);

        return null;
    }

}
