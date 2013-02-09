package fr.neyrick.karax.model;

import fr.neyrick.karax.entities.generic.CharacterEdit;

public class MinimalFixedFeature extends FixedNumericFeature{

	
	@Override
	public void recordEdit(CharacterEdit edit) {
		if (edit.getAmount() < getAmount()) {
			super.recordEdit(edit);
		}
	}

	public MinimalFixedFeature(FeaturesCollection container, String key) {
		super(container, key);
	}

	public MinimalFixedFeature(String key) {
		super(key);
	}

	public MinimalFixedFeature() {
	}

}
