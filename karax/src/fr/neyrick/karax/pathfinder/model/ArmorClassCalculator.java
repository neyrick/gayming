package fr.neyrick.karax.pathfinder.model;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;
import fr.neyrick.karax.model.CharacterFeature;
import fr.neyrick.karax.model.FeatureCalculator;

public class ArmorClassCalculator extends AbstractNumericFeatureCalculator implements FeatureCalculator{

	private Ability dexterity;
	
	@Override
	public Number calculate(CharacterFeature feature) {
		
		ArmorClass armorClass = (ArmorClass)feature;
		int result = 10;
		result += super.calculateFromTotalCost(armorClass);
		
		int armorBonus = armorClass.getCost("ARMOR");
		int naturalArmorBonus = armorClass.getCost("NATURAL_ARMOR");
		
		armorClass.setFlatfooted(result);
		
		result += dexterity.getBonus();
		
		armorClass.setTouch(result - armorBonus - naturalArmorBonus);
				
		return result;
	}

	public ArmorClassCalculator(Ability dexterity) {
		super();
		this.dexterity = dexterity;
	}

}
