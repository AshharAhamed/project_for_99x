package com.project99x.driw.Entities;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String uuid;
    private String name;

    @Column(name = "unit_per_carton")
    private int unitPerCarton;

    private int stock;

    @Column(name = "cartonPrice")
    private float cartonPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnitPerCarton() {
        return unitPerCarton;
    }

    public void setUnitPerCarton(int unitPerCarton) {
        this.unitPerCarton = unitPerCarton;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getCartonPrice() {
        return cartonPrice;
    }

    public void setCartonPrice(float cartonPrice) {
        this.cartonPrice = cartonPrice;
    }
}
