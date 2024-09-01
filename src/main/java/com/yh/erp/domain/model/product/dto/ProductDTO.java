package com.yh.erp.domain.model.product.dto;

import com.yh.erp.domain.model.product.ProductFile;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDTO {

    private Long id;
    private Long g2bNumber;
    private String name;
    private String modelName;
    private String size;
    private Long price;
    private String mainImageFullPath;
    private ProductFile mainImageInfo;
    private List<ProductFile> imageInfos = new ArrayList<>();
}
