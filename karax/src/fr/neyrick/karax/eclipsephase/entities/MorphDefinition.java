package fr.neyrick.karax.eclipsephase.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: MorphDefinition
 * 
 */
@Entity
@Table(name="EP_MorphDefinition")
public class MorphDefinition implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private String key;

	private String type;

	private String description;

	@OneToMany(mappedBy = "morph")
	private List<MorphEdit> edits = new ArrayList<>();

	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<MorphEdit> getEdits() {
		return edits;
	}

	public void setEdits(List<MorphEdit> edits) {
		this.edits = edits;
	}

	public MorphDefinition() {
		super();
	}

}
