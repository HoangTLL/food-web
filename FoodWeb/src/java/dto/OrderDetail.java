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
public class OrderDetail {

    private int detailId;
    private int orderId;
    private int dishId;
    private int quantity;
    private int price_per_unit;
    private boolean item_type;

    private String itemName;
    private String category;

    public OrderDetail() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public OrderDetail(int detailId, int orderId, String itemName, int quantity, int pricePerUnit, String category) {
        this.detailId = detailId;
        this.orderId = orderId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price_per_unit = pricePerUnit;
        this.category = category;
    }

    public OrderDetail(int detailId, int orderId, int dishId, int quantity, int price_per_unit, boolean item_type) {
        this.detailId = detailId;
        this.orderId = orderId;
        this.dishId = dishId;
        this.quantity = quantity;
        this.price_per_unit = price_per_unit;
        this.item_type = item_type;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice_per_unit() {
        return price_per_unit;
    }

    public void setPrice_per_unit(int price_per_unit) {
        this.price_per_unit = price_per_unit;
    }

    public boolean isItem_type() {
        return item_type;
    }

    public void setItem_type(boolean item_type) {
        this.item_type = item_type;
    }

}
