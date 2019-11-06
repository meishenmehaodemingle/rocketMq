package com.qf.controller;

import com.github.pagehelper.PageInfo;
import com.qf.pojo.User;
import com.qf.service.OtherUserService;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cuser")
@CrossOrigin(origins={"http://localhost:8080"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE})
public class ConsumerUserController {

//    @Autowired
//    private RestTemplate restTemplate;

    @Resource
    private UserService userService;

    @Resource
    private OtherUserService otherUserService;

    @RequestMapping
    public Object getRemoteUsers(@RequestParam(defaultValue = "1") Integer currentPage) {

//        List<User> list = restTemplate.getForObject("http://ms-provider/user", List.class);
        PageInfo<User> list = userService.getPageData(currentPage);

//        System.out.println(otherUserService.getOne(18).getName());

        return list;
    }


    @RequestMapping(method = RequestMethod.POST)
    public Object addUser(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        try{
            userService.add(user);
            map.put("code", 1);
            map.put("msg", "数据添加成功");
        }catch(Exception ex) {
            ex.printStackTrace();
            map.put("code", -1);
            map.put("msg", "数据添加失败，请联系管理员.");
        }
        return map;
    }

    @RequestMapping(value= "/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable("id") Integer id) {
        Map<String, Object> map = new HashMap<>();
        try{
            userService.delete(id);
            map.put("code", 1);
            map.put("msg", "数据删除成功");
        }catch(Exception ex) {
            ex.printStackTrace();
            map.put("code", -1);
            map.put("msg", "数据删除失败，请联系管理员.");
        }
        return map;
    }

    /**
    public public void main(String[] args) {
        String str = null;
        try {

            new Thread(() -> {
                System.out.println("-------------------");
                int a = 10 / 0;

            }).start();

            new Thread(() -> {
                System.out.println("*********************");
                str.substring(2);
            }).start();

        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     */
}
