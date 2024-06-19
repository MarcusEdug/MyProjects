package org.example.wigell_padel.services;

import org.example.wigell_padel.entities.Field;
import org.springframework.util.ReflectionUtils;

import java.util.List;

public interface FieldServiceInterface {
    Field saveById(long id);
    Field save (Field field);
    void deleteFieldById(long id);
    List<Field> fetchAllFields();
    List<Field> fetchAllAvailableFields();
}
