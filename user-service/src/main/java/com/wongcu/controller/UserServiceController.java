package com.wongcu.controller;

import com.wongcu.constants.ErrorCode;
import com.wongcu.param.PushUserParam;
import com.wongcu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author WongCU
 * @date 2018/7/10
 */
@RestController
@RequestMapping("/service/users")
@Slf4j
public class UserServiceController {

    private static final Random random = new Random();
    @Autowired
    private UserService userService;

    @PostMapping("/push")
    public Result<Boolean> pushUser(@RequestBody PushUserParam param) {
        param.setUserId(String.valueOf(random.nextInt(5)));
        log.debug("接收到客户:{}", param);
        try {
            return Result.genSuccessResult(userService.pushUser(param));
        }catch (Exception e){
            log.error("推送客户发生异常",e);
            return Result.genErrorResult(ErrorCode.APP_CODE,ErrorCode.PUSH_USER_ERROR.getCode(),ErrorCode.PUSH_USER_ERROR.getMessage(),e.getMessage());
        }
    }
}
