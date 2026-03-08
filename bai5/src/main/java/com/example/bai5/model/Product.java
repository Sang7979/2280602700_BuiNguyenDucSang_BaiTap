package com.example.bai5.model;

public class Product {

    private int id;
    private String name;
    private String image;
    private long price;
    private Category category;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public long getPrice() { return price; }
    public void setPrice(long price) { this.price = price; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}