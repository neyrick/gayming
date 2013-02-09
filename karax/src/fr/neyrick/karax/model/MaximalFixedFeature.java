package fr.neyrick.karax.model;

import fr.neyrick.karax.entities.generic.CharacterEdit;

public class MaximalFixedFeature extends FixedNumericFeature{

	
	@Override
	public void recordEdit(CharacterEdit edit) {
		if (edit.getAmount() > getAmount()) {
			super.recordEdit(edit);
		}
	}

	public MaximalFixedFeature(FeaturesCollection container, String key) {
		super(container, key);
	}

	public MaximalFixedFeature(String key) {
		super(key);
	}

	public MaximalFixedFeature() {
	}

}
