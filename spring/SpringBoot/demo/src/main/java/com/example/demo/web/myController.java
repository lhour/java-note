package com.example.demo.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class myController {
    
    @Value("${school.name}")
    private String name;

    @Value("${school.add}")
    private String add;

    @RequestMapping(value = "/say")
    @ResponseBody
    public String say() {
        
        return "hello " + name;
    }

    @RequestMapping(value = "/map")
    @ResponseBody
    public Map<String, String> map() {
        Map<String, String> map = new HashMap<>();
        map.put("name",name);
        map.put("add",add);

        return map;
    }
}
