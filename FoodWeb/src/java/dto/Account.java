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
public class Account {

    private int id;
    private String fullName;
    private String password;
    private String email;
    private String phone;
    private String address;
    private int wardId;
    private int districtId;
    private int cityId;
    private String role;
    private boolean status;

    public Account() {
    }
    //Password
    public Account(int id, String fullName, String password, String email, String phone, String address, int wardId, int districtId, int cityId, String role, boolean status) {
        this.id = id;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.wardId = wardId;
        this.districtId = districtId;
        this.cityId = cityId;
        this.role = role;
        this.status = status;
    }

    //No-password
    public Account(int id, String fullName, String email, String phone, String address, int wardId, int districtId, int cityId, String role, boolean status) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.wardId = wardId;
        this.districtId = districtId;
        this.cityId = cityId;
        this.role = role;
        this.status = status;
    }
    
    //No-password-id
    public Account(String fullName, String email, String phone, String address, int wardId, int districtId, int cityId, String role, boolean status) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.wardId = wardId;
        this.districtId = districtId;
        this.cityId = cityId;
        this.role = role;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
