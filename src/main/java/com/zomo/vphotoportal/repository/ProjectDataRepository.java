package com.zomo.vphotoportal.repository;

import com.zomo.vphotoportal.entity.ProjectData;
import org.springframework.data.repository.CrudRepository;

public interface ProjectDataRepository extends CrudRepository<ProjectData,Integer> {

    ProjectData findByProjectId(Integer projectId);
}
