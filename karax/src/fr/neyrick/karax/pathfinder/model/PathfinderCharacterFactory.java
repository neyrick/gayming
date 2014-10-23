package fr.neyrick.karax.pathfinder.model;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import fr.neyrick.karax.entities.generic.MetaCharacter;
import fr.neyrick.karax.model.CharacterFactory;
import fr.neyrick.karax.model.FixedNumericFeature;
import fr.neyrick.karax.model.GameCharacter;
import fr.neyrick.karax.model.Ruleset;
import fr.neyrick.karax.model.SimpleVariable;
import fr.neyrick.karax.model.StaticFeaturesCollection;
import fr.neyrick.karax.model.StringFeature;
import fr.neyrick.karax.model.VariableFeaturesCollection;

@Ruleset("PATHFINDER_1.0")
@RequestScoped
public class PathfinderCharacterFactory extends CharacterFactory {

	public static final String[] ABILITIES = { "STR", "DEX", "CON", "INT", "WIS", "CHA"};
	
	@Inject
	private FacesContext facesContext;
	
	private ResourceBundle resourceBundle;
	
	public ResourceBundle getResourceBundle(Locale locale) {
		if (resourceBundle == null ) resourceBundle = ResourceBundle.getBundle("fr.neyrick.karax.pathfinder.model.pathfinder", locale);
		return resourceBundle;
	}

	@Override
	protected GameCharacter initCharacter(MetaCharacter metaCharacter) {
		
		PathfinderCharacter character = new PathfinderCharacter();
		
		// GENERAL INFORMATION
		
		character.setHeight(registerListener(new StringFeature("HEIGHT")));
		character.setWeight(registerListener(new StringFeature("WEIGHT")));
		character.setSize(registerListener(new StringFeature("SIZE")));
		character.setHair(registerListener(new StringFeature("HAIR")));
		character.setEyes(registerListener(new StringFeature("EYES")));
		character.setRace(registerListener(new StringFeature("RACE")));
		character.setSubrace(registerListener(new StringFeature("SUBRACE")));
		character.setDeity(registerListener(new StringFeature("DEITY")));
		character.setAlignment(registerListener(new StringFeature("ALIGNMENT")));
		character.setHomeland(registerListener(new StringFeature("HOMELAND")));
		character.setGender(registerListener(new StringFeature("GENDER")));
		character.setAge(registerListener(new FixedNumericFeature("ACTUAL_AGE")));
		
		VariableFeaturesCollection<SimpleVariable> levels = new VariableFeaturesCollection<>("LEVEL", SimpleVariable.class);
	    character.setLevels(registerListener(levels));
	    character.setSpecialAbilities(registerListener(registerListener(new StaticFeaturesCollection<StringFeature>("SP_ABILITY", StringFeature.class))));
		SimpleVariable armorPenaltyVariable = new SimpleVariable("ARMOR_PENALTY");
		

		// Abilities

		AbilityCalculator abilityCalculator = new AbilityCalculator();
		VariableFeaturesCollection<Ability> abilities = registerListener(new VariableFeaturesCollection<Ability>("ABILITY", Ability.class));
		Map<String, Ability> abilitiesMap = new HashMap<String, Ability>(ABILITIES.length);
		Map<String, SkillCalculator> skillCalculatorsMap = new HashMap<String, SkillCalculator>(ABILITIES.length);
		Ability tempAbility;
		for(String ability : ABILITIES) {
			tempAbility = new Ability(ability, abilityCalculator);
			abilities.addFeature(tempAbility);
			abilitiesMap.put(ability, tempAbility);
			skillCalculatorsMap.put(ability, new SkillCalculator(tempAbility, armorPenaltyVariable));
		}		
		character.setAbilities(abilities);

		// Skills
		
		PathfinderSkillList skills = registerListener(new PathfinderSkillList("SKILL", skillCalculatorsMap));
        character.setSkills(skills)	;	
		skills.addSkill("ACROBATICS", "DEX", false, true);
		skills.addSkill("APPRAISE", "INT", false, true);
		skills.addSkill("BLUFF", "CHA", false, true);
		skills.addSkill("CLIMB", "STR", false, true);
		skills.addSkill("DIPLOMACY", "CHA", false, true);
		skills.addSkill("DISABLE_DEVICE", "DEX", false, true);
		skills.addSkill("DISGUISE", "CHA", false, true);
		skills.addSkill("ESCAPE_ARTIST", "DEX", false, true);
		skills.addSkill("FLY", "DEX", false, true);
		skills.addSkill("HANDLE_ANIMAL", "DEX", false, true);
		skills.addSkill("HEAL", "WIS", false, true);
		skills.addSkill("INTIMIDATE", "CHA", false, true);
		skills.addSkill("KN:ARCANA", "INT", false, true);
		skills.addSkill("KN:DUNGEONEERING", "INT", false, true);
		skills.addSkill("KN:ENGINEERING", "INT", false, true);
		skills.addSkill("KN:GEOGRAPHIE", "INT", false, true);
		skills.addSkill("KN:HISTORY", "INT", false, true);
		skills.addSkill("KN:LOCAL", "INT", false, true);
		skills.addSkill("KN:NATURE", "INT", false, true);
		skills.addSkill("KN:NOBILITY", "INT", false, true);
		skills.addSkill("KN:PLANES", "INT", false, true);
		skills.addSkill("KN:RELIGION", "INT", false, true);
		skills.addSkill("LINGUISTICS", "INT", false, true);
		skills.addSkill("PERCEPTION", "WIS", false, true);
		skills.addSkill("PERFORM", "CHA", false, true);
		skills.addSkill("RIDE", "DEX", false, true);
		skills.addSkill("SENSE_MOTIVE", "WIS", false, true);
		skills.addSkill("SLEIGHT_OF_HAND", "DEX", false, true);
		skills.addSkill("SPELLCRAFT", "INT", false, true);
		skills.addSkill("STEALTH", "DEX", false, true);
		skills.addSkill("SURVIVAL", "WIS", false, true);
		skills.addSkill("SWIM", "STR", false, true);
		skills.addSkill("USE_MAGIC_DEVICE", "CHA", false, true);
		
		// Saves
		
		VariableFeaturesCollection<SimpleVariable> saves = registerListener(new VariableFeaturesCollection<>("SAVE", SimpleVariable.class));
		character.setSaves(saves);
		saves.addFeature(new SimpleVariable("FOR", new SaveCalculator(abilitiesMap.get("CON"))));
		saves.addFeature(new SimpleVariable("REF", new SaveCalculator(abilitiesMap.get("DEX"))));
		saves.addFeature(new SimpleVariable("WIL", new SaveCalculator(abilitiesMap.get("WIS"))));
		
		// Misc
		
		character.setArmorclass(registerListener(new ArmorClass("AC", new ArmorClassCalculator(abilitiesMap.get("DEX")))));
		character.setInitiative(registerListener(new SimpleVariable("INITIATIVE", new InitiativeCalculator((abilitiesMap.get("DEX"))))));
		character.setHitpoints(registerListener(new SimpleVariable("HP", new HitPointCalculator(abilitiesMap.get("CON"), levels))));
		character.setArmorpenalty(registerListener(armorPenaltyVariable));
		character.setFavoredClasses(registerListener(new StaticFeaturesCollection<StringFeature>("FAVORED_CLASS", StringFeature.class)));
		character.setFeats(registerListener(new StaticFeaturesCollection<StringFeature>("FEAT", StringFeature.class)));
		character.setTraits(registerListener(new StaticFeaturesCollection<StringFeature>("TRAIT", StringFeature.class)));
		character.setRaceTraits(registerListener(new StaticFeaturesCollection<StringFeature>("RACE_TRAIT", StringFeature.class)));
		character.setGear(registerListener(new StaticFeaturesCollection<StringFeature>("GEAR", StringFeature.class)));
		character.setKnownSpells(registerListener(new StaticFeaturesCollection<StringFeature>("KNOWN_SPELL", StringFeature.class)));

		// Spell slots
		SpellSlotsMap spellSlots = registerListener(new SpellSlotsMap("SPELL_SLOT"));
		character.setSpellSlots(spellSlots);
		
		character.setDomains(registerListener(new StaticFeaturesCollection<StringFeature>("DOMAIN", StringFeature.class)));
		
		
		// Gear
		
		character.setGear(registerListener(new StaticFeaturesCollection<StringFeature>("GEAR", StringFeature.class)));
		
		
		return character;
	}

	@Override
	protected void filterEdits(MetaCharacter character,
			Map<String, String[]> filterMap) {
	}
	
}
