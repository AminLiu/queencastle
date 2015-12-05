package com.queencastle.service.interf;

import java.util.List;
import java.util.Map;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.RoleInfo;
import com.queencastle.dao.model.User;
import com.queencastle.service.vo.MenuVO;

public interface UserService {
    int insert(User user);

    User getById(String id);

    int updateById(User user);

    PageInfo<User> getUsersByParams(int page, int rows, Map<String, Object> map);

    List<User> getByUsername(String username);

    /**
     * 根据指定用户，获取对应的菜单体系
     * 
     * @param user
     * @return
     */
    List<MenuVO> getMenuVosByUser(User user);

    List<RoleInfo> getRolesByUser(User user);

    boolean setUserAdmin(String userId, boolean flag);

    User loadUserByOpenIdAndSource(String source, String openId);

    List<User> getAllUserIds();
}
