package fr.neyrick.karax.pathfinder.model;

import java.util.Collection;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.entities.generic.CharacterEdit;
import fr.neyrick.karax.model.CharacterFeature;
import fr.neyrick.karax.model.ComplexFeatureCollection;
import fr.neyrick.karax.model.FeaturesCollection;
import fr.neyrick.karax.model.FixedNumericFeature;
import fr.neyrick.karax.model.SimpleVariable;
import fr.neyrick.karax.model.StaticFeaturesCollection;
import fr.neyrick.karax.model.StringFeature;
import fr.neyrick.karax.model.VariableFeaturesCollection;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Spellcasting extends ComplexFeatureCollection {

	public static final String SUBKEY_LEVEL="LEVEL";
	public static final String SUBKEY_DOMAINS="DOMAIN";
	public static final String SUBKEY_KNOWNSPELLSCOUNT="KNOWNSPELLSCOUNT";
	public static final String SUBKEY_KNOWNSPELLS="KNOWNSPELL";
	public static final String SUBKEY_ABILITYKEY="ABILITY";
	public static final String SUBKEY_SPELLSLOT="SPELLSLOT";
	
	private SimpleVariable level;

	private StringFeature abilityKey;

	private Map<String, Ability> abilitiesMap;
	
	private StaticFeaturesCollection<StringFeature> domains;

	private VariableFeaturesCollection<SimpleVariable> knownSpellsCount;
	
	private StaticFeaturesCollection<FixedNumericFeature> knownSpells;

	private VariableFeaturesCollection<SimpleVariable> spellSlots;


	@XmlAttribute
	public String getCasterClass() {
		return tryTranslation(this.getKey());
	}
	
	@XmlAttribute
	public int getLevel() {
		return level.getNumericValue().intValue();
	}
	
	@XmlAttribute
	public String getAbilityKey() {
		return abilityKey.getValue();
	}

	@XmlElementWrapper(name="domains")
	@XmlElement(name="domain")
	public Collection<StringFeature> getDomains() {
		return domains.getActualSubFeatures();
	}

	@XmlElementWrapper(name="knownspellscount")
	@XmlElement(name="level")
	public Collection<SimpleVariable> getKnownSpellsCount() {
		return knownSpellsCount.getActualSubFeatures();
	}

	@XmlElementWrapper(name="knownspells")
	@XmlElement(name="knownspell")
	public Collection<FixedNumericFeature> getKnownSpells() {
		return knownSpells.getActualSubFeatures();
	}

	@XmlElementWrapper(name="spellslots")
	@XmlElement(name="level")
	public Collection<SimpleVariable> getSpellSlots() {
		return spellSlots.getActualSubFeatures();
	}

	private void initFields() {
		level = new SimpleVariable(this, SUBKEY_LEVEL);
		abilityKey = new StringFeature(this,  SUBKEY_ABILITYKEY);
		domains = new StaticFeaturesCollection<>(this, SUBKEY_DOMAINS, StringFeature.class);
		knownSpellsCount = new VariableFeaturesCollection<>(this, SUBKEY_KNOWNSPELLSCOUNT, SimpleVariable.class);
		knownSpells = new StaticFeaturesCollection<>(this,  SUBKEY_KNOWNSPELLS, FixedNumericFeature.class);
		spellSlots = new VariableFeaturesCollection<SimpleVariable>(this, SUBKEY_SPELLSLOT, SimpleVariable.class, new SpellSlotsCalculator(abilityKey, abilitiesMap));
	}
	
	public Spellcasting(FeaturesCollection parent, String key, Map<String, Ability> abilitiesMap) {
		super(parent, key);
		this.abilitiesMap = abilitiesMap;
		initFields();
	}
	
	public Spellcasting(String key, Map<String, Ability> abilitiesMap) {
		super(key);
		this.abilitiesMap = abilitiesMap;
		initFields();
	}

	public Spellcasting(Map<String, Ability> abilitiesMap) {
		super(null);
		this.abilitiesMap = abilitiesMap;
		initFields();
	}

	public Spellcasting() {
		super(null);
		initFields();
	}

	@Override
	protected CharacterFeature createSubFeature(String subItemKey, CharacterEdit edit) {
		switch(subItemKey) {
			case SUBKEY_LEVEL:return level;
			case SUBKEY_DOMAINS:return domains;
			case SUBKEY_KNOWNSPELLSCOUNT:return knownSpellsCount;
			case SUBKEY_KNOWNSPELLS:return knownSpells;
			case SUBKEY_ABILITYKEY:return abilityKey;
			case SUBKEY_SPELLSLOT:return spellSlots;
			default:return null;				
		}
	}

}
