package org.mvnsearch.spring.boot.hessian.demo.impl;

import org.mvnsearch.spring.boot.hessian.HessianService;
import org.mvnsearch.spring.boot.hessian.demo.UserService;
import org.springframework.stereotype.Component;

/**
 * user service implementation
 *
 * @author linux_china
 */
@Component(value = "userService")
@HessianService(serviceInterface = UserService.class)
public class UserServiceImpl implements UserService {
    public String findNick(Integer id) {
        return "nick:" + id;
    }
}
