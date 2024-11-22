package org.pharmacy;

import java.time.LocalDate;

public class Medicine {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private LocalDate expiry_date;

    public Medicine(int id, String name, int quantity) {
        this.id=id;
        this.name=name;
        this.quantity=quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(LocalDate expiry_date) {
        this.expiry_date = expiry_date;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Medicine(int id, String name, double price, int quantity, LocalDate expiry_date){
        this.id=id;
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.expiry_date=expiry_date;
    }
    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", expiryDate=" + expiry_date +
                '}';
    }


}
