package com.pfa.springjwt.controllers;


import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pfa.springjwt.models.PieceJointe;
import com.pfa.springjwt.service.PiecejointeService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("/piecejointes")
public class PieceJointeController {

    private final PiecejointeService piecejointeService;

    @GetMapping("/all")
    public List<PieceJointe> findAll() {
        return piecejointeService.findAll();
    }

/*    @PostMapping("/save-pj")
    public <S extends PieceJointe> S save(@RequestBody S entity) {
        return piecejointeService.save(entity);
    }*/

    @GetMapping("/pj/{id}")
    public Optional<PieceJointe> findById(@PathVariable Long id) {
        return piecejointeService.findById(id);
    }

    @DeleteMapping("/delete/pj/{id}")
    public void deleteById(@PathVariable Long id) {
        piecejointeService.deleteById(id);
    }

    @PostMapping("/save-pj")
    public void savePJ(@RequestParam("file")MultipartFile file,
                       @RequestParam("name") String name,
                       @RequestParam("type") String type) throws IOException {
         piecejointeService.savePJ(file,name,type);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePieceJointe(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("type") String type
    ) {
        try {
            Optional<PieceJointe> updatedPieceJointe = piecejointeService.updatePieceJointe(id, file, name, type);

            if (updatedPieceJointe.isPresent()) {
                return new ResponseEntity<>(updatedPieceJointe.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("PieceJointe not found", HttpStatus.NOT_FOUND);
                // Or throw an exception to handle this case differently
            }
        } catch (IOException e) {
            return new ResponseEntity<>("Error updating PieceJointe", HttpStatus.INTERNAL_SERVER_ERROR);
            // Or handle the specific exception as needed
        }
    }

}
