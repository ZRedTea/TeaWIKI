package com.zredtea.TeaWIKI.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zredtea.TeaWIKI.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
    *** 根据用户名查找用户
     */
    default User selectByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return selectOne(wrapper);
    }

    /**
    *** 根据用户编号查找用户
     */
    default User selectByUserId(Integer userid) {
        return selectById(userid);
    };

    /**
     * 根据用户编号检查用户是否存在
     */
    default Boolean checkByUserId(Integer userid) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, userid);
        return selectOne(wrapper) != null;
    }

    /**
    *** 根据用户名检查用户是否存在
     */
    default Boolean checkByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return selectCount(wrapper) > 0;
    };

    /**
    *** 根据用户名返回用户编号
     */
    default Integer selectIdByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        User user = selectOne(wrapper);
        return user != null ? user.getUserId() : null;
    };

    /**
    *** 返回所有用户列表
     */
    default List<User> selectAllUsers() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(User::getCreatedAt);
        return selectList(wrapper);
    };

    /**
    *** 根据学院返回所有用户列表
     */
    default List<User> selectAllUsersByDepartment(String department) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getDepartment,department);
        wrapper.orderByDesc(User::getCreatedAt);
        return selectList(wrapper);
    };

    /**
    *** 更新用户头像
     */
    default Boolean updateUserAvatar(Integer userid,
                                     String avatar) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserId, userid);
        User user = selectOne(wrapper);
        user.setAvatar(avatar);
        return updateById(user) > 0;
    };

    /**
    *** 更新用户密码
     */
    default Boolean updateUserPassword(Integer userid,
                                       String password, String salt) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserId, userid);
        User user = selectOne(wrapper);
        user.setPassword(password);
        user.setSalt(salt);
        return updateById(user) > 0;
    };


    /**
    *** 更新用户昵称
     */
    default Boolean updateUserNickname(Integer userid,
                                       String nickname) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserId, userid);
        User user = selectOne(wrapper);
        user.setNickname(nickname);
        return updateById(user) > 0;
    };
}
