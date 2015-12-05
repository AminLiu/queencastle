package com.queencastle.dao.model.shop;

import com.queencastle.dao.model.BaseModel;

/**
 * 购物项
 * 
 * @author YuDongwei
 *
 */
public class ShopItem extends BaseModel {

    private static final long serialVersionUID = 3433473843013053726L;
    /** 购买的产品 */
    private ShopProduct shopProduct;
    /** 购买产品的数量 */
    private int amount;

    public ShopProduct getShopProduct() {
        return shopProduct;
    }

    public void setShopProduct(ShopProduct shopProduct) {
        this.shopProduct = shopProduct;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }



}
