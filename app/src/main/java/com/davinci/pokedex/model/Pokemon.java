package com.davinci.pokedex.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Pokemon {

    @JsonProperty("no")
    public int no;
    @JsonProperty("name")
    public String name;
    @JsonProperty("types")
    public ArrayList<String> types;
    @JsonProperty("sprites")
    public Sprites sprites;
    @JsonProperty("regions")
    public ArrayList<String> regions;


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public ArrayList<String> getRegions() {
        return regions;
    }

    public void setRegions(ArrayList<String> regions) {
        this.regions = regions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
