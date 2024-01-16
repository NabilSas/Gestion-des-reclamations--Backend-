package com.pfa.springjwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.pfa.springjwt.models.Services;
import com.pfa.springjwt.repository.ServiceRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public List<Services> findAll() {
        return serviceRepository.findAll();
    }

    public <S extends Services> S save(S entity) {
        return serviceRepository.save(entity);
    }

    public Optional<Services> findById(Long aLong) {
        return serviceRepository.findById(aLong);
    }

    public void deleteById(Long aLong) {
        serviceRepository.deleteById(aLong);
    }
    
    public Optional<Services> updateService(Long id, Services updatedService) {
        Optional<Services> existingService = serviceRepository.findById(id);

        if (existingService.isPresent()) {
            Services serviceToUpdate = existingService.get();
            serviceToUpdate.setService(updatedService.getService());

            return Optional.of(serviceRepository.save(serviceToUpdate));
        }

        return Optional.empty();
    }
}
