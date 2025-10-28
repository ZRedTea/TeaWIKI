package com.zredtea.TeaWIKI.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zredtea.TeaWIKI.entity.CourseTeacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseTeacherMapper extends BaseMapper<CourseTeacher> {
    /**
     * 根据教师id和课程id联合精确查询
     */
    default CourseTeacher selectByUnionId(Integer teacherId,Integer courseId) {
        LambdaQueryWrapper<CourseTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseTeacher::getTeacherId,teacherId);
        queryWrapper.eq(CourseTeacher::getCourseId,courseId);
        return selectOne(queryWrapper);
    }

    /**
     * 根据教师id查询所有联系
     */
    default List<CourseTeacher> selectAllByTeacherId(Integer teacherId) {
        LambdaQueryWrapper<CourseTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseTeacher::getTeacherId,teacherId);
        return selectList(queryWrapper);
    }

    /**
     * 根据课程id查询所有联系
     */
    default List<CourseTeacher> selectAllByCourseId(Integer courseId) {
        LambdaQueryWrapper<CourseTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseTeacher::getCourseId,courseId);
        return selectList(queryWrapper);
    }

}
