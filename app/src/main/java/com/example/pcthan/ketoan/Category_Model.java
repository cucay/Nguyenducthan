package com.example.pcthan.ketoan;

public class Category_Model {
    private String category_icon_link;
    private String categoryName;

    public Category_Model(String category_icon_link, String categoryName) {
        this.category_icon_link = category_icon_link;
        this.categoryName = categoryName;
    }

    public String getCategory_icon_link() {
        return category_icon_link;
    }

    public void setCategory_icon_link(String category_icon_link) {
        this.category_icon_link = category_icon_link;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
