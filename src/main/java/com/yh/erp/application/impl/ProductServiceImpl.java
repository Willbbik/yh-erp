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

        /**
         *
         * 이미지 삭제
         * 새로 등록 할 메인이미지 있으면 db에 존재여부 확인 후 저장
         *
         * 상품 등록
         *
         * 등록 할 이미지랑 등록된 이미지 개수 확인
         * 이미지 저장
         *
         *
         *
         */

        Product product = Product.builder()
            .name(dto.getName())
            .modelName(dto.getModelName())
            .size(dto.getSize())
            .price(dto.getPrice())
            .g2bNumber(dto.getG2bNumber())
            .build();

        productRepository.save(product);

        List<ProductFile> uploadedImages = new ArrayList<>();

        ProductFile mainImageInfo = null;
        //메인 이미지 저장
        if(dto.getMainImage() != null){
            mainImageInfo = fileUploader.uploadProductImage(dto.getMainImage(), product.getId(), "temp");
            uploadedImages.add(mainImageInfo);
            product.updateMainImageFullPath(mainImageInfo.getFileFullPath());
        }

        List<ProductFile> imageInfos = new ArrayList<>();
        //기타 이미지들 저장
        for(MultipartFile image : dto.getImages()) {
            ProductFile productFile = fileUploader.uploadProductImage(image, product.getId(), "temp");
            uploadedImages.add(productFile);
            imageInfos.add(productFile);
        }

        ModelMapper modelMapper = new ModelMapper();
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setMainImageInfo(mainImageInfo);
        productDTO.setImageInfos(imageInfos);
        return productDTO;
    }

}
