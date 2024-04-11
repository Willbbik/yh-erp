package com.yh.erp.domain.model.product;

import com.yh.erp.domain.shared.YesOrNo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_PRODUCT_FILE")
@Getter
@NoArgsConstructor
public class ProductFile {

    @Id
    @Column(name = "product_file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_origin_name")
    private String fileOriginName;

    @Column(name = "file_size")
    private Integer fileSize;

    @Column(name = "file_extension")
    private String fileExt;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_full_path")
    private String fileFullPath;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "del_yn")
    @Enumerated(EnumType.STRING)
    private YesOrNo delYn;

}
