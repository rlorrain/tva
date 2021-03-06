package nl.lorrain.tva.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import nl.lorrain.tva.annotation.UniqueEmail;
import nl.lorrain.tva.annotation.UniqueUsername;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(unique=true)
	@Size(min=3, message="Name must be at least 3 characters!")
	@UniqueUsername(message="Username already in use!")
	private String name;
	
	@Column(unique=true)
	@Size(min=1, message="Invalid e-mail adress!")
	@Email(message="Invalid e-mail adress!")
	@UniqueEmail(message="E-mail adress already in use!")
	private String email;

	@Size(min=5, message="Name must be at least 5 characters!")
	private String password;

	private Boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	private List<Role> roles;

	@OneToMany(mappedBy = "user", cascade=CascadeType.REMOVE)
	private List<Blog> blogs;
	
	public User(){}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getId() {
		return id;
	}
}
