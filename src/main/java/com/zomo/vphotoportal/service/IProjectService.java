package com.zomo.vphotoportal.service;

import com.zomo.vphotoportal.common.ServiceResponse;

public interface IProjectService {

    ServiceResponse findAll();
    ServiceResponse findById(Integer id);
    ServiceResponse findByIdAndProjectStatus(Integer id);
    ServiceResponse findByIdAndProjectStatusPage(Integer id);
}
