package com.app.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TerrainData implements Serializable {
    private String id;
    private String code;
    private String surface;
    private List<String> sports = new ArrayList<>();
}