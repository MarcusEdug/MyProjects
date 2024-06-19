package org.example.wigell_padel.controllers;

import org.example.wigell_padel.entities.Booking;
import org.example.wigell_padel.entities.Customer;
import org.example.wigell_padel.entities.Field;
import org.example.wigell_padel.services.BookingService;
import org.example.wigell_padel.services.CustomerService;
import org.example.wigell_padel.services.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private FieldService fieldService;

    @GetMapping("/api/v5/availability")
    public ResponseEntity<List<Field>> getAvailableFields() {
        List<Field> availableFields = fieldService.fetchAllAvailableFields();
        return ResponseEntity.ok(availableFields);
    }

    @PostMapping("/api/v5/booking")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Booking> bookField(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.save(booking));
    }

    @GetMapping("/api/v5/mybookings")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Booking>> getBookingsByCustomer(@RequestBody Customer customer) {
        bookingService.getBookingsByCustomer(customer);
        return ResponseEntity.ok(bookingService.getBookingsByCustomer(customer));
    }

    @PutMapping("/api/v5/bookings/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
        booking.setBookingId(id);
        Booking updatedBooking = bookingService.updateBooking(booking);
        return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
    }
}
