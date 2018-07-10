package com.wongcu.orm.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author WongCU
 */
@Data
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private String userId;
    /**
     *
     */
    private String name;
    /**
     *
     */
    private Long createdTime;
    /**
     *
     */
    private Long modifiedTime;
    /**
     *
     */
    private Integer accessTimes;
}