package com.pfa.springjwt.controllers;


import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pfa.springjwt.models.Services;
import com.pfa.springjwt.service.ServiceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService serviceService;


    @GetMapping("/all")
    public List<Services> findAll() {
        return serviceService.findAll();
    }

    @PostMapping("/save-service")
    public <S extends Services> S save(@RequestBody S entity) {
        return serviceService.save(entity);
    }

    @GetMapping("/service/{id}")
    public Optional<Services> findById(@PathVariable Long id) {
        return serviceService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        serviceService.deleteById(id);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Services> updateService(
            @PathVariable Long id,
            @RequestBody Services updatedService
    ) {
        Optional<Services> updated = serviceService.updateService(id, updatedService);

        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
