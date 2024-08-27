package com.yh.erp.application.impl;

import com.yh.erp.application.ProductService;
import com.yh.erp.common.FileUploader;
import com.yh.erp.domain.model.product.Product;
import com.yh.erp.domain.model.product.ProductFile;
import com.yh.erp.domain.model.product.ProductFileRepository;
import com.yh.erp.domain.model.product.ProductRepository;
import com.yh.erp.domain.model.product.dto.ProductCreateDTO;
import com.yh.erp.domain.model.product.dto.ProductDTO;
import com.yh.erp.domain.model.product.dto.ProductFileDTO;
import com.yh.erp.domain.model.product.dto.ProductSearchReqDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductFileRepository productFileRepository;

    private final FileUploader fileUploader;

    @Override
    public List<ProductDTO> getProducts(ProductSearchReqDTO dto) {
        return productRepository.findProducts(dto);
    }


    @Override
    public ProductDTO getProduct(Long id) {
        ProductDTO productDTO = productRepository.findProductById(id);
        productDTO.setMainImageInfo(productFileRepository.findMainImageById(id));
        productDTO.setImageInfos(productFileRepository.findImagesById(id));

        return productDTO;
    }

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
            //TODO 추후 directroy 어떻게 할지 생각 필요
            mainImageInfo = fileUploader.uploadProductImage(dto.getMainImage(), product.getId(), null, sort);
            mainImageInfo.isMainFile();
            product.updateMainImageFullPath(mainImageInfo.getFileFullPath());
            sort++;
        }

        //기타 이미지들 저장
        List<ProductFile> imageInfos = new ArrayList<>();
        for(MultipartFile image : dto.getImages()) {
            //TODO 추후 directroy 어떻게 할지 생각 필요
            ProductFile productFile = fileUploader.uploadProductImage(image, product.getId(), null, sort);
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
