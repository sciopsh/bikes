package com.sciopsh.bikes.repository;

import com.sciopsh.bikes.model.Bike;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BikeRepository extends MongoRepository<Bike, String> {
    @Query("{ name: '?0' }")
    List<Bike> findAllByName(String name, Sort sort);

    @Query("{ manufacturer: '?0' }")
    List<Bike> findAllByManufacturer(String manufacturer, Sort sort);

    @Query("{ 'items.type': '?0' }")
    List<Bike> findAllByItems(String itemType, Sort sort);
}
