package fr.neyrick.karax.pathfinder.model;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;

public class ArmorClassCalculator extends AbstractNumericFeatureCalculator<ArmorClass>{

	private Ability dexterity;
	
	@Override
	public Number calculateFeature(ArmorClass feature) {
		
		int result = 0;
		result += super.calculateFromTotalCost(feature);
		
		int armorBonus = feature.getCost("ARMOR");
		int naturalArmorBonus = feature.getCost("NATURAL_ARMOR");
		int shieldBonus = feature.getCost("SHIELD");
		int sizeBonus = feature.getCost("SIZE");
		int deflectionBonus = feature.getCost("DEFLECTION");
		int miscBonus = result - armorBonus - naturalArmorBonus - shieldBonus - sizeBonus - deflectionBonus;
		
		feature.setArmor(armorBonus);
		feature.setShield(shieldBonus);
		feature.setSize(sizeBonus);
		feature.setNatural(naturalArmorBonus);
		feature.setDeflection(deflectionBonus);
		feature.setMisc(miscBonus);
		
		result += 10;
				
		feature.setFlatfooted(result);
		
		result += dexterity.getBonus();
		
		feature.setTouch(result - armorBonus - naturalArmorBonus);
				
		return result;
	}

	public ArmorClassCalculator(Ability dexterity) {
		super();
		this.dexterity = dexterity;
	}

}
