package nl.lorrain.tva.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Tournament {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	
	private Date startDate;
	
	private Date endDate;
	
	@OneToMany(mappedBy = "tournament", cascade = CascadeType.REMOVE)
	private List<Categorie> Categories;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Categorie> getCategories() {
		return Categories;
	}

	public void setCategories(List<Categorie> categories) {
		Categories = categories;
	}
}
