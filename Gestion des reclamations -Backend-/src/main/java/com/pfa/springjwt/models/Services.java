package com.pfa.springjwt.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String service;
    @OneToMany
    private List<Reclamation> reclamation;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }



    public Services(Long id, String service) {
		this.id = id;
		this.service = service;
	}

	public Services() {
	}

	public Services(Long id, String service, List<Reclamation> reclamation) {
		super();
		this.id = id;
		this.service = service;
		this.reclamation = reclamation;
	}

	public List<Reclamation> getReclamation() {
		return reclamation;
	}

	public void setReclamation(List<Reclamation> reclamation) {
		this.reclamation = reclamation;
	}
    
    
}
