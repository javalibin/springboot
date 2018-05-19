package com.school.manage.mapper;

import com.school.manage.model.times;

public interface timesMapper {
    int deleteByPrimaryKey(Integer datasId);

    int insert(times record);

    int insertSelective(times record);

    times selectByPrimaryKey(Integer datasId);

    int updateByPrimaryKeySelective(times record);

    int updateByPrimaryKey(times record);
}