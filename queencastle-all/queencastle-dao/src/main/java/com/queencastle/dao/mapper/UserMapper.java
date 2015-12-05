package com.queencastle.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.queencastle.dao.model.User;

public interface UserMapper {
    User getById(@Param("id") String id);

    int insert(User user);

    int updateById(User user);

    List<User> getUsersByParams(@Param("page") Pageable pageable,
            @Param("map") Map<String, Object> map);

    Integer getUsersCountByParams(@Param("map") Map<String, Object> map);

    List<User> getByUsername(@Param("username") String username);

    int setUserAdmin(@Param("userId") String userId, @Param("flag") boolean flag);

    User loadUserByOpenIdAndSource(@Param("source") String source, @Param("openId") String openId);

    List<User> getAllUserIds();

}
