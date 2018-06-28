package com.zomo.vphotoportal.controller;

import com.zomo.vphotoportal.common.ServiceResponse;
import com.zomo.vphotoportal.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    private IProjectService projectService;

    @RequestMapping(value = "index")
    public String index(Model model){
        ServiceResponse response=projectService.findAll();
        if (!response.isSuccess()){
            return "error";
        }
        model.addAttribute("projectVOList",response.getData());
        return "index";
    }
    @RequestMapping(value = "testtest")
    public String test(){
        return "weiChat";
    }

    @RequestMapping(value = "list")
    public String load(){
        return "layui";
    }


}
