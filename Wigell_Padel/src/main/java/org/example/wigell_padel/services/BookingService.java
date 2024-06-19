package org.example.wigell_padel.services;

import jakarta.transaction.Transactional;
import org.apache.log4j.Logger;
import org.example.wigell_padel.entities.Booking;
import org.example.wigell_padel.entities.Customer;
import org.example.wigell_padel.entities.Field;
import org.example.wigell_padel.exceptions.ResourceNotAvailableException;
import org.example.wigell_padel.exceptions.ResourceNotFoundException;
import org.example.wigell_padel.repositories.BookingRepository;
import org.example.wigell_padel.repositories.CustomerRepository;
import org.example.wigell_padel.repositories.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

@Service
public class BookingService implements BookingServiceInterface {

    private static final Logger logger = Logger.getLogger(BookingService.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private FieldService fieldService;

    @Autowired
    CustomerRepository customerRepository;




    @Override
    //@Transactional
    public Booking saveAndSetUnavailable(Booking booking) throws ResourceNotAvailableException {
        Customer customer = customerRepository.findById(booking.getCustomer().getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", booking.getCustomer().getCustomerId()));
        booking.setCustomer(customer);

        Field field = fieldRepository.findById(booking.getField().getFieldId()).orElseThrow(() -> new ResourceNotFoundException("Field", "id", booking.getField().getFieldId()));
        if (!field.isAvailable()) {
            throw new ResourceNotAvailableException("Field", "id", booking.getField().getFieldId());
        }

        booking.setActive(true);
        field.setAvailable(false);
        fieldRepository.save(field);
        Booking savedBooking = bookingRepository.save(booking);

        logger.info("Customer with id: " + customer.getCustomerId() + " made a booking");

        return savedBooking;

    }


    @Override
    public Booking save(Booking booking) {
       /* Optional<Booking> existingBooking = Optional.ofNullable(bookingRepository.findById(booking.getBookingId()).orElseThrow(() -> new ResourceNotFoundException("Booking", "id", booking.getBookingId())));

        if (existingBooking == null) {
            throw new ResourceNotAvailableException("Booking", "id", booking.getBookingId());
        }

        Field field = fieldRepository.findById(booking.getField().getFieldId()).orElseThrow(() -> new ResourceNotFoundException("Field", "id", booking.getField().getFieldId()));

        if (booking == null || booking.getField() == null) {
            throw new ResourceNotFoundException("Field", "id", booking.getField().getFieldId());
        }
        if (field.isAvailable()) {
            existingBooking.get().setStartDateAndTime(booking.getStartDateAndTime());
            existingBooking.get().setEndDateAndTime(booking.getEndDateAndTime());
            existingBooking.get().setNumberOfPlayers(booking.getNumberOfPlayers());
            existingBooking.get().setPriceTotalSEK(booking.getPriceTotalSEK());
            existingBooking.get().setPriceTotalEUR(booking.getPriceTotalEUR());

            booking.setActive(true);
            field.setAvailable(false);
            fieldRepository.save(field);
            Booking savedBooking = bookingRepository.save(booking);

            logger.info("Customer updated booking");
            return savedBooking;
        }
        return null;*/

        Field field = fieldRepository.findById(booking.getField().getFieldId())
                .orElseThrow(() -> new ResourceNotFoundException("Field", "id", booking.getField().getFieldId()));

        if (field.isAvailable()) {
            booking.setActive(true);
            field.setAvailable(false);
            fieldRepository.save(field);
            Booking savedBooking = bookingRepository.save(booking);

            logger.info("Customer created booking");
            return savedBooking;
        } else {
            throw new ResourceNotAvailableException("Field", "id", booking.getField().getFieldId());
        }
    }

    @Override
    public List<Booking> getBookingsByCustomer(Customer customer) {
        Customer existingCustomer = customerRepository.findById(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customer.getCustomerId()));
        if (existingCustomer != null) {
            List<Booking> listOfBookings = existingCustomer.getBookings();
            Collections.sort(listOfBookings, new Comparator<Booking>() {
                @Override
                public int compare(Booking o1, Booking o2) {
                    int startDateComparison = o1.getStartDateAndTime().compareTo(o2.getStartDateAndTime());

                    if (startDateComparison == 0) {
                        return o1.getEndDateAndTime().compareTo(o2.getEndDateAndTime());
                    }
                    return startDateComparison;
                }
            });

            return existingCustomer.getBookings();


        } else {
            logger.info("No bookings found for customer with ID: " + existingCustomer.getCustomerId());
            return Collections.emptyList();
        }
    }

    @Override
    public Booking updateBooking(Booking booking) {

        // Fetch existing booking to update
        Booking existingBooking = bookingRepository.findById(booking.getBookingId())
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "id", booking.getBookingId()));

        // Fetch and set the customer
        Customer customer = customerRepository.findById(booking.getCustomer().getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", booking.getCustomer().getCustomerId()));
        existingBooking.setCustomer(customer);

        // Fetch and set the new field
        Field fieldToUpdate = fieldRepository.findById(booking.getField().getFieldId())
                .orElseThrow(() -> new ResourceNotFoundException("Field", "id", booking.getField().getFieldId()));

        // Check if the new field is available
      /*  if (!fieldToUpdate.isAvailable() && !fieldToUpdate.equals(existingBooking.getField())) {
            throw new ResourceNotAvailableException("Field", "id", booking.getField().getFieldId());
        }*/

        // Update booking details
        existingBooking.setStartDateAndTime(booking.getStartDateAndTime());
        existingBooking.setEndDateAndTime(booking.getEndDateAndTime());
        existingBooking.setNumberOfPlayers(booking.getNumberOfPlayers());
        existingBooking.setPriceTotalSEK(booking.getPriceTotalSEK());
        existingBooking.setPriceTotalEUR(booking.getPriceTotalEUR());

        // Update field availability if it's being changed
        if (!fieldToUpdate.equals(existingBooking.getField())) {
            existingBooking.getField().setAvailable(true);  // Make the old field available again
            fieldToUpdate.setAvailable(false);  // Set the new field as unavailable
            fieldRepository.save(existingBooking.getField());
        }

        existingBooking.setField(fieldToUpdate);
        existingBooking.setActive(true);

        fieldRepository.save(fieldToUpdate);
        Booking updatedBooking = bookingRepository.save(existingBooking);

        logger.info("Customer with id: " + customer.getCustomerId() + " updated booking with id: " + updatedBooking.getBookingId());
        return updatedBooking;
    }
}




