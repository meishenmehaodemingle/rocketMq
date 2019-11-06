package com.qf.service;

import com.github.pagehelper.PageInfo;
import com.qf.fallback.UserServiceFallback;
import com.qf.pojo.Company;
import com.qf.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 在spring cloud中，所有服务接口需要写在同一个接口中。
 */
@FeignClient(name="ms-provider", contextId = "userService", fallback = UserServiceFallback.class)
public interface UserService {

    @RequestMapping("/user")
    public PageInfo<User> getPageData(@RequestParam Integer currentPage);

    /**
    @RequestMapping("/company")
    public List<Company> getAll();  // list集合对应Json[{}, {}], 对象对应的json {}
    */

    // 调用其他服务的时候只能使用@RequestMapping
    @RequestMapping(value="/user", method = RequestMethod.POST)
    public Map<String, Object> add(User user);

    @RequestMapping(value="/user/{id}", method = RequestMethod.DELETE)
    public Map<String, Object> delete(@PathVariable("id") Integer id);
}
