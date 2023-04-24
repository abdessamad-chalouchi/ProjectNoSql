package com.app.controller;

import com.app.entities.*;
import com.app.services.CarnetService;
import com.app.services.SortService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin/carnet")
public class CarnetController {
    private final SortService sService;
    private final CarnetService carnetService;
    @PostMapping(produces = {"application/json", "application/xml"},consumes = {"application/json", "application/xml"})
    public Carnet createCarnet(@RequestBody CarnetCreate carnet) {
        return carnetService.createCarnet(carnet);
    }
    @PutMapping(produces = {"application/json", "application/xml"},consumes = {"application/json", "application/xml"})
    public Carnet updateCarnet(@RequestBody CarnetUpdate carnet) {
        return carnetService.updateCarnet(carnet);
    }
    @GetMapping(value={"/{idClient}-{idSport}","/{idClient}/{idSport}"}, produces = {"application/json", "application/xml"})
    public CarnetResponse getCarnetById(@PathVariable String idClient, @PathVariable String idSport) {
        CarnetId id = new CarnetId(idClient, idSport);
        Carnet carnet = carnetService.getCarnetById(id).get();
        return new CarnetResponse(String.join("-", Arrays.asList(carnet.getIdClient().getId(), carnet.getIdSport().getId())),carnet.getIdClient(),carnet.getIdSport(),carnet.getNombreEntrees(),carnet.getSport(), carnet.getSportId(),carnet.getUserName(),carnet.getUserId() );
    }
    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<List<CarnetResponse>> getAllCarnets(
        @RequestParam(defaultValue = "1") int _page,
        @RequestParam(defaultValue = "10") int _size,
        @RequestParam(required = false) String[] _sort,
        @RequestParam(required = false) String[] _order
    ) {
        try {
            HttpHeaders responseHeaders = new HttpHeaders();
            Pageable paging = sService.getSorter(_page, _size, _sort, _order);

            if(paging == null) {
                List<CarnetResponse> carnetResponses = new ArrayList<>();
                for (Carnet carnet:
                        carnetService.getAllCarnets()) {
                    carnetResponses.add(new CarnetResponse(String.join("-", Arrays.asList(carnet.getIdClient().getId(), carnet.getIdSport().getId())),carnet.getIdClient(),carnet.getIdSport(),carnet.getNombreEntrees(),carnet.getSport(), carnet.getSportId(),carnet.getUserName(),carnet.getUserId() ));
                }
                return ResponseEntity.ok(carnetResponses);
            }
            List<CarnetResponse> carnetResponses = new ArrayList<>();
            Page<Carnet> pageCarnets = carnetService.getAllCarnets(paging);
            List<Carnet> carnets = pageCarnets.getContent();
            for (Carnet carnet:
                    carnets) {
                carnetResponses.add(new CarnetResponse(String.join("-", Arrays.asList(carnet.getIdClient().getId(), carnet.getIdSport().getId())),carnet.getIdClient(),carnet.getIdSport(),carnet.getNombreEntrees(),carnet.getSport(), carnet.getSportId(),carnet.getUserName(),carnet.getUserId() ));
            }
            responseHeaders.add("x-total-count",String.valueOf(pageCarnets.getTotalElements()));
            return ResponseEntity.ok().headers(responseHeaders).body(carnetResponses);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public void deleteCarnetById(@PathVariable String id) {
        String[] s = id.split("-");
        CarnetId c = new CarnetId(s[0],s[1]);
        carnetService.deleteCarnetById(c);
    }
    @DeleteMapping(consumes = {"application/json", "application/xml"})
    public void deleteCarnet(@RequestBody Carnet carnet) {
        carnetService.deleteCarnet(carnet);
    }
    @DeleteMapping("/all")
    public void deleteAllCarnets() {
        carnetService.deleteAllCarnets();
    }
    @PostMapping(value={"/{idClient}-{idSport}/buy","/{idClient}/{idSport}/buy"},produces = {"application/json", "application/xml"},consumes = {"application/json", "application/xml"})
    public ResponseEntity<Carnet> buyTicket(@PathVariable String idClient, @PathVariable String idSport, @RequestBody Integer nombres) {
        Carnet carnet = carnetService.buyTicket(idClient, idSport, nombres);
        if(carnet != null) {
            return ResponseEntity.ok(carnet);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
}
