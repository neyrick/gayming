package fr.neyrick.karax.pathfinder.model;

import java.util.Map;

import fr.neyrick.karax.entities.generic.CharacterEdit;
import fr.neyrick.karax.model.FeaturesCollection;
import fr.neyrick.karax.model.SimpleVariable;
import fr.neyrick.karax.model.StaticFeaturesCollection;

public class WeaponCollection extends StaticFeaturesCollection<Weapon> {

	private SimpleVariable baseAttackBonus;
	private Map<String, Ability> abilitiesMap;

	@Override
	public Weapon addFeature(String subItemKey, CharacterEdit edit) {
	    Weapon weapon = new Weapon(subItemKey, baseAttackBonus, abilitiesMap);
	    return addFeature(weapon);
	}
	
	public WeaponCollection(FeaturesCollection parent, String key,
			SimpleVariable baseAttackBonus, Map<String, Ability> abilitiesMap) {
		super(parent, key, Weapon.class);
		this.baseAttackBonus = baseAttackBonus;
		this.abilitiesMap = abilitiesMap;
	}

	public WeaponCollection(String key,
			SimpleVariable baseAttackBonus, Map<String, Ability> abilitiesMap) {
		super(key, Weapon.class);
		this.baseAttackBonus = baseAttackBonus;
		this.abilitiesMap = abilitiesMap;
	}

	public WeaponCollection(SimpleVariable baseAttackBonus, Map<String, Ability> abilitiesMap) {
		this.baseAttackBonus = baseAttackBonus;
		this.abilitiesMap = abilitiesMap;
	}

}
