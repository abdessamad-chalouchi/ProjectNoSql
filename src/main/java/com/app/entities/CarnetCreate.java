package com.app.entities;

import java.io.Serializable;

import lombok.Data;

@Data
public class CarnetCreate implements Serializable{
    private String client;
    private String sport;
    private int nombreEntrees;
}