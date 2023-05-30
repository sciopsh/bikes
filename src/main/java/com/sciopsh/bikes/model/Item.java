package com.sciopsh.bikes.model;

import org.bson.types.ObjectId;

import javax.validation.constraints.NotNull;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

