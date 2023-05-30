package com.sciopsh.bikes.controller;

import com.sciopsh.bikes.model.Bike;
import com.sciopsh.bikes.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/bikes")
public class BikeController {

    @Autowired
    BikeRepository repository;

    @PostMapping(consumes = "application/json")
    @CacheEvict("bikeSearch")
    public Bike createBike(@RequestBody @Valid Bike bike) {
        return repository.save(bike);
    }

    @GetMapping(path = "search")
    @Cacheable("bikeSearch")
    public List<Bike> searchBikes(@RequestParam String filterKey, @RequestParam String filterValue,
                                  @RequestParam(required = false) String order) {

        Sort.Order sortOrder = new Sort.Order(Sort.DEFAULT_DIRECTION, "name");
        switch (order) {
            case "asc":
            case "ascending":
                sortOrder = new Sort.Order(Sort.Direction.ASC, "name");
                break;
            case "desc":
            case "descending":
                sortOrder = new Sort.Order(Sort.Direction.DESC, "name");
                break;
        }

        switch (filterKey.toLowerCase()) {
            case "name":
            case "bike.name":
                return repository.findAllByName(filterValue, Sort.by(sortOrder));
            case "manufacturer":
            case "bike.manufacturer":
                return repository.findAllByManufacturer(filterValue, Sort.by(sortOrder));
            case "type":
            case "item.type":
                return repository.findAllByItems(filterValue, Sort.by(sortOrder));
            default:
                throw new IllegalArgumentException("Illegal filters");
        }
    }

    @GetMapping(path = "{id}")
    @Cacheable("bikes")
    public Bike getBike(@PathVariable String id) {
        return repository.findById(id).orElse(null);
    }

}
