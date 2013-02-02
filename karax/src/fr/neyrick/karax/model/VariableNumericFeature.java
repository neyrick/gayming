package fr.neyrick.karax.model;

import fr.neyrick.karax.entities.generic.CharacterEdit;
import fr.neyrick.karax.entities.generic.CharacterEdit.ExpenseType;


public abstract class VariableNumericFeature extends AbstractFeature {

//	private static final NumberFormat format = NumberFormat.getNumberInstance();
	
	private int creationCost = 0;	
	private int freeCost = 0;	
	private int experienceCost = 0;	
	
	public int getCreationCost() {
		return creationCost;
	}

	public int getFreeCost() {
		return freeCost;
	}

	public int getExperienceCost() {
		return experienceCost;
	}

	public int getTotalCost() {
		return creationCost + freeCost + experienceCost;
	}
	
	public VariableNumericFeature(String key) {
		super(key);
	}

	@Override
	public void recordEdit(CharacterEdit edit) {
		if (ExpenseType.CREATION.equals(edit.getExpenseType())) creationCost += edit.getSpentAmount();
		else if (ExpenseType.FREE.equals(edit.getExpenseType())) freeCost += edit.getSpentAmount();
		else if (ExpenseType.EXPERIENCE.equals(edit.getExpenseType())) experienceCost += edit.getSpentAmount();
	}
	

}
