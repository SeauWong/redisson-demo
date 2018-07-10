package com.wongcu.orm.mapper;

import com.wongcu.orm.entity.UserEntity;
import com.wongcu.orm.param.UserQueryBean;

import java.util.List;

/**
* @author WongCU
*/
public interface UserMapper {
    int delete(String id);

    int insert(UserEntity entity);

    UserEntity queryById(String id);

    List<UserEntity> query(UserQueryBean queryBean);

    int update(UserEntity entity);

}
