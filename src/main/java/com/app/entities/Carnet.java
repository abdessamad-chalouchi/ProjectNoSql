package com.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document("carnets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JacksonXmlRootElement
@ToString
public class Carnet {

    private CarnetId id;
    @JsonIgnore
    @DocumentReference
    @ToString.Exclude
    private Client idClient;

    @DocumentReference
    @ToString.Exclude
    @JsonIgnore
    private Sport idSport;

    private Integer nombreEntrees;

    @JsonProperty("sportName")
    public String getSport() {
        return idSport.getNom();
    }

    @JsonProperty("sport")
    public String getSportId() {
        return idSport.getId();
    }

    @JsonProperty("username")
    public String getUserName() {
        return idClient.getEmail();
    }

    @JsonProperty("client")
    public String getUserId() {
        return idClient.getId();
    }

}