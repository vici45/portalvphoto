package com.zomo.vphotoportal.VO;

import lombok.Data;

@Data
public class ProjectDetailVO {
    private Integer id;
    private String smallImage;
    private String middleImage;
    private String imageHost;
    private String size;
    private String status;
}
