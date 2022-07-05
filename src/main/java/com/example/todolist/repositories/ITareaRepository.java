package com.example.todolist.repositories;

import com.example.todolist.models.Tarea;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ITareaRepository extends CrudRepository<Tarea, Long> {

    List<Tarea> findAll();
    Tarea save(Tarea tarea);
    void deleteById(Long id);

}
