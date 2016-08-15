package fr.neyrick.karax.pathfinder.model;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;

import fr.neyrick.karax.entities.generic.MetaCharacter;
import fr.neyrick.karax.model.CharacterFactory;
import fr.neyrick.karax.model.FixedNumericFeature;
import fr.neyrick.karax.model.GameCharacter;
import fr.neyrick.karax.model.Ruleset;
import fr.neyrick.karax.model.SimpleBonus;
import fr.neyrick.karax.model.SimpleVariable;
import fr.neyrick.karax.model.StaticFeaturesCollection;
import fr.neyrick.karax.model.StringFeature;
import fr.neyrick.karax.model.VariableFeaturesCollection;

@Ruleset("PATHFINDER_1.0")
@RequestScoped
public class PathfinderCharacterFactory extends CharacterFactory {

	public static final String[] ABILITIES = { "STR", "DEX", "CON", "INT", "WIS", "CHA"};
	
//	@Inject
//	private FacesContext facesContext;
	
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
		FixedNumericFeature size = new FixedNumericFeature("SIZE");
		character.setSize(registerListener(size));
		character.setHair(registerListener(new StringFeature("HAIR")));
		character.setEyes(registerListener(new StringFeature("EYES")));
		character.setRace(registerListener(new StringFeature("RACE")));
		character.setSubrace(registerListener(new StringFeature("SUBRACE")));
		character.setDeity(registerListener(new StringFeature("DEITY")));
		character.setAlignment(registerListener(new StringFeature("ALIGNMENT")));
		character.setHomeland(registerListener(new StringFeature("HOMELAND")));
		character.setSex(registerListener(new StringFeature("SEX")));
		character.setAge(registerListener(new FixedNumericFeature("AGE")));

		VariableFeaturesCollection<SimpleVariable> levels = new VariableFeaturesCollection<>("LEVEL", SimpleVariable.class);
	    character.setLevels(registerListener(levels));
	    character.setSpecialAbilities(registerListener(registerListener(new StaticFeaturesCollection<StringFeature>("SP_ABILITY", StringFeature.class))));
		

		// Abilities

		StaticFeaturesCollection<FixedNumericFeature> gear = new StaticFeaturesCollection<>("GEAR", FixedNumericFeature.class);
		StaticFeaturesCollection<Armor> armors = new StaticFeaturesCollection<>("ARMOR", Armor.class);
		Load load = null;
		AbilityCalculator abilityCalculator = new AbilityCalculator();
		VariableFeaturesCollection<Ability> abilities = registerListener(new VariableFeaturesCollection<Ability>("ABILITY", Ability.class));
		Map<String, Ability> abilitiesMap = new HashMap<String, Ability>(ABILITIES.length);
		Map<String, SkillCalculator> skillCalculatorsMap = new HashMap<String, SkillCalculator>(ABILITIES.length);
		Ability tempAbility;
		for(String ability : ABILITIES) {
			if ("DEX".equals(ability)) {
				AbilityCalculator dexCalculator = new AbilityCalculator(load);
				tempAbility = new Ability(ability, dexCalculator);
			}
			else {
				tempAbility = new Ability(ability, abilityCalculator);
			}
			if ("STR".equals(ability)) {
				load = new Load("LOAD", new LoadCalculator(tempAbility, gear, armors));
			}
			abilities.addFeature(tempAbility);
			abilitiesMap.put(ability, tempAbility);
			skillCalculatorsMap.put(ability, new SkillCalculator(tempAbility, load));
		}		
		character.setAbilities(abilities);
		character.setSpeed(registerListener(new SimpleVariable("SPEED", new SpeedCalculator(load))));

		// Skills
		
		PathfinderSkillList skills = registerListener(new PathfinderSkillList("SKILL", skillCalculatorsMap));
        character.setSkills(skills)	;	
		skills.addSkill("ACROBATICS", "DEX", false, true);
		skills.addSkill("APPRAISE", "INT", false, false);
		skills.addSkill("BLUFF", "CHA", false, false);
		skills.addSkill("CLIMB", "STR", false, true);
		skills.addSkill("DIPLOMACY", "CHA", false, false);
		skills.addSkill("DISABLE_DEVICE", "DEX", true, true);
		skills.addSkill("DISGUISE", "CHA", false, false);
		skills.addSkill("ESCAPE_ARTIST", "DEX", false, true);
		skills.addSkill("FLY", "DEX", false, true);
		skills.addSkill("HANDLE_ANIMAL", "CHA", false, false);
		skills.addSkill("HEAL", "WIS", false, false);
		skills.addSkill("INTIMIDATE", "CHA", false, false);
		skills.addSkill("KN_ARCANA", "INT", true, false);
		skills.addSkill("KN_DUNGEONEERING", "INT", true, false);
		skills.addSkill("KN_ENGINEERING", "INT", true, false);
		skills.addSkill("KN_GEOGRAPHY", "INT", true, false);
		skills.addSkill("KN_HISTORY", "INT", true, false);
		skills.addSkill("KN_LOCAL", "INT", true, false);
		skills.addSkill("KN_NATURE", "INT", true, false);
		skills.addSkill("KN_NOBILITY", "INT", true, false);
		skills.addSkill("KN_PLANES", "INT", true, false);
		skills.addSkill("KN_RELIGION", "INT", true, false);
		skills.addSkill("LINGUISTICS", "INT", true, false);
		skills.addSkill("PERCEPTION", "WIS", false, false);
		skills.addSkill("RIDE", "DEX", false, true);
		skills.addSkill("SENSE_MOTIVE", "WIS", false, false);
		skills.addSkill("SLEIGHT_OF_HAND", "DEX", true, true);
		skills.addSkill("SPELLCRAFT", "INT", true, false);
		skills.addSkill("STEALTH", "DEX", false, true);
		skills.addSkill("SURVIVAL", "WIS", false, false);
		skills.addSkill("SWIM", "STR", false, true);
		skills.addSkill("USE_MAGIC_DEVICE", "CHA", true, false);
		
		// Saves
		
		VariableFeaturesCollection<Save> saves = registerListener(new VariableFeaturesCollection<>("SAVE", Save.class));
		character.setSaves(saves);
		saves.addFeature(new Save("FOR", new SaveCalculator(abilitiesMap.get("CON"))));
		saves.addFeature(new Save("REF", new SaveCalculator(abilitiesMap.get("DEX"))));
		saves.addFeature(new Save("WIL", new SaveCalculator(abilitiesMap.get("WIS"))));
		
		// Misc
		SimpleBonus bab = new SimpleBonus("BAB");
		
		character.setBaseAttackBonus(registerListener(bab));
		character.setManeuverAttack(registerListener(new SimpleBonus("CMB", new ManeuverAttackCalculator(bab, abilitiesMap.get("STR"), size))));
		character.setManeuverDefense(registerListener(new SimpleVariable("CMD", new ManeuverDefenseCalculator(bab, abilitiesMap.get("STR"), abilitiesMap.get("DEX"), size))));
		character.setArmorclass(registerListener(new ArmorClass("AC", new ArmorClassCalculator(abilitiesMap.get("DEX"), armors, size))));
		character.setInitiative(registerListener(new Initiative("INITIATIVE", new InitiativeCalculator((abilitiesMap.get("DEX"))))));
		character.setHitpoints(registerListener(new SimpleVariable("HP", new HitPointCalculator(abilitiesMap.get("CON"), levels))));
		character.setFavoredClasses(registerListener(new StaticFeaturesCollection<StringFeature>("FAVORED_CLASS", StringFeature.class)));
		character.setFeats(registerListener(new StaticFeaturesCollection<StringFeature>("FEAT", StringFeature.class)));
		character.setTraits(registerListener(new StaticFeaturesCollection<StringFeature>("TRAIT", StringFeature.class)));
		character.setRaceTraits(registerListener(new StaticFeaturesCollection<StringFeature>("RACE_TRAIT", StringFeature.class)));
		character.setLanguages(registerListener(new StaticFeaturesCollection<StringFeature>("LANGUAGE", StringFeature.class)));

		// Spell casting
		character.setSpellcastings(registerListener(new SpellcastingCollection("SPELLCASTING", abilitiesMap)));
				
		
		// Gear
		
		character.setGear(registerListener(gear));
		character.setLoad(registerListener(load));
		character.setWeapons(registerListener(new WeaponCollection("WEAPON", bab, abilitiesMap, size)));
		character.setArmors(registerListener(armors));

		
		return character;
	}

	@Override
	protected void filterEdits(MetaCharacter character,
			Map<String, String[]> filterMap) {
	}
	
}
