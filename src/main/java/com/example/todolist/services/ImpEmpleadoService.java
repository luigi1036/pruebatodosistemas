package com.example.todolist.services;

import com.example.todolist.interfaces.IEmpleadoService;
import com.example.todolist.models.Empleado;
import com.example.todolist.repositories.IEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImpEmpleadoService implements IEmpleadoService {

    @Autowired
    private IEmpleadoRepository iEmpleadoRepository;

    @Override
    public List<Empleado> getAllEmpleados() {
        return iEmpleadoRepository.findAll();
    }
}
