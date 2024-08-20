package com.yh.erp.domain.model.product.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductCreateDTO {

    private Long identifyNumber;
    private String name;
    private String modelName;
    private String size;
    private Long price;
    private MultipartFile mainImage;
    private MultipartFile image1;
    private MultipartFile image2;
    private MultipartFile image3;
    private MultipartFile image4;

}
