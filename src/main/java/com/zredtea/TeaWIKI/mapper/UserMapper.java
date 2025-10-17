package com.zredtea.TeaWIKI.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zredtea.TeaWIKI.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /*
    *** 根据用户名查找用户
     */
    User SelectByUsername(@Param("username") String username);

    /*
    *** 根据用户编号查找用户
     */
    User SelectByUserId(@Param("userid")  Integer userid);

    /*
    *** 根据用户名检查用户是否存在
     */
    Integer CheckByUsername(@Param("username") String username);

    /*
    *** 返回所有用户列表
     */
    List<User> SelectAllUsers();

    /*
    *** 根据学院返回所有用户列表
     */
    List<User> SelectAllUsersByDepartment(@Param("department") String department);

    /*
    *** 更新用户头像
     */
    void updateUserAvatar(@Param("userid") Integer userid,
                          @Param("avatar") String avatar);

    /*
    *** 更新用户密码
     */
    void updateUserPassword(@Param("userid") Integer userid,
                            @Param("password") String password,
                            @Param("salt") String salt);

    /*
    *** 更新用户昵称
     */
    void updateUserNickname(@Param("userid") Integer userid,
                            @Param("nickname") String nickname);
}
