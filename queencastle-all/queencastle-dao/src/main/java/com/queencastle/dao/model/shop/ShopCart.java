package com.queencastle.dao.model.shop;

import java.util.List;

import com.queencastle.dao.model.BaseModel;

/**
 * 购物车
 * 
 * @author YuDongwei
 *
 */
public class ShopCart extends BaseModel {
    private static final long serialVersionUID = -3572745087721537126L;
    /** 购物项 */
    private List<ShopItem> items;
    /** 总额 */
    private double total;

    public List<ShopItem> getItems() {
        return items;
    }

    public void setItems(List<ShopItem> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
