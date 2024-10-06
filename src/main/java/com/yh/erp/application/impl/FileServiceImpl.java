package com.yh.erp.application.impl;

import com.yh.erp.application.FileService;
import com.yh.erp.domain.model.product.ProductFile;
import com.yh.erp.domain.model.product.ProductFileRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final ProductFileRepository productFileRepository;

    @Override
    public Resource getProductFile(Long fileId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProductFile image = productFileRepository.findImageByFileId(fileId);

        if(image == null){
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }

        File file = new File(image.getFileFullPath());
        return new FileSystemResource(file);
    }

    public String getContentType(File file, HttpServletRequest request) {
        if(file == null) {
            return null;
        }

        String contentType = request.getServletContext().getMimeType(file.getAbsolutePath());

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return contentType;

        //TODO 이외 케이스 추가 필요
//        if (ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("jpeg")) {
//            contentType = "image/jpeg";
//        } else if (ext.equalsIgnoreCase("png")) {
//            contentType = "image/png";
//        } else if (ext.equalsIgnoreCase("gif")) {
//            contentType = "image/gif";
//        } else if (ext.equalsIgnoreCase("tif") || ext.equalsIgnoreCase("tiff")) {
//            contentType = "image/tiff";
//        } else if (ext.equalsIgnoreCase("ipa")) {
//            contentType = "application/octet-stream";
//        } else if (ext.equalsIgnoreCase("apk")) {
//            contentType = "application/vnd.android.package-archive";
//        } else if (ext.equalsIgnoreCase("plist")) {
//            contentType = "application/xml";
//        } else {
//            contentType = "application/octet-stream";
//        }
//        return contentType;
    }


}
