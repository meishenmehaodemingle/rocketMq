package com.qf.service;

import com.qf.pojo.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * ms-provider.FeignClientSpecification
 */
@FeignClient(name="ms-provider", contextId = "companyService")
public interface CompanyService {

    @RequestMapping("/company")
    public List<Company> getAll();  // list集合对应Json[{}, {}], 对象对应的json {}
}
