package com.queencastle.dao.model.shop;

import com.queencastle.dao.model.BaseModel;

/**
 * 产品规格
 * 
 * @author YuDongwei
 *
 */
public class ProductStandard extends BaseModel {
    private static final long serialVersionUID = -7381909051272329466L;

    /** 颜色 */
    private String colour;

    /** 尺码 */
    private String size;

	/** 图片 */
    private String img;

    /** 产品价格 **/
    private float price;

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }



}
