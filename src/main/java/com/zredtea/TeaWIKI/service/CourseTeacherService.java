package com.zredtea.TeaWIKI.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zredtea.TeaWIKI.DTO.request.CourseTeacher.CourseTeacherCommitDTO;
import com.zredtea.TeaWIKI.DTO.request.CourseTeacher.CourseTeacherDeleteDTO;
import com.zredtea.TeaWIKI.entity.Course;
import com.zredtea.TeaWIKI.entity.CourseTeacher;
import com.zredtea.TeaWIKI.entity.Teacher;

import java.util.List;

public interface CourseTeacherService extends IService<CourseTeacher> {
    boolean commitCTConnect(CourseTeacherCommitDTO dto);

    boolean cancelCTConnect(CourseTeacherDeleteDTO dto);

    List<Teacher> getTeachersByCourseId(Integer courseId);

    List<Course> getCoursesByTeacherId(Integer teacherId);

    boolean isCTConnectExist(Integer teacherId, Integer courseId);
}
