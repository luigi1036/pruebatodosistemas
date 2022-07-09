package com.example.todolist.repositories;

import com.example.todolist.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface IEmpleadoRepository extends JpaRepository<Empleado, Long> {

    List<Empleado> findAll();
}
