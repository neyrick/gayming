package fr.neyrick.karax.pathfinder.model;

import java.util.Map;

import fr.neyrick.karax.entities.generic.CharacterEdit;
import fr.neyrick.karax.model.FeaturesCollection;
import fr.neyrick.karax.model.FixedNumericFeature;
import fr.neyrick.karax.model.SimpleVariable;
import fr.neyrick.karax.model.StaticFeaturesCollection;

public class WeaponCollection extends StaticFeaturesCollection<Weapon> {

	private SimpleVariable baseAttackBonus;
	private Map<String, Ability> abilitiesMap;
	private FixedNumericFeature size;	

	@Override
	public Weapon addFeature(String subItemKey, CharacterEdit edit) {
	    Weapon weapon = new Weapon(subItemKey, baseAttackBonus, abilitiesMap, size);
	    return addFeature(weapon);
	}
	
	public WeaponCollection(FeaturesCollection parent, String key,
			SimpleVariable baseAttackBonus, Map<String, Ability> abilitiesMap, FixedNumericFeature size) {
		super(parent, key, Weapon.class);
		this.baseAttackBonus = baseAttackBonus;
		this.abilitiesMap = abilitiesMap;
		this.size = size;
	}

	public WeaponCollection(String key,
			SimpleVariable baseAttackBonus, Map<String, Ability> abilitiesMap, FixedNumericFeature size) {
		super(key, Weapon.class);
		this.baseAttackBonus = baseAttackBonus;
		this.abilitiesMap = abilitiesMap;
		this.size = size;
	}

	public WeaponCollection(SimpleVariable baseAttackBonus, Map<String, Ability> abilitiesMap, FixedNumericFeature size) {
		this.baseAttackBonus = baseAttackBonus;
		this.abilitiesMap = abilitiesMap;
		this.size = size;
	}

}
