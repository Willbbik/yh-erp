package com.yh.erp.domain.model.product;

import com.yh.erp.domain.shared.YesOrNo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_PRODUCT_FILE")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFile {

    @Id
    @Column(name = "product_file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_origin_name")
    private String fileOriginName;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_extension")
    private String fileExt;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_full_path")
    private String fileFullPath;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "del_yn")
    @Convert(converter = YesOrNo.StringTo.class)
    private YesOrNo delYn;

}
