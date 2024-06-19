package org.example.wigell_padel.repositories;

import org.example.wigell_padel.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomer_CustomerId(Long id);
    List<Booking> findByCustomerUsername(String username);
}
