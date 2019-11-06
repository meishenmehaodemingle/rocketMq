package com.qf.fallback;

import com.github.pagehelper.PageInfo;
import com.qf.pojo.User;
import com.qf.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class UserServiceFallback implements UserService {

    @Override
    public PageInfo<User> getPageData(Integer currentPage) {
        User user = new User();
        user.setId(-1);
        return PageInfo.of(Arrays.asList(user));

    }

    @Override
    public Map<String, Object> add(User user) {
        return null;
    }

    @Override
    public Map<String, Object> delete(Integer id) {
        return null;
    }
}
