/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Date;

/**
 *
 * @author Tan Phat
 */
public class Order {

    private int orderId;
    private String accountName;
    private Date orderDate;
    private Date shipDate;
    private String shipAddress;
    private String shipCity;
    private String shipDistrict;
    private String shipWard;
    private double totalPrice;
    private String customerNote;
    private int status;

    public Order() {
    }

    public Order(int orderId, String accountName, Date orderDate, Date shipDate, String shipAddress, String shipCity, String shipDistrict, String shipWard, double totalPrice, String customerNote, int status) {
        this.orderId = orderId;
        this.accountName = accountName;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.shipAddress = shipAddress;
        this.shipCity = shipCity;
        this.shipDistrict = shipDistrict;
        this.shipWard = shipWard;
        this.totalPrice = totalPrice;
        this.customerNote = customerNote;
        this.status = status;
    }
    
    public Order(String accountName, Date orderDate, Date shipDate, String shipAddress, String shipCity, String shipDistrict, String shipWard, double totalPrice, String customerNote, int status) {
        this.accountName = accountName;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.shipAddress = shipAddress;
        this.shipCity = shipCity;
        this.shipDistrict = shipDistrict;
        this.shipWard = shipWard;
        this.totalPrice = totalPrice;
        this.customerNote = customerNote;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date oderDate) {
        this.orderDate = oderDate;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipDistrict() {
        return shipDistrict;
    }

    public void setShipDistrict(String shipDistrict) {
        this.shipDistrict = shipDistrict;
    }

    public String getShipWard() {
        return shipWard;
    }

    public void setShipWard(String shipWard) {
        this.shipWard = shipWard;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
