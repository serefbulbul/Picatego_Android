package co.twinkly.picatego.utils.services.database.models;

/**
 * Created by serefbulbul on 21.12.2017.
 */

public class Category {

    private int id;
    private String name;
    private int interestCount;

    public Category() {

    }

    public Category(String name, int interestCount) {
        this.name = name;
        this.interestCount = interestCount;
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
}
