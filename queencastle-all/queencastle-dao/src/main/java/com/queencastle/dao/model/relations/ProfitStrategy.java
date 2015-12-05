package com.queencastle.dao.model.relations;

import com.queencastle.dao.model.BaseModel;

/**
 * 返利策略，定义每一级的返利方式,单位都是分计算的，忽略小数点
 * 
 * @author YuDongwei
 *
 */
public class ProfitStrategy extends BaseModel {

    private static final long serialVersionUID = -373068055464920532L;
    /** 第一级返利类型 */
    private ProfitType firstType;
    /** 第一级返利的百分比或者固定金额 */
    private int firstPoint;
    /** 第二级返利类型 */
    private ProfitType secondType;
    /** 第二级返利的百分比或者固定金额 */
    private int secondPoint;

    public ProfitType getFirstType() {
        return firstType;
    }

    public void setFirstType(ProfitType firstType) {
        this.firstType = firstType;
    }

    public int getFirstPoint() {
        return firstPoint;
    }

    public void setFirstPoint(int firstPoint) {
        this.firstPoint = firstPoint;
    }

    public ProfitType getSecondType() {
        return secondType;
    }

    public void setSecondType(ProfitType secondType) {
        this.secondType = secondType;
    }

    public int getSecondPoint() {
        return secondPoint;
    }

    public void setSecondPoint(int secondPoint) {
        this.secondPoint = secondPoint;
    }



}
