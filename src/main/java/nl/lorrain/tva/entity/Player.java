package nl.lorrain.tva.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;

import nl.lorrain.tva.type.GenderType;

@Entity
public class Player implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String firstName;
	
	private String surName;
	
	private Enum<GenderType> genderType;
	
	private Date dateOfBirth;
	
	private Integer knltbNumber;
	
	@Email(message="Invalid E-mail adress!")
	private String email;
	
	private String phoneNumber;
	
	public Player(){}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public Enum<GenderType> getGenderType() {
		return genderType;
	}

	public void setGenderType(Enum<GenderType> genderType) {
		this.genderType = genderType;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getKnltbNumber() {
		return knltbNumber;
	}

	public void setKnltbNumber(Integer knltbNumber) {
		this.knltbNumber = knltbNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
