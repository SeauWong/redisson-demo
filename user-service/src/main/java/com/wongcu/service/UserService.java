package com.wongcu.service;

import com.wongcu.param.PushUserParam;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
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
    RedissonClient redisson;

    public boolean pushUser(PushUserParam param) {
        RLock lock = redisson.getLock(param.getId());
        try {
            lock.lock(1, TimeUnit.MINUTES);
            log.debug("{}开始:{}", param.getId(), new Date());
            Thread.sleep(500L);
            log.debug("{}结束:{}", param.getId(), new Date());
            return true;
        } catch (Exception e) {
            log.error("客户推送发生异常", e);
            return false;
        } finally {
            lock.unlock();
        }
    }
}
