package com.sciopsh.bikes.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Document
@Data
public class Bike {
    private String id;
    @NotNull(message = "Bike name is mandatory")
    private String name;
    private String description;
    private int price;
    private String manufacturer;
    @Valid
    private List<Item> items;
}
