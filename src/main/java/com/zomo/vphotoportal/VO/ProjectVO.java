package com.zomo.vphotoportal.VO;

import lombok.Data;

import java.sql.Date;

@Data
public class ProjectVO {
    private Integer id;

    private String projectName;

    private String projectSit;

    private String projectBannerHost;

    private String projectKeyImageHost;

    private String projectContent;

    private String projectContentDetail;

    private Date projectTime;
}
