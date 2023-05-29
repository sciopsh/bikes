package com.sciopsh.bikes.controller;

import com.sciopsh.bikes.model.Bike;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bikes")
public class BikeController {

    @PostMapping(consumes = "application/json")
    public String CreateBike(@RequestBody Bike bike) {
        //TODO
        return "dummy response";
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
