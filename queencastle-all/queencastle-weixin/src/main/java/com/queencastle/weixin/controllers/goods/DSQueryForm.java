package com.queencastle.weixin.controllers.goods;


public class DSQueryForm {
    private String type;
    private String timeSearch;
    private String priceSearch;
    private String categoryId;
    private String attentionSearch;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimeSearch() {
        return timeSearch;
    }

    public void setTimeSearch(String timeSearch) {
        this.timeSearch = timeSearch;
    }

    public String getPriceSearch() {
        return priceSearch;
    }

    public void setPriceSearch(String priceSearch) {
        this.priceSearch = priceSearch;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getAttentionSearch() {
        return attentionSearch;
    }

    public void setAttentionSearch(String attentionSearch) {
        this.attentionSearch = attentionSearch;
    }



}
