package com.yh.erp.domain.model.product.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductCreateDTO {

    private Long identifyNumber;
    private String name;
    private String size;
    private Long price;
    private MultipartFile mainImage;
}
