package co.twinkly.picatego.utils.models;

import co.twinkly.picatego.utils.services.database.models.Category;

/**
 * Created by serefbulbul on 24.12.2017.
 */

public class CategoryItem {

    private int id;
    private String name;
    private int interestCount;
    private String imageUrl;

    public CategoryItem() {

    }

    public CategoryItem(Category category, String imageUrl) {
        this.id = category.getId();
        this.name = category.getName();
        this.interestCount = category.getInterestCount();
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInterestCount() {
        return interestCount;
    }

    public void setInterestCount(int interestCount) {
        this.interestCount = interestCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}