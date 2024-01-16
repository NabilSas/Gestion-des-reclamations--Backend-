package com.pfa.springjwt.controllers;



import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pfa.springjwt.models.Reclamation;
import com.pfa.springjwt.models.Services;
import com.pfa.springjwt.models.User;
import com.pfa.springjwt.service.ReclamationService;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/reclamations")
public class ReclamationController {

    private final ReclamationService reclamationService;

    
    @PostMapping("/save")
    public Reclamation save(@RequestParam("motif") String motif,
                      @RequestParam("criticite") boolean criticite,
                      @RequestParam("commentaire") String commentaire,
                      @RequestParam("motifMgr") String motifMgr,
                      @RequestParam("status") String status,
                      @RequestParam("pieceJointe") MultipartFile pieceJointe,
                      @RequestParam("service") Services service,
                      @RequestParam("user") User user) {

        Reclamation rec = new Reclamation();
        rec.setMotif(motif);
        rec.setCriticite(criticite);
        rec.setCommentaire(commentaire);
        rec.setMotifMgr(motifMgr);
        rec.setStatus(status);
        rec.setService(service);
        rec.setUser(user);
        rec.setPieceJointe(saveImage(pieceJointe));


        return reclamationService.save(rec);
    }
    
    @PostMapping("/save_client")
    public Reclamation save2(@RequestParam("motif") String motif,
                      @RequestParam("criticite") boolean criticite,
                      @RequestParam("commentaire") String commentaire,
                      @RequestParam("status") String status,
                      @RequestParam("pieceJointe") MultipartFile pieceJointe,
                      @RequestParam("service") Services service,
                      @RequestParam("user") User user) {

        Reclamation rec = new Reclamation();
        rec.setMotif(motif);
        rec.setCriticite(criticite);
        rec.setCommentaire(commentaire);
        rec.setStatus(status);
        rec.setService(service);
        rec.setUser(user);
        rec.setPieceJointe(saveImage(pieceJointe));


        return reclamationService.save(rec);
    }

	    private String saveImage(MultipartFile file) {
	        if (!file.isEmpty()) {
	            try {
	                // Generate a random image name using UUID
	                String imageName = UUID.randomUUID().toString() + ".zip";
	                byte[] bytes = file.getBytes();
	                Path path = Paths.get("C:\\Users\\Monaim\\Desktop\\24pfa\\black-dashboard-react-master copy\\src\\assets\\img\\" + imageName);
	                Files.write(path, bytes);
	                return imageName;
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return null;
	    }

    @GetMapping("/all")
    public List<Reclamation> findAll() {
        return reclamationService.findAll();
    }

    /*@PostMapping("/save-reclamation")
    public <S extends Reclamation> S save(@RequestBody S entity) {
        return reclamationService.save(entity);
    */
    
    @GetMapping("/reclamation/{id}/piece/{pieceName}")
    public ResponseEntity<byte[]> downloadPiece(@PathVariable Long id, @PathVariable String pieceName) {
        try {
            // Path to the directory where pieces are stored
            Path filePath = Paths.get("C:\\Users\\Monaim\\Desktop\\24pfa\\black-dashboard-react-master copy\\src\\assets\\img\\" + pieceName);

            // Read the file into a byte array
            byte[] fileContent = Files.readAllBytes(filePath);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", pieceName);

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public List<Reclamation> getReclamationsByUser(@PathVariable Long userId) {
        return reclamationService.getReclamationsByUserId(userId);
    }
    @GetMapping("/usercin/{userCin}")
    public List<Reclamation> getReclamationsByUserCin(@PathVariable String userCin) {
        return reclamationService.getReclamationsByUserCin(userCin);
    }


    @GetMapping("/reclamation/{id}")
    public Optional<Reclamation> findById(@PathVariable Long id) {
        return reclamationService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        reclamationService.deleteById(id);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Reclamation> updateReclamation(
            @PathVariable Long id,
            @RequestBody Reclamation updatedReclamation
    ) {
        Optional<Reclamation> updated = reclamationService.updateReclamation(id, updatedReclamation);

        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
