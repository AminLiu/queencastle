package com.queencastle.dao.model.shop;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.queencastle.dao.model.BaseModel;
import com.queencastle.dao.model.PropertyDict;

/**
 * 产品
 * 
 * @author YuDongwei
 *
 */
public class ShopProduct extends BaseModel {


    private static final long serialVersionUID = 8794472058885012295L;

    /** 产品类别 */
    private PropertyDict category;

    /** 品牌 */
    private ShopBrand brand;

    /** 产品库存 **/
    private Integer amount;

    /** 产品图片 **/
    private List<String> imgs;
    /** 所有图片，之间用逗号隔开 */
    private String images;

    private List<ProductStandard> standards;
    /** 所有规格的编号，中间逗号隔开 */
    private String standardIds;

    public PropertyDict getCategory() {
        return category;
    }


    public void setCategory(PropertyDict category) {
        this.category = category;
    }


    public ShopBrand getBrand() {
        return brand;
    }


    public void setBrand(ShopBrand brand) {
        this.brand = brand;
    }


    public Integer getAmount() {
        return amount;
    }


    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public List<String> getImgs() {
        return imgs;
    }


    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }


    public List<ProductStandard> getStandards() {
        return standards;
    }


    public void setStandards(List<ProductStandard> standards) {
        this.standards = standards;
    }


    public String getImages() {
        if (StringUtils.isNoneBlank(images)) {
            return images;
        }
        if (CollectionUtils.isEmpty(imgs)) {
            return "";
        }
        return StringUtils.join(imgs, ",");
    }


    public void setImages(String images) {
        this.images = images;
    }


    public String getStandardIds() {
        if (StringUtils.isNoneBlank(standardIds)) {
            return standardIds;
        }
        if (CollectionUtils.isEmpty(standards)) {
            return "";
        }
        List<String> ids = new ArrayList<String>();
        for (ProductStandard sstandard : standards) {
            ids.add(sstandard.getId());
        }

        return StringUtils.join(ids, ",");
    }


    public void setStandardIds(String standardIds) {
        this.standardIds = standardIds;
    }



}
