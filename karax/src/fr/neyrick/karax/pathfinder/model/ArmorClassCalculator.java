package fr.neyrick.karax.pathfinder.model;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;
import fr.neyrick.karax.model.FixedNumericFeature;
import fr.neyrick.karax.model.StaticFeaturesCollection;

public class ArmorClassCalculator extends AbstractNumericFeatureCalculator<ArmorClass>{

	private Ability dexterity;
	private StaticFeaturesCollection<Armor> armors;
	private FixedNumericFeature size;
	
	@Override
	public Number calculateFeature(ArmorClass feature) {
		
		int armorBonus = feature.getCost("ARMOR");
		int naturalArmorBonus = feature.getCost("NATURAL_ARMOR");
		int shieldBonus = feature.getCost("SHIELD");
		int sizeBonus = -size.getAmount();
		int deflectionBonus = feature.getCost("DEFLECTION");
		int miscBonus = feature.getCost("MISC");
		
		int tempInt;
		for (Armor armor : armors.getActualSubFeatures()) {
			tempInt = armor.getAcBonus().getAmount();
			switch(armor.getType()) {
				case "ARMOR": if (armorBonus < tempInt) armorBonus = tempInt; break; 
				case "NATURAL_ARMOR": if (naturalArmorBonus < tempInt) naturalArmorBonus = tempInt; break; 
				case "SHIELD": if (shieldBonus < tempInt) shieldBonus = tempInt;  break;
				case "DEFLECTION": if (deflectionBonus < tempInt) deflectionBonus = tempInt;  break;
				case "MISC": if (miscBonus < tempInt) miscBonus = tempInt;  break;
				default:  break;
			}
		}
		
		feature.setArmor(armorBonus);
		feature.setShield(shieldBonus);
		feature.setSize(sizeBonus);
		feature.setNatural(naturalArmorBonus);
		feature.setDeflection(deflectionBonus);
		feature.setMisc(miscBonus);
		
		int result = 10;
		result += armorBonus;
		result += shieldBonus;
		result += naturalArmorBonus;
		result += deflectionBonus;
		result += sizeBonus;
		result += miscBonus;
		
		tempInt = dexterity.getBonus();
		
		if (tempInt > 0) {
			feature.setFlatfooted(result);
			result += dexterity.getBonus();			
		}
		else {
			result += dexterity.getBonus();			
			feature.setFlatfooted(result);
		}
		
		feature.setTouch(result - armorBonus - naturalArmorBonus - shieldBonus);
				
		return result;
	}

	public ArmorClassCalculator(Ability dexterity, StaticFeaturesCollection<Armor> armors, FixedNumericFeature size) {
		super();
		this.dexterity = dexterity;
		this.armors = armors;
		this.size = size;
	}

}
