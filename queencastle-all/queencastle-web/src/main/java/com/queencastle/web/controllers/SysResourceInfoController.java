package com.queencastle.web.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.queencastle.dao.model.SysResourceInfo;
import com.queencastle.dao.model.User;
import com.queencastle.dao.utils.DateUtils;
import com.queencastle.service.interf.ResourceUploadService;
import com.queencastle.service.sessions.PermissionContext;

@Controller
public class SysResourceInfoController {
    @Autowired
    protected ResourceUploadService resourceUploadService;

    @RequestMapping("/fileUpload")
    public int fileUpload(List<MultipartFile> files) {
        User currentUser = PermissionContext.getUser();
        if (currentUser == null) {
            return 0;
        }
        if (!CollectionUtils.isEmpty(files)) {
            for (MultipartFile file : files) {

                try {
                    byte[] bytes = file.getBytes();
                    //
                    String key = resourceUploadService.uploadBytes(bytes, null);
                    if (StringUtils.isNoneBlank(key)) {
                        SysResourceInfo info = new SysResourceInfo();
                        info.setFileKey(key);
                        String originName = file.getOriginalFilename();
                        info.setFileName(DateUtils.getCurrFullTime() + "_" + originName);
                        info.setOriginName(originName);
                        String ext = originName.substring((originName.lastIndexOf(".") + 1));
                        info.setFileExt(ext);
                        info.setUserId(currentUser.getId());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }
}
