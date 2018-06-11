package com.zomo.vphotoportal.service.imp;

import com.google.common.collect.Lists;
import com.zomo.vphotoportal.VO.ProjectDetailVO;
import com.zomo.vphotoportal.VO.ProjectVO;
import com.zomo.vphotoportal.common.Const;
import com.zomo.vphotoportal.common.ServiceResponse;
import com.zomo.vphotoportal.entity.Project;
import com.zomo.vphotoportal.entity.ProjectDetail;
import com.zomo.vphotoportal.repository.ProjectDetailRepository;
import com.zomo.vphotoportal.repository.ProjectRepository;
import com.zomo.vphotoportal.service.IProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImp implements IProjectService {

    @Value("${qiniu.cdn.prefix}")
    public static String qiNiuCdnPrefix;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectDetailRepository projectDetailRepository;

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

    @Override
    public ServiceResponse findByIdAndProjectStatus(Integer id) {
        Project project=projectRepository.findByIdAndProjectStatus(id,Const.ProjectStatus.ONLINE.getCode());
        if (project==null){
            return ServiceResponse.createErrorMsg("项目不存在");
        }
        ProjectVO projectVO=assembleProject2ProjectVo(project);
        List<ProjectDetail> projectDetailList=projectDetailRepository.findAllByProjectId(project.getId());
        List<ProjectDetailVO> projectDetailVOList=Lists.newArrayList();
        if (projectDetailList.size()>=0){
           projectDetailVOList=assembleProjectDetailList2ProjectDetailVOList(projectDetailList);
        }
        projectVO.setProjectDetailVOList(projectDetailVOList);
        return ServiceResponse.createSuccess(projectVO);
    }

    @Override
    public ServiceResponse findByIdAndProjectStatusPage(Integer id) {
        Project project=projectRepository.findByIdAndProjectStatus(id,Const.ProjectStatus.ONLINE.getCode());
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

    /**
     * 组装projectDetailVo
     * @param projectDetail
     * @return
     */
    private ProjectDetailVO assembleProjectDetail2ProjectDetailVO(ProjectDetail projectDetail){
        ProjectDetailVO projectDetailVo=new ProjectDetailVO();
        BeanUtils.copyProperties(projectDetail,projectDetailVo);
        String smallImage=Const.qiNiuCdnPrefix+projectDetail.getImageHost()+Const.qiNiuSmallImageSuffix;
        String middleImage=Const.qiNiuCompressCdnPrefix+projectDetail.getImageHost();
        projectDetailVo.setSmallImage(smallImage);
        projectDetailVo.setMiddleImage(middleImage);
        projectDetailVo.setImageHost(Const.qiNiuCdnPrefix+projectDetail.getImageHost());
        return projectDetailVo;
    }

    private List<ProjectDetailVO> assembleProjectDetailList2ProjectDetailVOList(List<ProjectDetail> projectDetailList){
        List<ProjectDetailVO> projectDetailVOList=Lists.newArrayList();
        for (ProjectDetail projectDetail : projectDetailList) {
            ProjectDetailVO projectDetailVO=assembleProjectDetail2ProjectDetailVO(projectDetail);
            projectDetailVOList.add(projectDetailVO);
        }
        return projectDetailVOList;
    }

}
