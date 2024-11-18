/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.List;

/**
 *
 * @author htduy
 */
public class Menu {

    private int id;
    private int accId;
    private String name;
    private int totalPrice;
    private int period;
    private int dayOfWeek;
    private boolean status;
    private String description;
    private String category;
    private int totalCalories;
    private List<String> images;

    public Menu() {
    }

    // getMenuId
    public Menu(int id, String name, int totalPrice, int period, int dayOfWeek, boolean status, String description, String category, int totalCalories, List<String> images) {
        this.id = id;
        this.name = name;
        this.totalPrice = totalPrice;
        this.status = status;
        this.period = period;
        this.dayOfWeek = dayOfWeek;
        this.description = description;
        this.category = category;
        this.totalCalories = totalCalories;
        this.images = images;
    }

    public Menu(int id, String name, int totalPrice, boolean status, String description, String category, int totalCalories, List<String> images) {
        this.id = id;
        this.name = name;
        this.totalPrice = totalPrice;
        this.status = status;
        this.description = description;
        this.category = category;
        this.totalCalories = totalCalories;
        this.images = images;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

}
