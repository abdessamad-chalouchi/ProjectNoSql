package com.app.entities;

import java.io.Serializable;

import lombok.Data;

@Data
public class CarnetUpdate implements Serializable {
    private String id;
    private int nombreEntrees;
    public String getClient() {
        return (id.split("-")[0]);
    }
    public String getSport() {
        return (id.split("-")[1]);
    }
}