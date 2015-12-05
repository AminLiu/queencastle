package com.queencastle.dao.model.goods;

import com.queencastle.dao.model.BaseModel;

/**
 * 产品信息，这里没有其他的信息如：价格，库存等，设置在这里耦合性太强，如果制定类似价格区间的策略，不宜在这里定义
 * 
 * @author YuDongwei
 *
 */
public class Product extends BaseModel {

    private static final long serialVersionUID = 3601895585537229495L;
    /** 产品类别 */
    private Category category;
    /** 产品名称 */
    private String cname;
    /** 产品名称全拼 */
    private String ename;
    /** 产品介绍 */
    private String intro;
    /** 产品图片 */
    private String imgs;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }



}
