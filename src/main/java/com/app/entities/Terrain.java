package com.app.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document("terrains")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JacksonXmlRootElement
public class Terrain {

    @Schema(accessMode = AccessMode.READ_ONLY)
    private String id;


    private String code;


    private String surface;

    @JsonIgnore
//    @ManyToMany(mappedBy = "terrains")
    @DocumentReference
    private Set<Sport> sports = new HashSet<>();

    @JsonProperty("sportsNames")
    @Schema(accessMode = AccessMode.READ_ONLY)
    public Set<String> getSportsList() {
        Set<String> ret = new HashSet<>();
        for (Sport sport : sports) {
            ret.add(sport.getNom());
        }
        return ret;
    }

    @JsonProperty("sports")
    @Schema(accessMode = AccessMode.READ_ONLY)
    public Set<String> getSportsIDsList() {
        Set<String> ret = new HashSet<>();
        for (Sport sport : sports) {
            ret.add(sport.getId());
        }
        return ret;
    }
}