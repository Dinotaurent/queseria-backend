package com.dinotaurent.msregistros.controllers;

import com.dinotaurent.msregistros.models.entity.Registro;
import com.dinotaurent.msregistros.models.services.IRegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RegistroController {

    @Autowired
    IRegistroService service;

    @GetMapping("/")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody Registro registro) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.insert(registro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable String id) {
        Optional<Registro> o = service.findById(id);

        if (o.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }
}
