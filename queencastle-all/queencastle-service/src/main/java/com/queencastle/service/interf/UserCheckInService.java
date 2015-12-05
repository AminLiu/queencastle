package com.queencastle.service.interf;

import java.util.List;

import com.queencastle.dao.model.UserCheckIn;

/**
 * 用户签到服务
 * 
 * @author YuDongwei
 *
 */
public interface UserCheckInService {

    int insert(UserCheckIn userCheckIn);

    List<UserCheckIn> getByUserId(String userId);
}
