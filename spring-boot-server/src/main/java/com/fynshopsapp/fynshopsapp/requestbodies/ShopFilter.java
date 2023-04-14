package com.fynshopsapp.fynshopsapp.requestbodies;

public class ShopFilter {
    private String areaFilter;
    private String categoryFilter;
    private String openCloseFilter;

    public String getAreaFilter() {
        return areaFilter;
    }

    public void setAreaFilter(String areaFilter) {
        this.areaFilter = areaFilter;
    }

    public String getCategoryFilter() {
        return categoryFilter;
    }

    public void setCategoryFilter(String categoryFilter) {
        this.categoryFilter = categoryFilter;
    }

    public String getOpenCloseFilter() {
        return openCloseFilter;
    }

    public void setOpenCloseFilter(String openCloseFilter) {
        this.openCloseFilter = openCloseFilter;
    }

    public ShopFilter(String areaFilter, String categoryFilter, String openCloseFilter) {
        this.areaFilter = areaFilter;
        this.categoryFilter = categoryFilter;
        this.openCloseFilter = openCloseFilter;
    }
}
