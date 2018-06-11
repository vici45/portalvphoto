package com.zomo.vphotoportal.controller;

import com.zomo.vphotoportal.common.ServiceResponse;
import com.zomo.vphotoportal.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProjectController {
    @Autowired
    private IProjectService projectService;

    @RequestMapping(value = "findByIdDetail")
    public String findByIdDetail(Integer id, Model model){
        ServiceResponse serviceResponse=projectService.findByIdAndProjectStatus(id);
        if (serviceResponse.isSuccess()){
            model.addAttribute("project",serviceResponse.getData());
            return "list";
        }
        model.addAttribute("msg",serviceResponse.getMsg());
        return "error";
    }
    @RequestMapping(value = "findByIdPage")
    public String findByIdPage(Integer id, Model model){
        ServiceResponse serviceResponse=projectService.findByIdAndProjectStatusPage(id);
        if (serviceResponse.isSuccess()){
            model.addAttribute("project",serviceResponse.getData());
            return "projectIndex";
        }
        model.addAttribute("msg",serviceResponse.getMsg());
        return "error";
    }
}
