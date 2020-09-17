package com.yzh1024.entity;

import com.yzh1024.utils.Entity;
import lombok.Data;

import java.math.BigInteger;

/**
 * @author yzh1024
 * @date 2020/9/10
 **/
@Data
public class User extends Entity {
    private Integer id;
    private String userName;
    private String userPwd;
    private String name;
    private String remark;
}
