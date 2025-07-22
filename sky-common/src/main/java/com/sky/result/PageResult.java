package com.sky.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 封装分页查询结果
 */
@Data
@AllArgsConstructor //自动生成包含所有字段的构造方法
@NoArgsConstructor // 自动生成一个无参构造函数
public class PageResult implements Serializable {

    private long total; //总记录数

    private List records; //当前页数据集合

}
