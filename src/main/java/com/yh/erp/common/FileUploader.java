package com.yh.erp.common;

import com.yh.erp.domain.model.product.ProductFile;
import com.yh.erp.domain.model.product.ProductFileRepository;
import com.yh.erp.domain.shared.YesOrNo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileUploader {

    @Value("${file.upload_path}")
    private String uploadPath;

    private static final String PRODUCT_PATH = "/product";

    private final ProductFileRepository productFileRepository;

    public ProductFile uploadProductImage(MultipartFile file, Long productId, String directory) throws Exception {

        String originalFilename = file.getOriginalFilename();
        String extension = StringUtils.getFilenameExtension(originalFilename);
        String newFileName = originalFilename + "_" + UUID.randomUUID().toString();
        String path = uploadPath + PRODUCT_PATH + directory;
        String fileFullPath = uploadPath + PRODUCT_PATH + directory + newFileName + extension;

        //디렉토리 생성
        Path uploadDirectory = Paths.get(path);
        if(!Files.exists(uploadDirectory)){
            Files.createDirectory(uploadDirectory);
        }

        //파일 생성
        File newFile = new File(fileFullPath);
        file.transferTo(newFile);

        //파일 sort 조회
        Integer fileLength = productFileRepository.getLastSort(productId);

        //파일 정보 리턴
        ProductFile productFile = ProductFile.builder()
                .productId(productId)
                .fileName(newFileName)
                .fileOriginName(originalFilename)
                .fileExt(extension)
                .fileFullPath(fileFullPath)
                .filePath(path)
                .fileSize(newFile.length())
                .delYn(YesOrNo.NO)
                .sort(fileLength++)
                .build();

        productFileRepository.save(productFile);

        return productFile;
    }


}
