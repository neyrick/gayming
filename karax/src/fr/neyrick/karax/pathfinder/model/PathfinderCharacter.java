package fr.neyrick.karax.pathfinder.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.model.FixedNumericFeature;
import fr.neyrick.karax.model.GameCharacter;
import fr.neyrick.karax.model.SimpleVariable;
import fr.neyrick.karax.model.StaticFeaturesCollection;
import fr.neyrick.karax.model.StringFeature;
import fr.neyrick.karax.model.VariableFeaturesCollection;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class PathfinderCharacter extends GameCharacter {

	public static final String KEY_STR = "STR";
	public static final String KEY_DEX = "DEX";
	public static final String KEY_CON = "CON";
	public static final String KEY_WIS = "WIS";
	public static final String KEY_INT = "INT";
	public static final String KEY_CHA = "CHA";
	
	private StringFeature size;

	private StringFeature height;

	private StringFeature weight;

	private StringFeature hair;

	private StringFeature eyes;

	private StringFeature race;
	
	private StringFeature subrace;
	
	private StringFeature deity;
	
	private StringFeature alignment;
	
	private StringFeature homeland;
	
	private StringFeature gender;

	private FixedNumericFeature age;

	private SimpleVariable armorpenalty;
	
	private StaticFeaturesCollection<StringFeature> favoredClasses;

	private StaticFeaturesCollection<StringFeature> feats;
	
	private StaticFeaturesCollection<StringFeature> traits;

	private StaticFeaturesCollection<StringFeature> raceTraits;

	private StaticFeaturesCollection<StringFeature> gear;

	private StaticFeaturesCollection<StringFeature> knownSpells;
	
	private StaticFeaturesCollection<StringFeature> domains;
	
	private VariableFeaturesCollection<SimpleVariable> spellSlots;

	private VariableFeaturesCollection<Ability> abilities;
	
	private VariableFeaturesCollection<Skill> skills;
	
	private VariableFeaturesCollection<SimpleVariable> levels;
	
	private StaticFeaturesCollection<StringFeature> specialAbilities;


	@XmlElement
	public StringFeature getSize() {
		return size;
	}

	public void setSize(StringFeature size) {
		this.size = size;
	}

	@XmlElement
	public StringFeature getHeight() {
		return height;
	}

	public void setHeight(StringFeature height) {
		this.height = height;
	}

	@XmlElement
	public StringFeature getWeight() {
		return weight;
	}

	public void setWeight(StringFeature weight) {
		this.weight = weight;
	}

	@XmlElement
	public StringFeature getHair() {
		return hair;
	}

	public void setHair(StringFeature hair) {
		this.hair = hair;
	}

	@XmlElement
	public StringFeature getEyes() {
		return eyes;
	}

	public void setEyes(StringFeature eyes) {
		this.eyes = eyes;
	}

	@XmlElement
	public StringFeature getRace() {
		return race;
	}

	public void setRace(StringFeature race) {
		this.race = race;
	}

	@XmlElement
	public StringFeature getSubrace() {
		return subrace;
	}

	public void setSubrace(StringFeature subrace) {
		this.subrace = subrace;
	}

	@XmlElement
	public StringFeature getDeity() {
		return deity;
	}

	public void setDeity(StringFeature deity) {
		this.deity = deity;
	}

	@XmlElement
	public StringFeature getAlignment() {
		return alignment;
	}

	public void setAlignment(StringFeature alignment) {
		this.alignment = alignment;
	}

	@XmlElement
	public StringFeature getHomeland() {
		return homeland;
	}
	
	public void setHomeland(StringFeature homeland) {
		this.homeland = homeland;
	}

	@XmlElement
	public StringFeature getGender() {
		return gender;
	}

	public void setGender(StringFeature gender) {
		this.gender = gender;
	}

	@XmlElement
	public FixedNumericFeature getAge() {
		return age;
	}

	public void setAge(FixedNumericFeature age) {
		this.age = age;
	}	
	
	@XmlElement
	public SimpleVariable getArmorpenalty() {
		return armorpenalty;
	}

	public void setArmorpenalty(SimpleVariable armorpenalty) {
		this.armorpenalty = armorpenalty;
	}

	@XmlElementWrapper(name="favoredclasses")
	@XmlElement(name="favoredclass")
	public Collection<StringFeature> getFavoredClasses() {
		return favoredClasses.getActualSubFeatures();
	}

	public void setFavoredClasses(
			StaticFeaturesCollection<StringFeature> favoredClasses) {
		this.favoredClasses = favoredClasses;
	}

	@XmlElementWrapper(name="feats")
	@XmlElement(name="feat")
	public Collection<StringFeature> getFeats() {
		return feats.getActualSubFeatures();
	}

	public void setFeats(StaticFeaturesCollection<StringFeature> feats) {
		this.feats = feats;
	}

	@XmlElementWrapper(name="racetraits")
	@XmlElement(name="racetrait")
	public Collection<StringFeature> getRaceTraits() {
		return raceTraits.getActualSubFeatures();
	}

	public void setRaceTraits(StaticFeaturesCollection<StringFeature> raceTraits) {
		this.raceTraits = raceTraits;
	}

	@XmlElementWrapper(name="knownspells")
	@XmlElement(name="knownspell")
	public Collection<StringFeature> getKnownSpells() {
		return knownSpells.getActualSubFeatures();
	}

	public void setKnownSpells(StaticFeaturesCollection<StringFeature> knownSpells) {
		this.knownSpells = knownSpells;
	}

	@XmlElementWrapper(name="domains")
	@XmlElement(name="domain")
	public Collection<StringFeature> getDomains() {
		return domains.getActualSubFeatures();
	}

	public void setDomains(StaticFeaturesCollection<StringFeature> domains) {
		this.domains = domains;
	}

	@XmlElementWrapper(name="skills")
	@XmlElement(name="skill")
	public Collection<Skill> getSkills() {
		return skills.getActualSubFeatures();
	}

	public void setSkills(VariableFeaturesCollection<Skill> skills) {
		this.skills = skills;
	}

	@XmlElementWrapper(name="traits")
	@XmlElement(name="trait")
	public Collection<StringFeature> getTraits() {
		return traits.getActualSubFeatures();
	}

	public void setTraits(StaticFeaturesCollection<StringFeature> traits) {
		this.traits = traits;
	}

	@XmlElementWrapper(name="gear")
	@XmlElement(name="item")
	public Collection<StringFeature> getGear() {
		return gear.getActualSubFeatures();
	}

	public void setGear(StaticFeaturesCollection<StringFeature> gear) {
		this.gear = gear;
	}
	
	@XmlElementWrapper(name="abilities")
	@XmlElement(name="ability")
	public Collection<Ability> getAbilities() {
		return abilities.getActualSubFeatures();
	}

	public void setAbilities(VariableFeaturesCollection<Ability> abilities) {
		this.abilities = abilities;
	}

	@XmlElementWrapper(name="spellSlots")
	@XmlElement(name="skill")
	public Collection<SimpleVariable> getSpellSlots() {
		return spellSlots.getActualSubFeatures();
	}

	public void setSpellSlots(VariableFeaturesCollection<SimpleVariable> spellSlots) {
		this.spellSlots = spellSlots;
	}

	@XmlElementWrapper(name="levels")
	@XmlElement(name="level")
	public Collection<SimpleVariable> getLevels() {
		return levels.getActualSubFeatures();
	}

	public void setLevels(VariableFeaturesCollection<SimpleVariable> levels) {
		this.levels = levels;
	}

	@XmlElementWrapper(name="specialAbilities")
	@XmlElement(name="specialAbility")
	public Collection<StringFeature> getSpecialAbilities() {
		return specialAbilities.getActualSubFeatures();
	}

	public void setSpecialAbilities(StaticFeaturesCollection<StringFeature> specialAbilities) {
		this.specialAbilities = specialAbilities;
	}

}
