package fr.neyrick.karax.pathfinder.model;

import java.util.Map;

import fr.neyrick.karax.entities.generic.CharacterEdit;
import fr.neyrick.karax.model.FeaturesCollection;
import fr.neyrick.karax.model.StaticFeaturesCollection;

public class SpellcastingCollection extends
		StaticFeaturesCollection<Spellcasting> {

	private Map<String, Ability> abilitiesMap;
	
	@Override
	public Spellcasting addFeature(String subItemKey, CharacterEdit edit) {
		return addFeature(new Spellcasting(subItemKey, abilitiesMap));
	}

	public SpellcastingCollection(FeaturesCollection parent, String key,
			Map<String, Ability> abilitiesMap) {
		super(parent, key, Spellcasting.class);
		this.abilitiesMap = abilitiesMap;
	}

	public SpellcastingCollection(String key, Map<String, Ability> abilitiesMap) {
		super(key, Spellcasting.class);
		this.abilitiesMap = abilitiesMap;
	}

	public SpellcastingCollection() {
	}

}
