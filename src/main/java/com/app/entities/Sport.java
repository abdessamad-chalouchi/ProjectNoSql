package com.app.entities;

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

import java.util.HashSet;
import java.util.Set;


@Document("sports")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JacksonXmlRootElement
public class Sport {

    @Schema(accessMode = AccessMode.READ_ONLY)
    private String id;


    private String nom;


    private Integer nombreJoueurs;

    // @Column(name = "sports_terrain", nullable = false)
    // @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
//    @ManyToMany
//    @JoinTable(
//        name = "sports_terrains",
//        joinColumns = @JoinColumn(name = "identifant_sport"),
//        inverseJoinColumns = @JoinColumn(name = "identifiant_terrain"))
    @DocumentReference
    private Set<Terrain> terrains = new HashSet<>();

    @JsonProperty("terrains")
    @Schema(accessMode = AccessMode.READ_ONLY)
    public Set<String> getTerrainsList() {
        Set<String> ret = new HashSet<>();
        for (Terrain terrain : terrains) {
            ret.add(terrain.getCode());
        }
        return ret;
    }

}