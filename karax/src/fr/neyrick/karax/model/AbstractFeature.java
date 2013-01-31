package fr.neyrick.karax.model;

import java.io.Serializable;
import java.util.Iterator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.entities.generic.MetaCharacter;
import fr.neyrick.karax.entities.generic.CharacterEdit;
import fr.neyrick.karax.entities.generic.CharacterEdit.ExpenseType;

@MappedSuperclass
@XmlRootElement
public abstract class AbstractFeature {

	@Id
	@GeneratedValue
	private Long Id;
	
	private MetaCharacter character;
	
	private String displayName;
	
	private Serializable value;

	private String key;
	
	public void afterUnmarshal(Unmarshaller u, Object parent) {
		    this.character = (MetaCharacter)parent;
	}
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getKey() {
		if (key == null) return displayName;
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Object getValue() {
		return value;
	}
	
	public String getStringValue() {
		return value.toString();
	}
	
	public void setValue(Serializable value) {
		this.value = value;
	}

	public MetaCharacter getCharacter() {
		return character;
	}

	public void setCharacter(MetaCharacter character) {
		this.character = character;
	}
	
	public int getCreationExpenses() {
		Iterator<CharacterEdit> edits = getCharacter().getEdits().iterator();
		CharacterEdit edit = null;
		int result = 0;
		while(edits.hasNext()) {
			edit = edits.next();
			if (edit.getTargetKey().equals(getKey())
					&& ExpenseType.CREATION.equals(edit.getExpenseType())) {
				result += edit.getSpentAmount();
			}
		}
		return result;
	}

	public int getExperienceandFreeExpenses() {
		Iterator<CharacterEdit> edits = getCharacter().getEdits().iterator();
		CharacterEdit edit = null;
		int result = 0;
		while(edits.hasNext()) {
			edit = edits.next();
			if (edit.getTargetKey().equals(getKey())
				&& (ExpenseType.EXPERIENCE.equals(edit.getExpenseType())
				|| ExpenseType.FREE.equals(edit.getExpenseType()))) {			
				result += edit.getSpentAmount();
			}
		}
		return result;
	}

	public int getExpensesTotal() {
		Iterator<CharacterEdit> expenses = getCharacter().getEdits().iterator();
		CharacterEdit expense = null;
		int result = 0;
		while(expenses.hasNext()) {
			expense = expenses.next();
			if (expense.getTargetKey().equals(getKey())) {
				result += expense.getSpentAmount();
			}
		}
		return result;
	}

}
