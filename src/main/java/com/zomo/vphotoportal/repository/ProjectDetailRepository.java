package com.zomo.vphotoportal.repository;

import com.zomo.vphotoportal.entity.ProjectDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectDetailRepository extends CrudRepository<ProjectDetail,Integer> {
    List<ProjectDetail> findAllByProjectId(Integer projectId);
}
