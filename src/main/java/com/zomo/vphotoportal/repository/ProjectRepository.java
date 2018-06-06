package com.zomo.vphotoportal.repository;

import com.zomo.vphotoportal.entity.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project,Integer> {
    List<Project> findAllByProjectStatus(Integer status);
    Project findById(Integer id);
}
