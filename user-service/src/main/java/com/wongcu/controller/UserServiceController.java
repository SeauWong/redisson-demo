package com.wongcu.controller;

import com.wongcu.param.PushUserParam;
import com.wongcu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author WongCU
 * @date 2018/7/10
 */
@RestController
@RequestMapping("/service/users")
@Slf4j
public class UserServiceController {

    @Autowired
    private UserService userService;

    @PostMapping("/push")
    public Result<Boolean> pushUser(@RequestBody PushUserParam param){
        log.debug("接收到客户:{}",param);
        return Result.genSuccessResult(userService.pushUser(param));
    }
}
