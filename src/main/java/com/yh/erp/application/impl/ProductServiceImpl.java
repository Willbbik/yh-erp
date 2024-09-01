package com.yh.erp.application.impl;

import com.yh.erp.application.ProductService;
import com.yh.erp.common.FileUploader;
import com.yh.erp.domain.model.product.Product;
import com.yh.erp.domain.model.product.ProductFile;
import com.yh.erp.domain.model.product.ProductFileRepository;
import com.yh.erp.domain.model.product.ProductRepository;
import com.yh.erp.domain.model.product.dto.*;
import com.yh.erp.domain.shared.YesOrNo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductFileRepository productFileRepository;

    private final FileUploader fileUploader;

    private static final ModelMapper modelMapper = new ModelMapper();

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

    @Transactional
    @Override
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
            if(image == null){
                continue;
            }
            //TODO 추후 directroy 어떻게 할지 생각 필요
            ProductFile productFile = fileUploader.uploadProductImage(image, product.getId(), null, sort);
            imageInfos.add(productFile);
            sort++;
        }

//        ModelMapper modelMapper = new ModelMapper();
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setMainImageInfo(mainImageInfo);
        productDTO.setImageInfos(imageInfos);
        return productDTO;
    }

    @Transactional
    @Override
    public ProductDTO updateProduct(Long id, ProductUpdateDTO dto) throws Exception {

        Product product = productRepository.findByIdAndDelYn(id, YesOrNo.NO);
        if(product == null){
            throw new RuntimeException("존재하지 않는 상품입니다.");
        }

        //TODO 물리파일 삭제는 추후 스케줄러를 통해서 할 예정

        //메인 이미지 삭제
        ProductFile originMainImage = productFileRepository.findMainImageById(id);
        if(YesOrNo.isYes(dto.getMainImageDelYn())){
            product.setMainImageFullPath(null);
            originMainImage.delete();
        }

        //이미지 삭제
        productFileRepository.delFilesByIds(id, dto.getDelImageIds());

        //메인이미지 중복 업로드 체크
        if(YesOrNo.isNo(dto.getMainImageDelYn()) && dto.getMainImage() != null){
            throw new RuntimeException("메인 이미지가 이미 존재합니다. 삭제 후 재등록 해주세요.");
        }

        Integer lastSort = productFileRepository.findLastSort(id);

        //메인 파일 업로드
        ProductFile mainImageInfo = null;
        if(dto.getMainImage() != null){
            ++lastSort;

            mainImageInfo = fileUploader.uploadProductImage(dto.getMainImage(), product.getId(), null, lastSort);
            mainImageInfo.isMainFile();
            product.updateMainImageFullPath(mainImageInfo.getFileFullPath());
        }

        //기타 이미지들 저장
        List<ProductFile> imageInfos = new ArrayList<>();
        for(MultipartFile image : dto.getImages()) {
            if(image == null){
                continue;
            }

            ++lastSort;
            ProductFile productFile = fileUploader.uploadProductImage(image, product.getId(), null, lastSort);
            imageInfos.add(productFile);
        }

        product.setG2bNumber(dto.getG2bNumber());
        product.setName(dto.getName());
        product.setModelName(dto.getModelName());
        product.setSize(dto.getSize());
        product.setPrice(dto.getPrice());
        product.setModAt(LocalDateTime.now());

        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setMainImageInfo(mainImageInfo);
        productDTO.getImageInfos().addAll(productFileRepository.findImagesById(id));
        productDTO.getImageInfos().addAll(imageInfos);

        return productDTO;
    }
}
