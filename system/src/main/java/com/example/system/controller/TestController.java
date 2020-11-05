package com.example.system.controller;

import com.example.server.domain.Test;
import com.example.server.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 * date: 2020/10/29 19:37
 *
 * @author 896951384
 * @since JDK 1.8
 */
@RestController
public class TestController {

    @Autowired
    public TestService testService;

    @RequestMapping("/selectAll")
    public List<Test> selectAll(){
        List<Test> list = testService.selectAll();
        return list;
    }
}
