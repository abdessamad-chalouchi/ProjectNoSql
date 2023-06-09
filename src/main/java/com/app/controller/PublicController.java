package com.app.controller;

import com.app.entities.Sport;
import com.app.services.SportServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/public")
public class PublicController {
    private final SportServices sportServices;
    @GetMapping(value="sports",produces={"application/json", "application/xml"})
    public ResponseEntity<List<Sport>> getAllSports(){
        return ResponseEntity.ok(sportServices.getAllSports());
    }
}
