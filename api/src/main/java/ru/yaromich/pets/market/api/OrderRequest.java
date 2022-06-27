package ru.yaromich.pets.market.api;

public class OrderRequest {
    private String address;
    private String phone;

    public OrderRequest(String address, String phone) {
        this.address = address;
        this.phone = phone;
    }

    public OrderRequest() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
