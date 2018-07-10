package com.wongcu.service;

import com.wongcu.orm.entity.UserEntity;
import com.wongcu.orm.mapper.UserMapper;
import com.wongcu.param.PushUserParam;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author WongCU
 * @date 2018/7/10
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    @Qualifier("redissonSingle")
    private RedissonClient redisson;

    @Autowired
    private UserMapper userMapper;

    public boolean pushUser(PushUserParam param) {
        RLock lock = redisson.getLock(param.getUserId());
        try {
            lock.lock(1, TimeUnit.MINUTES);
            updateUser(param);
            return true;
        } catch (Exception e) {
            log.error("推送客户发生异常", e);
            return false;
        } finally {
            lock.unlock();
        }
    }

    private void updateUser(PushUserParam param){
        UserEntity userEntity = userMapper.queryById(param.getUserId());
        if(null != userEntity){
            userEntity.setAccessTimes(userEntity.getAccessTimes() + 1);
            userEntity.setModifiedTime(System.currentTimeMillis());
            userEntity.setName(Thread.currentThread().getName());
            userMapper.update(userEntity);
            return;
        }
        userEntity = new UserEntity();
        BeanUtils.copyProperties(param,userEntity);
        userEntity.setAccessTimes(1);
        userEntity.setCreatedTime(System.currentTimeMillis());
        userEntity.setName(Thread.currentThread().getName());
        userMapper.insert(userEntity);
        return;
    }
}
