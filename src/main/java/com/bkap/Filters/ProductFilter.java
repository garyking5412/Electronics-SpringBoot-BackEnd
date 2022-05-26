package com.bkap.Filters;

import java.io.Serializable;

public class ProductFilter implements Serializable {

    private static final long serialVersionUID=1L;
    private int id;
    private String name;
    private int cateId;

    public ProductFilter(int id, String name, int cateId) {
        this.id = id;
        this.name = name;
        this.cateId = cateId;
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

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }
}
