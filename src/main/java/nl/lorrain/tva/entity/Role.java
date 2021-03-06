package nl.lorrain.tva.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import nl.lorrain.tva.type.RoleType;

@Entity
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@ManyToMany(mappedBy="roles", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<User> users;
	
	public Role(){}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(RoleType roleType) {
		this.name = roleType.toString();
	}
}
