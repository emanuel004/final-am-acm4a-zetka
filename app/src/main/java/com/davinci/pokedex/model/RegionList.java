package com.davinci.pokedex.model;

public class RegionList {
    private int image;
    private String text;

    private int id;
    private int inicio;
    private int total;

    public RegionList(int image, String text, int id, Integer inicio, int total) {
        this.image = image;
        this.text = text;
        this.id = id;

        this.inicio = inicio;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
