package com.project99x.driw.DTO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ProductDTO {

    private long id;
    private String uuid;
    private String name;
    private int unitPurchased;
    private float total;


    public ProductDTO() {
    }

    public ProductDTO(long id, String uuid, String name, int unitPurchased) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.unitPurchased = unitPurchased;
    }

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

    public int getUnitPurchased() {
        return unitPurchased;
    }

    public void setUnitPurchased(int unitPurchased) {
        this.unitPurchased = unitPurchased;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
