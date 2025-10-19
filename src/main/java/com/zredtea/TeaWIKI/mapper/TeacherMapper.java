package com.zredtea.TeaWIKI.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zredtea.TeaWIKI.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    default Teacher SelectByTeacherName(String teacherName){
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("teacher_name",teacherName);
        return selectOne(queryWrapper);
    }
}
