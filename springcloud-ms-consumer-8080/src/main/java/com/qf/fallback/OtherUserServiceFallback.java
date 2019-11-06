package com.qf.fallback;

import com.qf.pojo.User;
import com.qf.service.OtherUserService;
import org.springframework.stereotype.Component;

@Component
public class OtherUserServiceFallback implements OtherUserService {
    @Override
    public User getOne(Integer integer) {
        return new User();
    }
}