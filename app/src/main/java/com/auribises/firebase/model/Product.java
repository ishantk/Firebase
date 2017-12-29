package com.auribises.firebase.model;

/**
 * Created by ishantkumar on 29/12/17.
 */

public class Product {

    public int pid;
    String uid;
    public String name;
    public int price;

    public Product() {
    }

    public Product(int pid, String uid, String name, int price) {
        this.pid = pid;
        this.uid = uid;
        this.name = name;
        this.price = price;
    }
}
