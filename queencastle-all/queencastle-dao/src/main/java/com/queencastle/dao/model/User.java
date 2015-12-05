package com.queencastle.dao.model;

/**
 * 系统用户
 * 
 * @author YuDongwei
 *
 */
public class User extends BaseModel {

    private static final long serialVersionUID = 425283391548977376L;
    /** 电话号码 */
    private String phone;
    /** 用户名,全局唯一 */
    private String username;
    /** 密码 */
    private String password;
    /** 来源 */
    private String source;
    /** 在来源中的昵称 */
    private String outNick;
    /** 外部编码 */
    private String openId;
    /** 性别 */
    private String sex;
    /** 头像 */
    private String headImg;
    /** 是否是系统管理员 */
    private boolean admin;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOutNick() {
        return outNick;
    }

    public void setOutNick(String outNick) {
        this.outNick = outNick;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }



}
