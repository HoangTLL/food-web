/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;


/**
 *
 * @author htduy
 */
public class Item {

    private int id;
    private String name;
    private int price;
    private boolean status;
    private String desc;
    private String category;
    private int calories;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String recipe;

    public Item() {
    }

    // meal plan
    public Item(int id, String name, int price, boolean status, String category, String image1) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.category = category;
        this.image1 = image1;
    }
    
    // dishes and drinks
    public Item(int id, String name, int price, boolean status, String desc, String category, int calories, String image1, String recipe) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.desc = desc;
        this.category = category;
        this.calories = calories;
        this.image1 = image1;
        this.recipe = recipe;
    }
    
    // menus
    public Item(int id, String name, int price, boolean status, String desc, String category, int calories, String image1, String image2, String image3, String image4) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.desc = desc;
        this.category = category;
        this.calories = calories;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

}
