package org.example.wigell_padel.repositories;

import org.example.wigell_padel.entities.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface FieldRepository extends JpaRepository<Field, Long> {
}
