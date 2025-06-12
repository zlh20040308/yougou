package com.fengtdi.mapper;


import com.fengtdi.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO users (username) VALUES (#{username})")
    int insertUser(User user);


    List<User> selectAllUsers();

    @Select("SELECT id, username, password, role_id , created_at FROM users WHERE username = #{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO users (username, password, role_id, created_at) " +
            "VALUES (#{username}, #{password}, #{roleId}, NOW())")
    void insert(User user);

    @Select("SELECT id, username, password, role_id , created_at FROM users WHERE username = #{username} and password = #{password}")
    User selectByUsernameAndPassword(User user);

    @Select("SELECT * FROM users WHERE role_id = #{roleId}")
    List<User> selectByRoleId(int roleId);

    @Delete("DELETE FROM users WHERE id = #{userId}")
    int deleteById(Integer userId);
}
