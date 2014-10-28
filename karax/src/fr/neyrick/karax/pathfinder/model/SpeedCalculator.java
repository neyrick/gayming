package fr.neyrick.karax.pathfinder.model;

import static fr.neyrick.karax.pathfinder.model.Load.LOAD_HEAVY;
import static fr.neyrick.karax.pathfinder.model.Load.LOAD_LIGHT;
import static fr.neyrick.karax.pathfinder.model.Load.LOAD_MEDIUM;
import static fr.neyrick.karax.pathfinder.model.Load.LOAD_OVER;
import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;
import fr.neyrick.karax.model.SimpleVariable;


public class SpeedCalculator extends AbstractNumericFeatureCalculator<SimpleVariable>{

	private Load load;
	
	@Override
	public Number calculateFeature(SimpleVariable feature) {
		
		float result = calculateFromTotalCost(feature);
		
		switch (load.getState()) {
			case LOAD_LIGHT: return result;
			case LOAD_MEDIUM: return result*2f/3f;
			case LOAD_HEAVY: return result*2f/3f;
			case LOAD_OVER: return 0f;
			default: return 0f;
		}
	}

	public SpeedCalculator(Load load) {
		super();
		this.load = load;
	}

}
