package com.wongcu.param;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author WongCU
 * @date 2018/7/10
 */
@Data
public class PushUserParam implements Serializable {
    private String id;
    private String name;
}
