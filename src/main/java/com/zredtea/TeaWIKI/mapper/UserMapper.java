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
    default User SelectByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return selectOne(queryWrapper);
    }

    /**
    *** 根据用户编号查找用户
     */
    default User SelectByUserId(Integer userid) {
        return selectById(userid);
    };

    /**
    *** 根据用户名检查用户是否存在
     */
    default Boolean CheckByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return selectCount(queryWrapper) > 0;
    };

    /**
    *** 根据用户名返回用户编号
     */
    default Integer SelectIdByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = selectOne(queryWrapper);
        return user != null ? user.getUserId() : null;
    };

    /**
    *** 返回所有用户列表
     */
    default List<User> SelectAllUsers() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(User::getCreatedAt);
        return selectList(wrapper);
    };

    /**
    *** 根据学院返回所有用户列表
     */
    default List<User> SelectAllUsersByDepartment(String department) {
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
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userid);
        User user = selectOne(queryWrapper);
        user.setAvatar(avatar);
        return updateById(user) > 0;
    };

    /**
    *** 更新用户密码
     */
    default Boolean updateUserPassword(Integer userid,
                                    String password, String salt) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userid);
        User user = selectOne(queryWrapper);
        user.setPassword(password);
        user.setSalt(salt);
        return updateById(user) > 0;
    };


    /**
    *** 更新用户昵称
     */
    default Boolean updateUserNickname(Integer userid,
                                       String nickname) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userid);
        User user = selectOne(queryWrapper);
        user.setNickname(nickname);
        return updateById(user) > 0;
    };
}
