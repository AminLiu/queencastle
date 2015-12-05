package com.queencastle.service.interf.relations;

import com.queencastle.dao.model.relations.UserQRCode;

public interface UserQRCodeService {

    int insert(UserQRCode userQRCode);

    UserQRCode getByUserId(String userId);


}
