package com.example.demo.web;

import com.example.demo.config.School;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class myController {
    
    // @Value("${school.name}")
    // private String name;

    // @Value("${school.add}")
    // private String add;

    @Autowired
    private School school;

    @RequestMapping(value = "/say")
    @ResponseBody
    public String say() {
        
        return "hello " + school.getName();
    }

    @RequestMapping(value = "/map")
    @ResponseBody
    public Map<String, String> map() {
        Map<String, String> map = new HashMap<>();
        map.put("name",school.getName());
        map.put("add",school.getAdd());

        return map;
    }

    @RequestMapping(value = "/myjsp")
    public ModelAndView myjsp(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", school.getName());
        mv.setViewName("myjsp");
        return mv;
    }
}
