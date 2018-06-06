package com.zomo.vphotoportal.service.imp;

import com.google.common.collect.Lists;
import com.zomo.vphotoportal.VO.ProjectVO;
import com.zomo.vphotoportal.common.Const;
import com.zomo.vphotoportal.common.ServiceResponse;
import com.zomo.vphotoportal.entity.Project;
import com.zomo.vphotoportal.repository.ProjectRepository;
import com.zomo.vphotoportal.service.IProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImp implements IProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ServiceResponse findAll() {
        List<Project> projectList = projectRepository.findAllByProjectStatus(Const.ProjectStatus.ONLINE.getCode());
        if (projectList.size()<=0){
            return ServiceResponse.createErrorMsg("没有项目");
        }
        List<ProjectVO> projectVOList=assembleProjectList2ProjectVOList(projectList);
        return ServiceResponse.createSuccess(projectVOList);
    }

    @Override
    public ServiceResponse findById(Integer id) {
        Project project=projectRepository.findById(id);
        if (project==null){
            return ServiceResponse.createErrorMsg("项目不存在");
        }
        ProjectVO projectVO=assembleProject2ProjectVo(project);
        return ServiceResponse.createSuccess(projectVO);
    }

    /**
     * 组装ProjectVOList
     * @param projectList
     * @return
     */
    private List<ProjectVO> assembleProjectList2ProjectVOList(List<Project> projectList){
        List<ProjectVO> projectVOList=Lists.newArrayList();
        for (Project project : projectList) {
            ProjectVO projectVO=assembleProject2ProjectVo(project);
            projectVOList.add(projectVO);
        }
        return projectVOList;
    }

    /**
     * 组装ProjectVo
     * @param project
     * @return projectVO
     */
    private ProjectVO assembleProject2ProjectVo(Project project){
        ProjectVO projectVO=new ProjectVO();
        BeanUtils.copyProperties(project,projectVO);
        return projectVO;
    }

}
