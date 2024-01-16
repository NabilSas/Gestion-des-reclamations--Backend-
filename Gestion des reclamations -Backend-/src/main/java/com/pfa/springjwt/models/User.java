package com.pfa.springjwt.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email") 
    })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 20)
  private String username;
  
  @Size(max = 20)
  private String nom;
  
  @Size(max = 20)
  private String cin;
  
  @Size(max = 20)
  private String prenom;

  @Size(max = 50)
  @Email
  private String email;

  @Size(max = 120)
  private String password;

  @OneToMany
  private List<Reclamation> reclamation;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }
  
  public User(String nom,String prenom,String username, String email, String password,String cin) {
	    this.username = username;
	    this.email = email;
	    this.password = password;
	    this.nom=nom;
	    this.prenom=prenom;
	    this.cin=cin;
	  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public String getPrenom() {
	return prenom;
}

public void setPrenom(String prenom) {
	this.prenom = prenom;
}

public String getCin() {
	return cin;
}

public void setCin(String cin) {
	this.cin = cin;
}

public User(Long id, @Size(max = 20) String username, @Size(max = 20) String nom, @Size(max = 20) String cin,
		@Size(max = 20) String prenom, @Size(max = 50) @Email String email, @Size(max = 120) String password,
		List<Reclamation> reclamation, Set<Role> roles) {
	super();
	this.id = id;
	this.username = username;
	this.nom = nom;
	this.cin = cin;
	this.prenom = prenom;
	this.email = email;
	this.password = password;
	this.reclamation = reclamation;
	this.roles = roles;
}

public List<Reclamation> getReclamation() {
	return reclamation;
}

public void setReclamation(List<Reclamation> reclamation) {
	this.reclamation = reclamation;
}


  
  
}
