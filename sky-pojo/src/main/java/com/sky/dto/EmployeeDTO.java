package com.sky.dto;

import lombok.Data;

import java.io.Serializable; // 序列化，将复杂对象转化成字节流，方便存储或者网络传输(redis, mysql)

@Data // 自动生成该类的常用代码：比如getter()和setter()方法，toString(),equals()等方法
public class EmployeeDTO implements Serializable {

    private Long id;

    private String username;

    private String name;

    private String phone;

    private String sex;

    private String idNumber;

}
