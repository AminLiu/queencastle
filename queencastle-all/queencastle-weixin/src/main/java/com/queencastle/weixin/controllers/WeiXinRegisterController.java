package com.queencastle.weixin.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.queencastle.dao.model.SysResourceInfo;
import com.queencastle.dao.model.User;
import com.queencastle.dao.model.UserDetailInfo;
import com.queencastle.dao.utils.DateUtils;
import com.queencastle.dao.utils.MD5Util;
import com.queencastle.dao.utils.jedis.ObjectJedisCache;
import com.queencastle.dao.utils.jedis.StringJedisCache;
import com.queencastle.service.helper.PermissionHelper;
import com.queencastle.service.impl.security.PermissionManage;
import com.queencastle.service.interf.ResourceUploadService;
import com.queencastle.service.interf.SysResourceInfoService;
import com.queencastle.service.interf.UserDetailInfoService;
import com.queencastle.service.interf.UserService;
import com.queencastle.service.sessions.PermissionContext;

@Controller
public class WeiXinRegisterController {
    private static Logger logger = LoggerFactory.getLogger(WeiXinRegisterController.class);
    @Autowired
    private StringJedisCache sessionIdCache;
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectJedisCache sessionContextCache;
    @Autowired
    private ResourceUploadService resourceUploadService;
    @Autowired
    private SysResourceInfoService sysResourceInfoService;
    @Autowired
    private UserDetailInfoService userDetailInfoService;
    @Autowired
    private PermissionManage permissionManage;

    /**
     * 系统中用户名唯一，这里要判断是否重复
     * 
     */
    @ResponseBody
    @RequestMapping("/isRepeated")
    public boolean isRepeated(String username) {
        List<User> users = userService.getByUsername(username);
        if (!users.isEmpty() && users.size() >= 1) {
            return true;
        }
        return false;
    }

    /**
     * 注册和登录非常相似，多了用户信息的录入过程
     * 
     * @param request
     * @param response
     * @param phone
     * @param password
     * @param img
     * @param model
     * @return
     */
    @RequestMapping("/registerTester")
    public String registerTester(HttpServletRequest request, HttpServletResponse response,
            String phone, String password, String cityCode, String provinceCode, @RequestParam(
                    value = "img", required = true) MultipartFile img, Model model) {
        // 如果有过期的session先清理掉
        PermissionHelper.clearSessionIdFromCookie(request, response, sessionIdCache);
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(password) || img == null) {
            String errorMessage = "注册信息不全!";
            model.addAttribute("info", errorMessage);
            return "weixinregister";
        } else {
            // 判断用户名是否重复，虽然发生意外请求的概率非常低，但是还是需要验证
            if (isRepeated(phone)) {
                String errorMessage = "您注册的用户名已存在!";
                model.addAttribute("info", errorMessage);
                return "weixinregister";
            } else {
                // 上传微信

                try {
                    // 加密用户密码
                    password = MD5Util.MD5Encode(password);
                    User user = new User();
                    user.setUsername(phone);
                    user.setPassword(password);
                    user.setSource("weixin");
                    user.setOutNick(phone);
                    user.setPhone(phone);
                    userService.insert(user);
                    String fileName = saveUserImgs(img, user.getId());
                    UserDetailInfo udi = new UserDetailInfo();
                    udi.setUserId(user.getId());
                    if (StringUtils.isNoneBlank(fileName)) {
                        udi.setImg(fileName);
                    }
                    udi.setCityCode(cityCode);
                    udi.setProvinceCode(provinceCode);
                    userDetailInfoService.insert(udi);

                    PermissionHelper.generateLoginedSession(request, response, user,
                            sessionIdCache, sessionContextCache);
                    PermissionContext.setUser(user);
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }


        return "/makeup";
    }

    @RequestMapping("/makeUpInfo")
    public String makeUpInfo(HttpServletRequest request, HttpServletResponse response,
            String phone, String password, String cityCode, String provinceCode, @RequestParam(
                    value = "img", required = true) MultipartFile img, Model model) {
        User user = PermissionContext.getUser();
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(password) || img == null) {
            String errorMessage = "注册信息不全!";
            model.addAttribute("info", errorMessage);
            return "makeup";
        } else {
            // 判断用户名是否重复，虽然发生意外请求的概率非常低，但是还是需要验证
            if (isRepeated(phone)) {
                String errorMessage = "您注册的用户名已存在!";
                model.addAttribute("info", errorMessage);
                return "makeup";
            } else {
                try {
                    // 加密用户密码
                    password = MD5Util.MD5Encode(password);
                    user.setUsername(phone);
                    user.setPassword(password);
                    user.setPhone(phone);
                    // 更新用户基本信息
                    logger.error(user.getId());
                    userService.updateById(user);
                    //
                    String fileName = saveUserImgs(img, user.getId());
                    UserDetailInfo udi = userDetailInfoService.getByUserId(user.getId());
                    if (StringUtils.isNoneBlank(fileName)) {
                        udi.setImg(fileName);
                    }
                    udi.setCityCode(cityCode);
                    udi.setProvinceCode(provinceCode);
                    // 更新用户详细信息
                    userDetailInfoService.updateById(udi);
                    // 如果用户是登录状态，更新缓存数据
                    String sessionId = permissionManage.getSessionId(request);
                    permissionManage.updateUserSessionCache(sessionId, user);
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }



        return "redirect:/memberGuide";
    }


    private String saveUserImgs(MultipartFile img, String userId) throws IOException {
        byte[] bytes = img.getBytes();
        if (bytes.length > 0) {
            String originName = img.getOriginalFilename();
            String fileName = DateUtils.getCurrFullTime() + "_" + originName;
            String key = resourceUploadService.uploadBytes(bytes, fileName);
            if (StringUtils.isNoneBlank(key)) {
                SysResourceInfo info = new SysResourceInfo();
                info.setFileKey(key);
                info.setFileName(fileName);
                info.setOriginName(originName);
                String ext = originName.substring((originName.lastIndexOf(".") + 1));
                info.setFileExt(ext);
                info.setUserId(userId);
                sysResourceInfoService.insert(info);
            }

            return fileName;
        }
        return null;
    }



}
