package com.yh.erp.application.impl;

import com.yh.erp.application.ProductService;
import com.yh.erp.common.FileUploader;
import com.yh.erp.domain.model.product.Product;
import com.yh.erp.domain.model.product.ProductFile;
import com.yh.erp.domain.model.product.ProductRepository;
import com.yh.erp.domain.model.product.dto.ProductCreateDTO;
import com.yh.erp.domain.model.product.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final FileUploader fileUploader;

    @Override
    @Transactional
    public ProductDTO createProduct(ProductCreateDTO dto) throws Exception {

        //물품 등록
        Product product = Product.builder()
            .name(dto.getName())
            .modelName(dto.getModelName())
            .size(dto.getSize())
            .price(dto.getPrice())
            .g2bNumber(dto.getG2bNumber())
            .build();

        productRepository.save(product);

        Integer sort = 1;

        //메인 이미지 저장
        ProductFile mainImageInfo = null;
        if(dto.getMainImage() != null){
            mainImageInfo = fileUploader.uploadProductImage(dto.getMainImage(), product.getId(), "temp2", sort);
            product.updateMainImageFullPath(mainImageInfo.getFileFullPath());
            sort++;
        }

        //기타 이미지들 저장
        List<ProductFile> imageInfos = new ArrayList<>();
        for(MultipartFile image : dto.getImages()) {
            ProductFile productFile = fileUploader.uploadProductImage(image, product.getId(), "temp2", sort);
            imageInfos.add(productFile);
            sort++;
        }

        ModelMapper modelMapper = new ModelMapper();
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setMainImageInfo(mainImageInfo);
        productDTO.setImageInfos(imageInfos);
        return productDTO;
    }

}
