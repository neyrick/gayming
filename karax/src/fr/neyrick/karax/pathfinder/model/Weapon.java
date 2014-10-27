package fr.neyrick.karax.pathfinder.model;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.entities.generic.CharacterEdit;
import fr.neyrick.karax.model.CharacterFeature;
import fr.neyrick.karax.model.ComplexFeatureCollection;
import fr.neyrick.karax.model.FeaturesCollection;
import fr.neyrick.karax.model.SimpleBonus;
import fr.neyrick.karax.model.SimpleVariable;
import fr.neyrick.karax.model.StringFeature;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Weapon extends ComplexFeatureCollection {

	public static final String SUBKEY_NAME="NAME";
	public static final String SUBKEY_ATTACKBONUS="ATTACKBONUS";
	public static final String SUBKEY_ATTACKABILITY="ATTACKABILITY";
	public static final String SUBKEY_DAMAGEBASE="DAMAGEBASE";
	public static final String SUBKEY_DAMAGEBONUS="DAMAGEBONUS";
	public static final String SUBKEY_DAMAGEABILITY="DAMAGEABILITY";
	public static final String SUBKEY_CRITICAL="CRITICAL";
	public static final String SUBKEY_TYPE="TYPE";
	public static final String SUBKEY_RANGE="RANGE";
	public static final String SUBKEY_AMMO="AMMO";
	
	private SimpleVariable baseAttackBonus;
	
	private Map<String, Ability> abilitiesMap;
	
	private StringFeature name;
	
	private StringFeature damageBase;
	
	private SimpleBonus damageBonus;
	
	private SimpleBonus attackBonus;

	private StringFeature attackAbilityKey;

	private StringFeature damageAbilityKey;

	private StringFeature critical;
	
	private StringFeature type;
	
	private StringFeature range;
	
	private StringFeature ammo;
	

	@XmlElement
	public StringFeature getName() {
		return name;
	}

	@XmlElement
	public String getDamage() {
		int bonus = damageBonus.getNumericValue().intValue();
		if (bonus == 0) return damageBase.getValue();
		else return damageBase.getValue() + " + " + damageBonus.getValue();
	}

	public StringFeature getDamageBase() {
		return damageBase;
	}

	public SimpleBonus getDamageBonus() {
		return damageBonus;
	}

	@XmlElement
	public SimpleBonus getAttackBonus() {
		return attackBonus;
	}

	@XmlElement
	public StringFeature getCritical() {
		return critical;
	}

	@XmlElement
	public StringFeature getType() {
		return type;
	}

	@XmlElement
	public StringFeature getRange() {
		return range;
	}

	@XmlElement
	public StringFeature getAmmo() {
		return ammo;
	}

	private void initFields() {
		name = new StringFeature(this, SUBKEY_NAME);
		attackAbilityKey = new StringFeature(this, SUBKEY_ATTACKABILITY);
		damageAbilityKey = new StringFeature(this, SUBKEY_DAMAGEABILITY);
		damageBase = new StringFeature(this, SUBKEY_DAMAGEBASE);
		damageBonus = new SimpleBonus(this, SUBKEY_DAMAGEBONUS, new WeaponDamageCalculator(abilitiesMap, damageAbilityKey));
		attackBonus = new SimpleBonus(this, SUBKEY_ATTACKBONUS, new WeaponAttackCalculator(abilitiesMap, baseAttackBonus, attackAbilityKey));
		critical = new StringFeature(this, SUBKEY_CRITICAL);
		type = new StringFeature(this, SUBKEY_TYPE);
		range = new StringFeature(this, SUBKEY_RANGE);
		ammo = new StringFeature(this, SUBKEY_AMMO);
	}
	
	public Weapon(FeaturesCollection parent, String key, SimpleVariable baseAttackBonus, Map<String, Ability> abilitiesMap) {
		super(parent, key);
		this.abilitiesMap = abilitiesMap;
		this.baseAttackBonus = baseAttackBonus;
		initFields();
	}

	public Weapon(String key, SimpleVariable baseAttackBonus, Map<String, Ability> abilitiesMap) {
		super(key);
		this.abilitiesMap = abilitiesMap;
		this.baseAttackBonus = baseAttackBonus;
		initFields();
	}

	public Weapon() {
		super(null);
		initFields();
	}

	@Override
	protected CharacterFeature createSubFeature(String subItemKey,
			CharacterEdit edit) {
		switch(subItemKey) {
		case SUBKEY_ATTACKABILITY: return attackAbilityKey;
		case SUBKEY_DAMAGEABILITY: return damageAbilityKey;
		case SUBKEY_NAME:return name;
		case SUBKEY_DAMAGEBASE:return damageBase;
		case SUBKEY_DAMAGEBONUS:return damageBonus;
		case SUBKEY_ATTACKBONUS:return attackBonus;
		case SUBKEY_CRITICAL:return critical;
		case SUBKEY_TYPE:return type;
		case SUBKEY_RANGE:return range;
		case SUBKEY_AMMO:return ammo;
		default:return null;				
	}
	}

}
