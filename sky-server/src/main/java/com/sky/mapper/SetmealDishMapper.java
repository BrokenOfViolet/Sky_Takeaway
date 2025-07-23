package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// Mybatis自动完成，不需要实现这个接口
@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id查询对应的套餐id
     * @param dishIds
     * @retur
     */
    // select setmeal_id from setmeal_dish where dish_id in (1,2,3,4);
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);
}
