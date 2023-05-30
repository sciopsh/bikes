package com.sciopsh.bikes.model;

import lombok.Data;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotNull;

@Data
public class Item {
    private String id;
    @NotNull(message = "Item model is mandatory")
    private String model;
    @NotNull(message = "Item type is mandatory")
    private String type;
    private String description;

    public Item() {
        this.id = new ObjectId().toString();
    }
}

