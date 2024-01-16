package com.pfa.springjwt.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "piece jointe")
public class PieceJointe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] photo;
    private String type;
    // @JsonIgnore
    @OneToMany
    private List<Reclamation> reclamation;

    public PieceJointe() {

    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Reclamation> getReclamation() {
		return reclamation;
	}

	public void setReclamation(List<Reclamation> reclamation) {
		this.reclamation = reclamation;
	}

	public PieceJointe(Long id, String name, byte[] photo, String type, List<Reclamation> reclamation) {
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.type = type;
		this.reclamation = reclamation;
	}
    

    //Ghadi ncreeryiw wst l constructeur wa7d l arraylist bach ntfko mn NULL POINTER EXCEPTION wink wink ;)
    /*public PieceJointe() {
        this.reclamation = new ArrayList<>();
    }*/


  /*  public PieceJointe(byte[] photoBytes,String name, String type) {
        this.name = name;
        this.photo = photoBytes != null ? Base64.encodeBase64String(photoBytes) : null;
        this.type = type;
    }*/
}