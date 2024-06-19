package org.example.wigell_padel.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;

    @Column(name = "start_date_and_time", length = 20)
    private LocalDateTime startDateAndTime;
    @Column(name = "end_date_and_time", length = 20)
    private LocalDateTime endDateAndTime;
    @Column(name = "number_of_players", length = 4)
    private int numberOfPlayers;
    @Column(name = "price_total_SEK", length = 10)
    private double priceTotalSEK;
    @Column(name = "price_total_EUR", length = 10)
    private double priceTotalEUR;
    @Column(name = "is_active", length = 5)
    private boolean isActive;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long id) {
        this.bookingId = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getStartDateAndTime() {
        return startDateAndTime;
    }

    public void setStartDateAndTime(LocalDateTime dateTime) {
        this.startDateAndTime = dateTime;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public double getPriceTotalSEK() {
        return priceTotalSEK;
    }

    public void setPriceTotalSEK(double priceTotalSEK) {
        this.priceTotalSEK = priceTotalSEK;
    }

    public double getPriceTotalEUR() {
        return priceTotalEUR;
    }

    public void setPriceTotalEUR(double priceTotalEUR) {
        this.priceTotalEUR = priceTotalEUR;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getEndDateAndTime() {
        return endDateAndTime;
    }

    public void setEndDateAndTime(LocalDateTime endDateAndTime) {
        this.endDateAndTime = endDateAndTime;
    }
}
