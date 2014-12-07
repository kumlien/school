package com.kumliens.school.user;

import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.google.common.collect.Sets;

@Entity(name = "Users")
public class SchoolUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Size(min=6, max=64)
	private String username;

	@NotNull
	private  String encryptedPwd;

	@NotNull
	private  String firstName;

	@NotNull
	private  String lastName;
	
	@Email
	private  String email;

	@CreatedDate
	private Date createdDate;
	
	@LastModifiedDate
	private Date modifiedDate;
	
	@ElementCollection(targetClass=Role.class)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name="role")
	private Set<Role> roles = Sets.newHashSet();
	
	//Required according to JPA spec
	protected SchoolUser(){}

	public SchoolUser(String username, String encryptedPwd, String firstName,
			String lastName, String email) {
		this.username = username;
		this.encryptedPwd = encryptedPwd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEncryptedPwd() {
		return encryptedPwd;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public Set<Role> getRoles() {
		return roles;
	}
	
	public void addRole(Role role) {
		if (roles == null) roles = Sets.newHashSet();
		roles.add(role);
	}

}
