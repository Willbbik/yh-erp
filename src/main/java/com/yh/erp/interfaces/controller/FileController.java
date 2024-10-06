package com.yh.erp.interfaces.controller;

import com.yh.erp.application.FileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/api/file/products/{id}")
    public Resource getFileById(@PathVariable(name = "id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return fileService.getProductFile(id, request, response);
    }

}
