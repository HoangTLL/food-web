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
public class Ingredient {

    private int id;
    private String name;
    private float quantity;
    private String unit;
    private boolean status;

    public Ingredient() {
    }

    public Ingredient(String name, float quantity, String unit, boolean status) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.status = status;
    }

    public Ingredient(int id, String name, float quantity, String unit, boolean status) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.status = status;
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

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
