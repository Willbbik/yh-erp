package com.yh.erp.domain.model.product.dto;

import com.yh.erp.domain.model.product.ProductFile;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {

    private Long id;
    private Long identifyNumber;
    private String name;
    private String size;
    private String price;
    private String mainImageFullPath;

    private ProductFile mainImageInfo;
    private List<ProductFile> imageInfos;
}
