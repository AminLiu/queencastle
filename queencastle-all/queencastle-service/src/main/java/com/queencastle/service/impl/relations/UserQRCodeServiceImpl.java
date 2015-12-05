package com.queencastle.service.impl.relations;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queencastle.dao.mapper.relations.UserQRCodeMapper;
import com.queencastle.dao.model.relations.UserQRCode;
import com.queencastle.dao.utils.jedis.ObjectJedisCache;
import com.queencastle.service.interf.relations.UserQRCodeService;

@Service
public class UserQRCodeServiceImpl implements UserQRCodeService {
    @Autowired
    private UserQRCodeMapper userQRCodeMapper;
    @Autowired
    private ObjectJedisCache qrcodeCache;

    @Override
    public int insert(UserQRCode userQRCode) {
        return userQRCodeMapper.insert(userQRCode);
    }

    @Override
    public UserQRCode getByUserId(String userId) {
        if (StringUtils.isNoneBlank(userId)) {
            UserQRCode userQRCode = (UserQRCode) qrcodeCache.getObj(userId);
            if (userQRCode == null) {
                userQRCode = userQRCodeMapper.getByUserId(userId);
            }
            return userQRCode;
        }
        return null;
    }

}
