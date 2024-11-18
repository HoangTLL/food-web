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
public class MealPlanItem {

    private int accid;
    private Item item;
    private boolean status;

    public MealPlanItem() {
    }

    public MealPlanItem(int accid, Item item, boolean status) {
        this.accid = accid;
        this.item = item;
        this.status = status;
    }

    public int getAccid() {
        return accid;
    }

    public void setAccid(int accid) {
        this.accid = accid;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
