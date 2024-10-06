package com.yh.erp.domain.model.product.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductUpdateDTO {

    private String g2bNumber;
    private String category;
    private String name;
    private String modelName;
    private String size;
    private Long price;
    private MultipartFile mainImage;
    private List<MultipartFile> images = new ArrayList<>();
    private String mainImageDelYn;
    private List<Long> delImageIds = new ArrayList<>();
}
