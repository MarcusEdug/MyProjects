package org.example.wigell_padel.services;

import org.example.wigell_padel.entities.Field;
import org.example.wigell_padel.repositories.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

@Service
public class FieldService implements FieldServiceInterface {

    private static final Logger logger = Logger.getLogger(FieldService.class.getName());

    @Autowired
    private FieldRepository fieldRepository;


    @Override
    public Field saveById(long id) {
        Field field = new Field();
        field.setFieldId(id);
        logger.info("Admin saved field with id: " + field.getFieldId());
        return fieldRepository.save(field);
    }

    @Override
    public Field save(Field field) {
        logger.info("Admin created a new field: ");
        return fieldRepository.save(field);
    }

    @Override
    public void deleteFieldById(long id) {
        Field field = new Field();
        field.setFieldId(id);
        logger.info("Admin deleted field with id: " + field.getFieldId() );
        fieldRepository.deleteById(id);
    }

    @Override
    public List<Field> fetchAllFields() {
        return fieldRepository.findAll();
    }

    @Override
    public List<Field> fetchAllAvailableFields() {
        List<Field> fields = fieldRepository.findAll();
        List<Field> availableFields = new ArrayList<>();

        for (Field field : fields) {
            if (field.isAvailable()) {
                availableFields.add(field);
            }
        }
        return availableFields;
    }


}


