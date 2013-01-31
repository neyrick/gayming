package fr.neyrick.karax.entities.generic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class MetaCharacter {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String playerName;
	
	@ManyToOne
	private Game game;
	
	@OneToMany(mappedBy="character", fetch=FetchType.LAZY)
	@OrderBy("editDate")
	private List<CharacterEdit> edits = new ArrayList<CharacterEdit>();
	
	@OneToMany(mappedBy="character")
	private Set<ExperienceGain> experienceGains;

	private Date creationDate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<CharacterEdit> getEdits() {
		return edits;
	}

	public void setEdits(List<CharacterEdit> edits) {
		this.edits = edits;
	}

	public Set<ExperienceGain> getExperienceGains() {
		return experienceGains;
	}

	public void setExperienceGains(Set<ExperienceGain> experienceGains) {
		this.experienceGains = experienceGains;
	}
	
	public int getExperienceTotal() {
		int result = 0;
		for (ExperienceGain gain : experienceGains) {
			result += gain.getAmount();
		}
		return result;
	}
	
	public Date getLastUpdate() {
		if (edits == null) return null;
		Date result = null;
		for(CharacterEdit edit : edits) {
			if (result == null) result = edit.getEditDate();
			else if (result.before(edit.getEditDate())) result = edit.getEditDate();
		}
		return result;
	}
}
