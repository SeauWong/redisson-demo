package com.wongcu.orm.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author WongCU
 */
@Data
public class UserQueryBean implements Serializable {
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
     * (Start)
     */
    private Long createdTimeStart;

    /**
     * (End)
     */
    private Long createdTimeEnd;

    /**
     *
     */
    private Long modifiedTime;
    /**
     * (Start)
     */
    private Long modifiedTimeStart;

    /**
     * (End)
     */
    private Long modifiedTimeEnd;

    /**
     *
     */
    private Integer accessTimes;
}