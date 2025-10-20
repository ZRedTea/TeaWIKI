package com.zredtea.TeaWIKI.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zredtea.TeaWIKI.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    /**
     * 根据教师编号进行精确查询
     */
    default Teacher selectByTeacherId(Integer teacherId) {
//        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(Teacher::getTeacherId, teacherId);
//        return selectOne(wrapper);
        return selectById(teacherId);
    }

    /**
     * 获得教师列表
     */
    default List<Teacher> selectAllTeacher() {
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        return selectList(wrapper);
    }

    /**
     * 使用教师名进行模糊查询
     */
    default List<Teacher> matchByTeacherName(String teacherName){
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Teacher::getTeacherName, teacherName);
        wrapper.orderByDesc(Teacher::getCreatedAt);
        return selectList(wrapper);
    }

    /**
     * 根据教师学院进行模糊查询
     */
    default List<Teacher> matchByTeacherDepartment(String department){
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Teacher::getDepartment, department);
        wrapper.orderByDesc(Teacher::getCreatedAt);
        return selectList(wrapper);
    }
}
