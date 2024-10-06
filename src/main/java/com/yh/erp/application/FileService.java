package com.yh.erp.application;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.Resource;

import java.io.IOException;

public interface FileService {

    Resource getProductFile(Long fileId, HttpServletRequest request, HttpServletResponse response) throws IOException;

}
