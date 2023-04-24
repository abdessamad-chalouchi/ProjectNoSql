package com.app.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.Instant;

@Document("messages")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JacksonXmlRootElement
public class Message implements Serializable {

    private String id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant dateEcriture;


    private String message;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "id_client", nullable = false)
    @DocumentReference
    private Client idClient;

    @JsonIgnore
    public Client getIdClient() {
        return idClient;
    }

    @JsonProperty("From")
    public String getFrom() {
        return idClient.getPrenom() + " " + idClient.getNom();
    }

    public Message(String msg,Client client) {
        this.dateEcriture = Instant.now();
        this.message = msg;
        this.idClient = client;
    }

}