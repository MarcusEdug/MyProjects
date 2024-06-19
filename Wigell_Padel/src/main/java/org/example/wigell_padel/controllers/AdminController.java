package org.example.wigell_padel.controllers;

import org.example.wigell_padel.entities.Customer;
import org.example.wigell_padel.entities.Field;
import org.example.wigell_padel.services.CustomerService;
import org.example.wigell_padel.services.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private FieldService fieldService;

    @Autowired
    CustomerService customerService;

    @GetMapping("/api/v5/customers")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Customer> getAllCustomers() {
        return customerService.fetchAllCustomers();
    }

    @PostMapping("/api/v5/addfield")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Field> addField(@RequestBody Field field) {
        Field savedField = fieldService.save(field);
        return ResponseEntity.ok(savedField);
    }

    @DeleteMapping("/api/v5/deletefield/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteField(@PathVariable long id) {
        fieldService.deleteFieldById(id);
        return new ResponseEntity<>("Field deleted", HttpStatus.OK);
    }

    @PutMapping("/api/v5/updateinfo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Field> updateField(@RequestBody Field field) {
        return ResponseEntity.ok(fieldService.save(field));
    }



}
