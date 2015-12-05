package com.queencastle.dao.model.shop;

import com.queencastle.dao.model.BaseModel;

/**
 * 
 * @author YuDongwei
 *
 */
public class OrderItem extends BaseModel {

    private static final long serialVersionUID = 2152115222431323907L;
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
