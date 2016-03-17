package shared.model;

// Java Imports
import java.util.ArrayList;
import java.util.List;

/**
 * The ShopItem class is used to store information about an individual item
 * being sold in the Shop.
 */
public class ShopItem {

    private int item_id;
    private int level;
    private String name;
    private int price;
    private String description;
    private List<String> extraArgs;
    private List<String> categoryList;
    private List<String> tagList;

    public ShopItem(int item_id, int level, String name, String description, int price) {
        this.item_id = item_id;
        this.level = level;
        this.name = name;
        this.description = description;
        this.price = price;

        extraArgs = new ArrayList<String>();
        categoryList = new ArrayList<String>();
        tagList = new ArrayList<String>();
    }

    public int getID() {
        return item_id;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getExtraArgs() {
        return extraArgs;
    }

    public List<String> setExtraArgs(List<String> extraArgs) {
        return this.extraArgs = extraArgs;
    }

    public List<String> getCategoryList() {
        return categoryList;
    }

    public String getCategoryListAsString() {
        String result = "";

        for (String category : categoryList) {
            result += category;

            if (categoryList.indexOf(category) < categoryList.size()) {
                result += ", ";
            }
        }

        return result;
    }

    public List<String> setCategoryList(List<String> categoryList) {
        return this.categoryList = categoryList;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public String getTagListAsString() {
        String result = "";

        for (String tag : tagList) {
            result += tag;

            if (tagList.indexOf(tag) < tagList.size()) {
                result += ", ";
            }
        }

        return result;
    }

    public List<String> setTagList(List<String> tagList) {
        return this.tagList = tagList;
    }
}
