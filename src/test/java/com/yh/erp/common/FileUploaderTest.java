package com.yh.erp.common;

import com.yh.erp.domain.model.product.ProductFile;
import com.yh.erp.domain.model.product.ProductFileRepository;
import com.yh.erp.infrastructure.properties.FileProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "local")
class FileUploaderTest {

    @InjectMocks
    private FileUploader fileUploader;

    @Mock
    private ProductFileRepository productFileRepository;

    @Mock
    private FileProperties fileProperties;

    @Test
    void uploadProductImage() throws Exception {

        //given
        MockMultipartFile multipartFile2 = new MockMultipartFile("file", "test2.txt", "text/plain", "test file2".getBytes(StandardCharsets.UTF_8) );

        when(productFileRepository.save(any())).thenReturn(null);
        when(productFileRepository.getLastSort(any())).thenReturn(1);
        when(fileProperties.getUploadPath()).thenReturn("/Users/min/Documents/yh-erp/file");

        //when
        ProductFile productFile = fileUploader.uploadProductImage(multipartFile2, 1L, "/a");

        //then
        Assertions.assertNotNull(productFile);
        Assertions.assertEquals(productFile.getFileName(), "test2.txt");
        Assertions.assertEquals(productFile.getSort(), 2);
    }

}