package com.zomo.vphotoportal.VO;

import com.google.common.collect.Lists;
import lombok.Data;

import java.sql.Date;
import java.util.List;

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

    private List<ProjectDetailVO> projectDetailVOList=Lists.newArrayList();
}
