package fr.neyrick.karax.pathfinder.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.entities.generic.CharacterEdit;
import fr.neyrick.karax.model.CharacterFeature;
import fr.neyrick.karax.model.ComplexFeatureCollection;
import fr.neyrick.karax.model.FeaturesCollection;
import fr.neyrick.karax.model.FixedBonus;
import fr.neyrick.karax.model.FixedNumericFeature;
import fr.neyrick.karax.model.StringFeature;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Armor extends ComplexFeatureCollection {

	public static final String SUBKEY_NAME="NAME";
	public static final String SUBKEY_ACBONUS="ACBONUS";
	public static final String SUBKEY_MAXDEX="MAXDEX";
	public static final String SUBKEY_PENALTY="PENALTY";
	public static final String SUBKEY_SPELLFAILURE="SPELLFAILURE";
	
	private StringFeature name;
	
	private FixedBonus acBonus;
	
	private FixedBonus maxDex;
	
	private FixedBonus penalty;

	private FixedNumericFeature spellFailure;
	
	@XmlAttribute
	public String getType() {
		return acBonus.getType();
	}

	@XmlElement
	public StringFeature getName() {
		return name;
	}

	@XmlElement
	public FixedBonus getAcBonus() {
		return acBonus;
	}

	@XmlElement
	public FixedBonus getMaxDex() {
		return maxDex;
	}

	@XmlElement
	public FixedBonus getPenalty() {
		return penalty;
	}

	@XmlElement
	public FixedNumericFeature getSpellFailure() {
		return spellFailure;
	}

	private void initFields() {
		name = new StringFeature(this, SUBKEY_NAME);
		acBonus = new FixedBonus(this, SUBKEY_ACBONUS);
		maxDex = new FixedBonus(this, SUBKEY_MAXDEX);
		penalty = new FixedBonus(this, SUBKEY_PENALTY);
		spellFailure = new FixedNumericFeature(this, SUBKEY_SPELLFAILURE);
	}
	
	public Armor(FeaturesCollection parent, String key) {
		super(parent, key);
		initFields();
	}

	public Armor(String key) {
		super(key);
		initFields();
	}

	public Armor() {
		super(null);
		initFields();
	}

	@Override
	protected CharacterFeature createSubFeature(String subItemKey,
			CharacterEdit edit) {
		switch(subItemKey) {
		case SUBKEY_NAME:return name;
		case SUBKEY_ACBONUS:return acBonus;
		case SUBKEY_MAXDEX:return maxDex;
		case SUBKEY_PENALTY:return penalty;
		case SUBKEY_SPELLFAILURE:return spellFailure;
		default:return null;				
	}
	}

}
