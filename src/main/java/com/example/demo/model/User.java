package com.example.demo.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "First name should be provided")
	private String first_name;
	
	@NotBlank(message = "Last name should be provided")
	private String last_name;
	
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Must be in an Email address format")
	private String email;
	
	@NotBlank(message = "Password must be provided")
	private String user_password;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "E MMM dd HH:mm:ss yyyy")
	private Date registration_date;
	
	private boolean login_status;
	
	// Mapped By references the property name in the object making the join column call
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	@JsonIgnore
	private Set<UserLists> userLists; /* = new HashSet<>(); */
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
	@JsonManagedReference(value = "delegation")
	private Set<ListItem> userItems;
	
	public User () {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}
	
	public boolean isLoginStatus() {
		return login_status;
	}

	public void setLoginStatus(boolean login_status) {
		this.login_status = login_status;
	}
	
	public Set<UserLists> getUserLists() {
		return userLists;
	}

	public void setUserLists(Set<UserLists> userLists) {
		this.userLists = userLists;
	}
	
	public Set<ListItem> getUserItems() {
		return userItems;
	}

	public void setUserItems(Set<ListItem> userItems) {
		this.userItems = userItems;
	}
	
	public String toString () {
		return "User " + first_name + " " + last_name + ", with email address " + email;
	}	
}
