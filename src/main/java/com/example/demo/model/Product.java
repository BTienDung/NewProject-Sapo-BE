package com.example.demo.model;

import org.omg.CORBA.PRIVATE_MEMBER;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne()
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne()
    @JoinColumn(name = "capacity_id")
    private Capacity capacity;

    private ArrayList<String> picture;

    @Column(name = "create_date")
    private Date craeteDate;
    @Column(name = "modified_date")
    private Date modifiedDate;
    private boolean status;


    public Product() {
    }

    public Product(boolean status) {
        this.status = status;
    }

    public Product(String name, Category category, Color color, Capacity capacity, ArrayList<String> picture, Date craeteDate, Date modifiedDate, boolean status) {
        this.name = name;
        this.category = category;
        this.color = color;
        this.capacity = capacity;
        this.picture = picture;
        this.craeteDate = craeteDate;
        this.modifiedDate = modifiedDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public void setCapacity(Capacity capacity) {
        this.capacity = capacity;
    }

    public ArrayList<String> getPicture() {
        return picture;
    }

    public void setPicture(ArrayList<String> picture) {
        this.picture = picture;
    }

    public Date getCraeteDate() {
        return craeteDate;
    }

    public void setCraeteDate(Date craeteDate) {
        this.craeteDate = craeteDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
