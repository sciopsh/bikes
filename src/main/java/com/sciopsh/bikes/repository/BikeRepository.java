package com.sciopsh.bikes.repository;

import com.sciopsh.bikes.model.Bike;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BikeRepository extends MongoRepository<Bike, String> {
}
