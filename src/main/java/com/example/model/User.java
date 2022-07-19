package com.example.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.example.generator.UserGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="UserTable")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@GenericGenerator(name = "user_seq", strategy = "com.example.generator.UserGenerator", parameters = {
			@Parameter(name = UserGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = UserGenerator.VALUE_PREFIX_PARAMETER, value = "U_"),
			@Parameter(name = UserGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d") })
	@Column(name = "userId", updatable = false, nullable = false)
	private String userId;
	@Column(name="name",nullable =false)
	@NotEmpty
	@Size(min=3,max = 30,message ="User name should have at least 3 characters")
	private String userName;
	@NotNull
	@NotEmpty
	@Size(min=10,max = 10,message ="Enter valid Phone No")
	private String mobileNumber;
	@NotEmpty
	@Size(min=8,message ="Password should have at least 8 characters")
	private String password;
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
	@JsonIgnoreProperties("user")
	private List<Ticket> ticket;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public User(String userId,
			@NotEmpty @Size(min = 3, max = 30, message = "User name should have at least 3 characters") String userName,
			@NotNull @NotEmpty @Size(min = 10, max = 10, message = "Enter valid Phone No") String mobileNumber,
			@NotEmpty @Size(min = 8, message = "Password should have at least 8 characters") String password,
			List<Ticket> ticket) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.ticket = ticket;
	}


	public List<Ticket> getTicket() {
		return ticket;
	}

	public void setTicket(List<Ticket> ticket) {
		this.ticket = ticket;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
