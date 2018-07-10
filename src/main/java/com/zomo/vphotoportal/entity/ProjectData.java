package com.zomo.vphotoportal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ProjectData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer projectId;
    @Column(name = "project_pv")
    private Integer projectPV;
}
