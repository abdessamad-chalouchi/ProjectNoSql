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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Document( "client")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JacksonXmlRootElement
public class Client implements UserDetails {

    private String id;

    private Integer codePostal;


    private String rue;


    private String ville;


    private String email;


    private String password;


    private String nom;


    private String prenom;

    private Role role;

    public Client(Integer codePostal, String rue, String ville, String email, String password, String nom, String prenom) {
        this.codePostal = codePostal;
        this.rue = rue;
        this.ville = ville;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
    }

    @Override
    @Schema(accessMode = AccessMode.READ_ONLY)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String mdp) {
        password = mdp;
    }

    @Override
    @Schema(accessMode = AccessMode.READ_ONLY)
    public String getUsername() {
        return email;
    }

    @Override
    @Schema(accessMode = AccessMode.READ_ONLY)
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Schema(accessMode = AccessMode.READ_ONLY)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Schema(accessMode = AccessMode.READ_ONLY)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Schema(accessMode = AccessMode.READ_ONLY)
    public boolean isEnabled() {
        return true;
    }

    @JsonProperty("isAdmin")
    @Schema(accessMode = AccessMode.READ_ONLY)
    public boolean isAdmin() {
//        return role.name().equals("ADMIN");
        return role.equals(Role.ADMIN);
    }
}