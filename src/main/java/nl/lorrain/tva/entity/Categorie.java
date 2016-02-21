package nl.lorrain.tva.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import nl.lorrain.tva.type.AgeCategorieType;
import nl.lorrain.tva.type.StrengthCategorieType;
import nl.lorrain.tva.type.MatchType;

@Entity
public class Categorie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "tournament_id")
	private Tournament tournament;
	
	private Enum<MatchType> matchType;
	
	private Enum<AgeCategorieType> ageCategorieType;
	
	private Enum<StrengthCategorieType> strengthCategorieType;
	
	public Categorie(){}

	public Integer getId() {
		return id;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public Enum<MatchType> getMatchType() {
		return matchType;
	}

	public void setMatchType(Enum<MatchType> matchType) {
		this.matchType = matchType;
	}

	public Enum<AgeCategorieType> getAgeCategorieType() {
		return ageCategorieType;
	}

	public void setAgeCategorieType(Enum<AgeCategorieType> ageCategorieType) {
		this.ageCategorieType = ageCategorieType;
	}

	public Enum<StrengthCategorieType> getStrengthCategorieType() {
		return strengthCategorieType;
	}

	public void setStrengthCategorieType(Enum<StrengthCategorieType> strengthCategorieType) {
		this.strengthCategorieType = strengthCategorieType;
	}
}
