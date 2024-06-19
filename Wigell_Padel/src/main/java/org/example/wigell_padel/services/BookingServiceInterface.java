package org.example.wigell_padel.services;

import org.example.wigell_padel.entities.Booking;
import org.example.wigell_padel.entities.Customer;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookingServiceInterface {
    Booking saveAndSetUnavailable(Booking booking);
    Booking save(Booking booking);
    List<Booking> getBookingsByCustomer(Customer customer);
    Booking updateBooking(Booking booking);
}
