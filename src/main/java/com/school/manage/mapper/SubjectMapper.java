package com.school.manage.mapper;

import java.util.List;

import com.school.manage.model.Subject;

public interface SubjectMapper {
    int deleteByPrimaryKey(Integer subjectId);

    int insert(Subject record);

    int insertSelective(Subject record);

    Subject selectByPrimaryKey(Integer subjectId);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);
    
    List<Subject> selectAll();
    
   int updateByRowCloun(Subject record);
   
   Subject selectByRowCloun(Subject record);
}