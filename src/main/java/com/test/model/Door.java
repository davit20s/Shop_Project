package com.test.model;

import javax.persistence.*;

@Entity
public class Door {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String color;

    private double width;

    private double height;

//    private int shop_id;

    @JoinColumn(name = "shop_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Shop shop;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }



    @Override
    public String toString() {
        return "Door{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", shop=" + shop +
                '}';
    }
}
