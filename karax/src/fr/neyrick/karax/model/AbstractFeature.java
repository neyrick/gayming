package fr.neyrick.karax.model;

import javax.xml.bind.annotation.XmlTransient;


@XmlTransient
public abstract class AbstractFeature implements EditListener {

//	private MetaCharacter character;
	
	private String key;
	
//	private List<CharacterEdit> edits = new ArrayList<>();
		
	public String getKey() {
		return key;
	}
	
	public abstract String getValue();

	/*
	public MetaCharacter getCharacter() {
		return character;
	}
*/
	
	public AbstractFeature(String key) {
		super();
//		this.character = character;
		this.key = key;
		/*
		for (CharacterEdit edit : getCharacter().getEdits()) {
			if (edit.getTargetKey().equals(key)) {
				edits.add(edit);
			}
		}
		*/
	}
/*
	public List<CharacterEdit> getEdits() {
		return edits;
	}

	public int getCreationExpenses() {
		int result = 0;
		for (CharacterEdit edit : getEdits()) {
			if (ExpenseType.CREATION.equals(edit.getExpenseType())) {
				result += edit.getSpentAmount();
			}
		}
		return result;
	}

	public int getExperienceAndFreeExpenses() {
		int result = 0;
		for (CharacterEdit edit : getEdits()) {
			if (ExpenseType.EXPERIENCE.equals(edit.getExpenseType())
					|| ExpenseType.FREE.equals(edit.getExpenseType())) {			
				result += edit.getSpentAmount();
			}
		}
		return result;
	}

	public int getExpensesTotal() {
		int result = 0;
		for (CharacterEdit edit : getEdits()) {
			result += edit.getSpentAmount();
		}
		return result;
	}

	public CharacterEdit getLastEdit() {
		return edits.get(edits.size()-1);
	}
	*/
}
