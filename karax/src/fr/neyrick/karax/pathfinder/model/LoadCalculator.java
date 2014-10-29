package fr.neyrick.karax.pathfinder.model;

import java.util.Collection;


import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;
import fr.neyrick.karax.model.FixedNumericFeature;
import fr.neyrick.karax.model.StaticFeaturesCollection;
import static fr.neyrick.karax.pathfinder.model.Load.*;


public class LoadCalculator extends AbstractNumericFeatureCalculator<Load>{

	private Ability strength;
	
	private StaticFeaturesCollection<FixedNumericFeature> gear;
	
	private StaticFeaturesCollection<Armor> armors; 

	private static int getLightCapacity(int strength) {
		if (strength > 29) return getLightCapacity(20 + strength % 10) * 4 * ((strength / 10) -2);
		else switch (strength) {
			case 1: return 3;
			case 2: return 6;
			case 3: return 10;
			case 4: return 13;
			case 5: return 16;
			case 6: return 20;
			case 7: return 23;
			case 8: return 26;
			case 9: return 30;
			case 10: return 33;
			case 11: return 38;
			case 12: return 43;
			case 13: return 50;
			case 14: return 58;
			case 15: return 66;
			case 16: return 76;
			case 17: return 86;
			case 18: return 100;
			case 19: return 116;
			case 20: return 133;
			case 21: return 153;
			case 22: return 173;
			case 23: return 200;
			case 24: return 233;
			case 25: return 266;
			case 26: return 306;
			case 27: return 346;
			case 28: return 400;
			case 29: return 466;
			default: throw new IllegalArgumentException("Force incorrecte: " + strength);
		}
	}
	
	@Override
	public Number calculateFeature(Load feature) {
		
		float result = 0;
		Collection<FixedNumericFeature> items = gear.getActualSubFeatures();
		for(FixedNumericFeature item : items) {
			result += item.getAmount() / 10f;
		}

		int capacity = getLightCapacity(strength.getNumericValue().intValue());
		float maxLight = capacity / 2f;
		float maxMedium = capacity;
		float maxHeavy = capacity * 1.5f;
		int maxDex = 100;
		int penalty = 0;		
		
		feature.setMaxLight(maxLight);
		feature.setMaxMedium(maxMedium);
		feature.setMaxHeavy(maxHeavy);

		if (result <= maxLight) {
			feature.setState(LOAD_LIGHT);
		}
		else if (result <= maxMedium) {
			feature.setState(LOAD_MEDIUM);
			maxDex = 3;
			penalty = -3;
		}
		else if (result <= maxHeavy) {
			feature.setState(LOAD_HEAVY);
			maxDex = 1;
			penalty = -6;
		}
		else {
			feature.setState(LOAD_OVER);
			maxDex = -20;
			penalty = -20;
		}
		
		int tempInt;
		int armorpenalty = 0;
		for(Armor armor : armors.getActualSubFeatures()) {
			tempInt = armor.getMaxDex().getNumericValue();
			if (tempInt < maxDex) maxDex = tempInt;
			armorpenalty += armor.getPenalty().getNumericValue();
		}
		if (armorpenalty < penalty) penalty = armorpenalty;
		
		feature.setPenalty(penalty);
		feature.setMaxDex(maxDex);		
		
		return result;
	}

	public LoadCalculator(Ability strength, StaticFeaturesCollection<FixedNumericFeature> gear, StaticFeaturesCollection<Armor> armors) {
		super();
		this.strength = strength;
		this.gear = gear;
		this.armors = armors;
	}

}
