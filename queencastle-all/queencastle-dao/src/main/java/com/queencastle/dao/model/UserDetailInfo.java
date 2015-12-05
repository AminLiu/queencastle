package com.queencastle.dao.model;

/**
 * 用户详情，不和User类进行合并，保持前者的简洁，如果需要所有信息两个类进行整合
 * 
 * @author YuDongwei
 *
 */
public class UserDetailInfo extends BaseModel {

    private static final long serialVersionUID = 3182332374349531637L;
    private String userId;
    /** 微信二维码图片 */
    private String img;
    /** 所在地区城市编码 */
    private String cityCode;
    /** 所在地区省编码 */
    private String provinceCode;
    /** 微信标注所在国家 */
    private String country;
    /** 微信标注所在省 */
    private String province;
    /** 微信标注所在城市 */
    private String city;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }



}
