package com.sciopsh.bikes.controller;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sciopsh.bikes.model.Bike;
import com.sciopsh.bikes.model.Item;
import org.apache.catalina.mapper.Mapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bikes")
public class BikeController {

    Map<Long, Bike> bikes = new HashMap<>();
    long consecutive = 0L;
    long itemConsecutive = 0L;

    @PostMapping(consumes = "application/json")
    public String createBike(@RequestBody Bike bike) {
        //TODO
        for(Item item : bike.getItems()) {
            item.setId(itemConsecutive++);
        }
        bike.setId(consecutive);
        bikes.put(consecutive, bike);
        return "" + consecutive++;
    }

    @GetMapping(path = "{id}")
    public String getBike(@PathVariable long id) {
        try {
            return new ObjectMapper().writeValueAsString(bikes.get(id));
        } catch (Exception e) {
            return "not found";
        }
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
