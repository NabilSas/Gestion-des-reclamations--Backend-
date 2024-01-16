package com.pfa.springjwt.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.pfa.springjwt.models.Reclamation;
import com.pfa.springjwt.repository.ReclamationRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReclamationService {

    private final ReclamationRepository reclamationRepository;


    public List<Reclamation> findAll() {
        return reclamationRepository.findAll();
    }

    public <S extends Reclamation> S save(S entity) {
        return reclamationRepository.save(entity);
    }

    public Optional<Reclamation> findById(Long aLong) {
        return reclamationRepository.findById(aLong);
    }

    public void deleteById(Long aLong) {
        reclamationRepository.deleteById(aLong);
    }
    public Optional<Reclamation> updateReclamation(Long id, Reclamation updatedReclamation) {
        Optional<Reclamation> existingReclamation = reclamationRepository.findById(id);

        if (existingReclamation.isPresent()) {
            Reclamation reclamationToUpdate = existingReclamation.get();
            reclamationToUpdate.setMotif(updatedReclamation.getMotif());
            reclamationToUpdate.setCriticite(updatedReclamation.isCriticite());
            reclamationToUpdate.setCommentaire(updatedReclamation.getCommentaire());
            reclamationToUpdate.setMotifMgr(updatedReclamation.getMotifMgr());
            reclamationToUpdate.setStatus(updatedReclamation.getStatus());
            reclamationToUpdate.setPieceJointe(updatedReclamation.getPieceJointe());
            reclamationToUpdate.setService(updatedReclamation.getService());
            return Optional.of(reclamationRepository.save(reclamationToUpdate));
        }

        return Optional.empty(); // Indicate failure if reclamation with the given ID doesn't exist
    }

    public List<Reclamation> getReclamationsByUserId(Long userId) {
        
        return reclamationRepository.findByUserId(userId);
    }
    public List<Reclamation> getReclamationsByUserCin(String userCin) {

        return reclamationRepository.findByUserCin(userCin);
    }
}
