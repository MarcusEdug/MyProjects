package org.example.wigell_padel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fieldId;
    @Column(name = "name", length = 25, nullable = false)
    private String name;
    @Column(name = "location", length = 25, nullable = false)
    private String location;
    @Column(name = "available", length = 5)
    private boolean available = true;
    @Column(name = "price_SEK", length = 10,nullable = false)
    private double priceSEK;
    @Column(name = "price_EUR", length = 10, nullable = false)
    private double priceEUR;

    @JsonIgnoreProperties("booking")
    @JsonIgnore
    @OneToOne(mappedBy = "field", cascade = CascadeType.MERGE)
    private Booking booking;

    public long getFieldId() {
        return fieldId;
    }

    public void setFieldId(long id) {
        this.fieldId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getPriceSEK() {
        return priceSEK;
    }

    public void setPriceSEK(double priceSEK) {
        this.priceSEK = priceSEK;
    }

    public double getPriceEUR() {
        return priceEUR;
    }

    public void setPriceEUR(double priceEUR) {
        this.priceEUR = priceEUR;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
