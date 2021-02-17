package com.loginAndRoles.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name = "USER")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "password")
	private String password;

	@NotEmpty(message = "Debe ingresar el name")
	@Column(name = "name")
	private String name;

	@NotEmpty(message = "Debe ingresar un email")
	@Email(message = "El email es invalido")
	@Column(name = "email")
	private String email;

	// Se activa el usuario cuando confirma el email
	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = "confirmation_token")
	private String confirmationToken;

    @ManyToMany 
    @JoinTable(name = "users_roles", 
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")) 
    private Collection<Role> roles;
}