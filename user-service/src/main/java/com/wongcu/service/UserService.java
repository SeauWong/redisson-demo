package com.wongcu.service;

import com.wongcu.param.PushUserParam;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
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
            lock.lock();
            //todo orm
            Thread.sleep(500L);
            log.debug("入库,param:{}",param);
            return true;
        }catch (Exception e){
            log.error("客户推送发生异常",e);
            return false;
        }finally {
            lock.unlock();
            lock.delete();
        }
    }
}
