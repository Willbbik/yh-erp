package com.yh.erp.common;

import com.yh.erp.domain.model.product.ProductFile;
import com.yh.erp.domain.model.product.ProductFileRepository;
import com.yh.erp.domain.shared.YesOrNo;
import com.yh.erp.infrastructure.properties.FileProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploader {

    private static final String PRODUCT_PATH = "/files/product";

    private final FileProperties fileProperties;

    private final ProductFileRepository productFileRepository;

    @Transactional
    public ProductFile uploadProductImage(MultipartFile file, Long productId, String directory, Integer sort) throws Exception {

        if(file == null) {
            return null;
        }

        if(StringUtils.isEmpty(directory)){
            directory = "";
        }

        if(!directory.contains("/")){
            directory = "/"+directory;
        }

        String fileNameNoExt = removeExtension(file.getOriginalFilename());
        String originalFilename = file.getOriginalFilename();
        String extension = StringUtils.getFilenameExtension(originalFilename);
        String newFileName = fileNameNoExt + "__" + UUID.randomUUID() + "." + extension;
        String path = PRODUCT_PATH + directory + "/" + newFileName;
        String fileFullPath = fileProperties.getUploadPath() + PRODUCT_PATH + directory + "/" + newFileName;

        //디렉토리 생성
        this.createDirectory(fileProperties.getUploadPath() + path);

        //파일 생성
        File newFile = this.createFile(file, fileFullPath);

        //파일 정보 리턴
        ProductFile productFile = ProductFile.builder()
                .productId(productId)
                .fileName(originalFilename)
                .fileOriginName(newFileName)
                .fileExt(extension)
                .fileFullPath(fileFullPath)
                .filePath(path)
                .fileSize(newFile.length())
                .delYn(YesOrNo.NO)
                .mainFileYn(YesOrNo.NO)
                .sort(sort)
                .build();

        //파일 정보 db 저장
        productFileRepository.save(productFile);

        return productFile;
    }

    private void createDirectory(String dir) throws IOException {
        Path uploadDirectory = Paths.get(dir);
        if(!Files.exists(uploadDirectory)){
            Files.createDirectories(uploadDirectory);
        }
    }

    private File createFile(MultipartFile file, String fileFullPath) throws IOException {
        File newFile = new File(fileFullPath);
        file.transferTo(newFile);

        return newFile;
    }

    private String removeExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex != -1) {
            fileName = fileName.substring(0, lastIndex);
        }
        return fileName;
    }

}
