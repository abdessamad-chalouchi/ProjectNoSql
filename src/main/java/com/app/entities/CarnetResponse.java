package com.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarnetResponse {
    String id;
    private Client idClient;
    private Sport idSport;
    private Integer nombreEntrees;
    private String sportName;
    private String sport;
    private String username;
    private String client;
}
