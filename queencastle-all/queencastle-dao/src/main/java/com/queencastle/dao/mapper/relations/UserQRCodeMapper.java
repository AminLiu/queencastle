package com.queencastle.dao.mapper.relations;

import org.apache.ibatis.annotations.Param;

import com.queencastle.dao.model.relations.UserQRCode;

public interface UserQRCodeMapper {

    int insert(UserQRCode userQRCode);

    UserQRCode getByUserId(@Param("userId") String userId);

}
