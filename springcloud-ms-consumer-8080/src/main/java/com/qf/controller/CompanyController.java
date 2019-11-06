package com.qf.controller;

import com.qf.service.CompanyService;
import com.qf.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/com")
@CrossOrigin({"http://localhost:8080"})
public class CompanyController {

    @Resource
    private CompanyService companyService;

    @RequestMapping
    public Object getAll() {
        return companyService.getAll();
    }
}
