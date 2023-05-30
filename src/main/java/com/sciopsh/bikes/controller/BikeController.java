package com.sciopsh.bikes.controller;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sciopsh.bikes.model.Bike;
import com.sciopsh.bikes.model.Item;
import com.sciopsh.bikes.repository.BikeRepository;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bikes")
public class BikeController {

    @Autowired
    BikeRepository repository;

    @PostMapping(consumes = "application/json")
    public Bike createBike(@RequestBody @Valid Bike bike) {
        return repository.save(bike);
    }

    @GetMapping(path = "{id}")
    public Bike getBike(@PathVariable String id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping(path = "search")
    public String searchBikes(@RequestParam String q,
                              @RequestParam(required = false) String filterKey,
                              @RequestParam(required = false) String filterValue,
                              @RequestParam(required = false) String order) {
        //TODO
        return String.format("Your query was: q=%s,filterKey=%s,filterValue=%s,order=%s",
                q, filterKey, filterValue, order);
    }
}
