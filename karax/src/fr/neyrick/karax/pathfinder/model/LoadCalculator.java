package fr.neyrick.karax.pathfinder.model;

import java.util.Collection;


import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;
import fr.neyrick.karax.model.FixedNumericFeature;
import fr.neyrick.karax.model.StaticFeaturesCollection;
import static fr.neyrick.karax.pathfinder.model.Load.*;


public class LoadCalculator extends AbstractNumericFeatureCalculator<Load>{

	private Ability strength;
	
	private StaticFeaturesCollection<FixedNumericFeature> gear;

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
	
	private static void setLoadLevel(Load load, String state, int penalty, int maxDex) {
		load.setState(state);
		load.setPenalty(penalty);
		load.setMaxDex(maxDex);		
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
		
		feature.setMaxLight(maxLight);
		feature.setMaxMedium(maxMedium);
		feature.setMaxHeavy(maxHeavy);

		if (result < maxLight) setLoadLevel(feature, LOAD_LIGHT, 0, 100);
		else if (result < maxMedium) setLoadLevel(feature, LOAD_MEDIUM, -3, 3);
		else if (result < maxHeavy) setLoadLevel(feature, LOAD_HEAVY, -6, 1);
		else setLoadLevel(feature, LOAD_OVER, -20, -20);
		
		return result;
	}

	public LoadCalculator(Ability strength, StaticFeaturesCollection<FixedNumericFeature> gear) {
		super();
		this.strength = strength;
		this.gear = gear;
	}

}
