package com.zredtea.TeaWIKI.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zredtea.TeaWIKI.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    /**
     * 根据课程名精确查找
     */
    default Course selectByName(String courseName) {
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Course::getCourseName,courseName);
        return selectOne(queryWrapper);
    }

    /**
     * 根据课程名模糊查询
     */
    default List<Course> matchByName(String courseName) {
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Course::getCourseName,courseName);
        return selectList(queryWrapper);
    }
}
