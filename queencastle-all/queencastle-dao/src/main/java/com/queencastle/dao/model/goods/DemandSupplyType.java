package com.queencastle.dao.model.goods;

/**
 * 供需类型
 * 
 * @author YuDongwei
 *
 */
public enum DemandSupplyType {
    demand, supply;


    public static DemandSupplyType getByName(String name) {
        switch (name) {
            case "demand":
                return demand;
            case "supply":
                return supply;
            default:
                return demand;
        }
    }
}
