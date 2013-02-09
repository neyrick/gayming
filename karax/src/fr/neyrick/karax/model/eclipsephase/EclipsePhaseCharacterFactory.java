package fr.neyrick.karax.model.eclipsephase;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import fr.neyrick.karax.entities.generic.MetaCharacter;
import fr.neyrick.karax.model.CharacterFactory;
import fr.neyrick.karax.model.CharacterFeature;
import fr.neyrick.karax.model.FeatureCalculator;
import fr.neyrick.karax.model.FixedNumericFeature;
import fr.neyrick.karax.model.GameCharacter;
import fr.neyrick.karax.model.Ruleset;
import fr.neyrick.karax.model.SimpleVariable;
import fr.neyrick.karax.model.StaticFeaturesCollection;
import fr.neyrick.karax.model.StringFeature;
import fr.neyrick.karax.model.VariableFeaturesCollection;
import fr.neyrick.karax.model.VariableNumericFeature;

@Ruleset("ECLIPSE_PHASE_1.0")
@RequestScoped
public class EclipsePhaseCharacterFactory extends CharacterFactory {

	@Inject
	private FacesContext facesContext;
	
	private ResourceBundle resourceBundle;
	
	public ResourceBundle getResourceBundle(Locale locale) {
		if (resourceBundle == null ) resourceBundle = ResourceBundle.getBundle("fr.neyrick.karax.model.eclipsephase.eclipsephase", locale);
		return resourceBundle;
	}

	@Override
	protected GameCharacter initCharacter(MetaCharacter metaCharacter) {
		
		EclipsePhaseCharacter character = new EclipsePhaseCharacter();
		
		// GENERAL INFORMATION
		
		character.setBackground(registerListener(new StringFeature("BACKGROUND")));
		character.setFaction(registerListener(new StringFeature("FACTION")));
		character.setActualAge(registerListener(new FixedNumericFeature("ACTUAL_AGE")));
		character.setGender(registerListener(new StringFeature("GENDER")));
		
		// TRAITS
		
		character.setTraits(registerListener(new StaticFeaturesCollection<>("TRAIT", Trait.class)));
		
		// MORPH
		
		Morph currentMorph = new Morph();
		currentMorph.setOrigin(registerListener(new StringFeature("MORPH_ORIGIN")));
		currentMorph.setType(registerListener(new StringFeature("MORPH_TYPE")));
		currentMorph.setDurability(registerListener(new SimpleVariable("MORPH_DURABILITY")));
		currentMorph.setAptitudeMax(registerListener(new FixedNumericFeature("MORPH_APT_MAX")));
		currentMorph.setSpeedModifier(registerListener(new FixedNumericFeature("MORPH_SPEED_MOD")));
		currentMorph.setEnhancements(registerListener(new StaticFeaturesCollection<>("MORPH_ENH", StringFeature.class)));
		currentMorph.setTraits(registerListener(new StaticFeaturesCollection<>("MORPH_TRAIT", Trait.class)));
		currentMorph.setMovementRate(registerListener(new StringFeature("MORPH_MOVEMENT")));
		character.setCurrentMorph(currentMorph);
		
		// APTITUDES

		AptitudeCalculator aptitudeCalculator = new AptitudeCalculator();
		VariableFeaturesCollection<Aptitude> aptitudes = registerListener(new VariableFeaturesCollection<>("APT", Aptitude.class));
		Aptitude aptitudeCOG = aptitudes.addFeature(new Aptitude("COG", aptitudeCalculator));
		Aptitude aptitudeCOO = aptitudes.addFeature(new Aptitude("COO", aptitudeCalculator));
		Aptitude aptitudeINT = aptitudes.addFeature(new Aptitude("INT", aptitudeCalculator));
		Aptitude aptitudeSOM = aptitudes.addFeature(new Aptitude("SOM", aptitudeCalculator));
		Aptitude aptitudeWIL = aptitudes.addFeature(new Aptitude("WIL", aptitudeCalculator));
		Aptitude aptitudeREF = aptitudes.addFeature(new Aptitude("REF", aptitudeCalculator));
		Aptitude aptitudeSAV = aptitudes.addFeature(new Aptitude("SAV", aptitudeCalculator));
		character.setAptitudes(aptitudes);
				
		// MOTIVATIONS
		
		character.setMotivations(registerListener(new StaticFeaturesCollection<StringFeature>("MOTIVATION", StringFeature.class)));
		
		// MOXIE
		
		character.setMoxie(registerListener(new SimpleVariable("MOXIE", new FeatureCalculator() {
			
			@Override
			public Number calculate(CharacterFeature feature) {
				VariableNumericFeature targetFeature = (VariableNumericFeature)feature;
				int result = 1;
				result += (targetFeature.getFreebieCost() / 15) + (targetFeature.getExperienceCost() / 15) + (targetFeature.getFreeCost() / 15) + (targetFeature.getModifier());
				if (result > 10) return 10;
				return result;
			}
		})));

		// SKILLS (Active = ASK, Knowledge = KSK, PSI = PSK)
		
		Map<String, SkillCalculator> skillCalculatorsMap = new HashMap<>(7);
		skillCalculatorsMap.put("COG", new SkillCalculator(aptitudeCOG));
		skillCalculatorsMap.put("COO", new SkillCalculator(aptitudeCOO));
		skillCalculatorsMap.put("INT", new SkillCalculator(aptitudeINT));
		skillCalculatorsMap.put("REF", new SkillCalculator(aptitudeREF));
		skillCalculatorsMap.put("SAV", new SkillCalculator(aptitudeSAV));
		skillCalculatorsMap.put("SOM", new SkillCalculator(aptitudeSOM));
		skillCalculatorsMap.put("WIL", new SkillCalculator(aptitudeWIL));

		EclipsePhaseSkillList activeSkills = registerListener(new EclipsePhaseSkillList("ASK", skillCalculatorsMap));
		character.setActiveSkills(activeSkills);
		activeSkills.addSkill("ANIMAL_HANDLING", EclipsePhaseCharacter.KEY_SAV, EclipsePhaseSkillList.CATEGORY_SOCIAL);
		activeSkills.addSkill("BEAM_WEAPONS", EclipsePhaseCharacter.KEY_COO, EclipsePhaseSkillList.CATEGORY_COMBAT);
		activeSkills.addSkill("BLADES", EclipsePhaseCharacter.KEY_SOM, EclipsePhaseSkillList.CATEGORY_COMBAT);
		activeSkills.addSkill("CLIMBING", EclipsePhaseCharacter.KEY_SOM, EclipsePhaseSkillList.CATEGORY_MOVEMENT);
		activeSkills.addSkill("CLUBS", EclipsePhaseCharacter.KEY_SOM, EclipsePhaseSkillList.CATEGORY_COMBAT);
		activeSkills.addSkill("DECEPTION", EclipsePhaseCharacter.KEY_SAV, EclipsePhaseSkillList.CATEGORY_SOCIAL);
		activeSkills.addSkill("DEMOLITIONS", EclipsePhaseCharacter.KEY_COG, true, EclipsePhaseSkillList.CATEGORY_MENTAL);
		activeSkills.addSkill("DISGUISE", EclipsePhaseCharacter.KEY_INT, EclipsePhaseSkillList.CATEGORY_SOCIAL);
		activeSkills.addSkill("FLIGHT", EclipsePhaseCharacter.KEY_SOM, EclipsePhaseSkillList.CATEGORY_MOVEMENT);
		activeSkills.addSkill("FRAY", EclipsePhaseCharacter.KEY_REF, EclipsePhaseSkillList.CATEGORY_COMBAT);
		activeSkills.addSkill("FREE_FALL", EclipsePhaseCharacter.KEY_REF, EclipsePhaseSkillList.CATEGORY_MOVEMENT);
		activeSkills.addSkill("FREERUNNING", EclipsePhaseCharacter.KEY_SOM, EclipsePhaseSkillList.CATEGORY_MOVEMENT);
		activeSkills.addSkill("GUNNERY", EclipsePhaseCharacter.KEY_INT, EclipsePhaseSkillList.CATEGORY_COMBAT);
		activeSkills.addSkill("IMPERSONATION", EclipsePhaseCharacter.KEY_SAV, EclipsePhaseSkillList.CATEGORY_SOCIAL);
		activeSkills.addSkill("INFILTRATION", EclipsePhaseCharacter.KEY_COO, EclipsePhaseSkillList.CATEGORY_MOVEMENT);
		activeSkills.addSkill("INFOSEC", EclipsePhaseCharacter.KEY_COG, true, EclipsePhaseSkillList.CATEGORY_MENTAL);
		activeSkills.addSkill("INTERFACING", EclipsePhaseCharacter.KEY_COG, EclipsePhaseSkillList.CATEGORY_MENTAL);
		activeSkills.addSkill("INTIMIDATION", EclipsePhaseCharacter.KEY_SAV, EclipsePhaseSkillList.CATEGORY_SOCIAL);
		activeSkills.addSkill("INVESTIGATION", EclipsePhaseCharacter.KEY_INT, EclipsePhaseSkillList.CATEGORY_MENTAL);
		activeSkills.addSkill("KINESICS", EclipsePhaseCharacter.KEY_SAV, EclipsePhaseSkillList.CATEGORY_SOCIAL);
		activeSkills.addSkill("KINETIC_WEAPONS", EclipsePhaseCharacter.KEY_COO, EclipsePhaseSkillList.CATEGORY_COMBAT);
		activeSkills.addSkill("NAVIGATION", EclipsePhaseCharacter.KEY_INT, EclipsePhaseSkillList.CATEGORY_MENTAL);
		activeSkills.addSkill("PALMING", EclipsePhaseCharacter.KEY_COO, EclipsePhaseSkillList.CATEGORY_MOVEMENT);
		activeSkills.addSkill("PERCEPTION", EclipsePhaseCharacter.KEY_INT, EclipsePhaseSkillList.CATEGORY_MOVEMENT);
		activeSkills.addSkill("PERSUASION", EclipsePhaseCharacter.KEY_SAV, EclipsePhaseSkillList.CATEGORY_SOCIAL);
		activeSkills.addSkill("PROGRAMMING", EclipsePhaseCharacter.KEY_INT, true, EclipsePhaseSkillList.CATEGORY_MENTAL);
		activeSkills.addSkill("PROTOCOL", EclipsePhaseCharacter.KEY_SAV, EclipsePhaseSkillList.CATEGORY_SOCIAL);
		activeSkills.addSkill("PSYCHOSURGERY", EclipsePhaseCharacter.KEY_INT, EclipsePhaseSkillList.CATEGORY_MENTAL);
		activeSkills.addSkill("RESEARCH", EclipsePhaseCharacter.KEY_COG, EclipsePhaseSkillList.CATEGORY_MENTAL);
		activeSkills.addSkill("SCROUNGING", EclipsePhaseCharacter.KEY_INT, EclipsePhaseSkillList.CATEGORY_MENTAL);
		activeSkills.addSkill("SEEKER_WEAPONS", EclipsePhaseCharacter.KEY_COO, EclipsePhaseSkillList.CATEGORY_COMBAT);
		activeSkills.addSkill("SPRAY_WEAPONS", EclipsePhaseCharacter.KEY_COO, EclipsePhaseSkillList.CATEGORY_COMBAT);
		activeSkills.addSkill("SWIMMING", EclipsePhaseCharacter.KEY_SOM, EclipsePhaseSkillList.CATEGORY_MOVEMENT);
		activeSkills.addSkill("THROWING_WEAPONS", EclipsePhaseCharacter.KEY_COO, EclipsePhaseSkillList.CATEGORY_COMBAT);
		activeSkills.addSkill("UNARMED", EclipsePhaseCharacter.KEY_SOM, EclipsePhaseSkillList.CATEGORY_COMBAT);
		
		
		EclipsePhaseSkillList  knowledgeSkills = registerListener(new EclipsePhaseSkillList("KSK", skillCalculatorsMap));
		character.setKnowledgeSkills(knowledgeSkills);
		
		EclipsePhaseSkillList  psiSkills = registerListener(new EclipsePhaseSkillList("PSK", skillCalculatorsMap));
		psiSkills.addSkill("CONTROL", EclipsePhaseCharacter.KEY_WIL, true, EclipsePhaseSkillList.CATEGORY_PSI);
		psiSkills.addSkill("ASSAULT", EclipsePhaseCharacter.KEY_WIL, true, EclipsePhaseSkillList.CATEGORY_PSI);
		psiSkills.addSkill("SENSE", EclipsePhaseCharacter.KEY_INT, true, EclipsePhaseSkillList.CATEGORY_PSI);
		character.setPsiSkills(psiSkills);
		
		// IDENTITIES
		
		character.setIdentities(registerListener(new StaticFeaturesCollection<Identity>("IDENTITY", Identity.class)));

		// MUSE
		
		Muse muse = new Muse();
		muse.setName(registerListener(new StringFeature("MUSE_NAME")));
		muse.setAptitudes(registerListener(new StaticFeaturesCollection<>("MUSE_APT", FixedNumericFeature.class)));
		muse.setSkills(registerListener(new StaticFeaturesCollection<>("MUSE_SKILL", FixedNumericFeature.class)));
		character.setMuse(muse);
		
		// CREDITS
		
		character.setCredits(registerListener(new SimpleVariable("CREDITS", new FeatureCalculator() {
			
			@Override
			public Number calculate(CharacterFeature feature) {
				VariableNumericFeature targetFeature = (VariableNumericFeature)feature;
				int result = (targetFeature.getFreebieCost() * 1000) + (targetFeature.getExperienceCost() * 1000) + targetFeature.getFreeCost() + targetFeature.getModifier();
				return result;
			}
		})));
		
		// INITIATIVE
		
		character.setInitiative(registerListener(new SimpleVariable("INITIATIVE", new InitiativeCalculator(aptitudeINT, aptitudeREF))));
		
		// PSI SLEIGHTS
		
		character.setPsiSleights(registerListener(new StaticFeaturesCollection<>("PSI_SLEIGHT", FixedNumericFeature.class)));
		
		
		return character;
	}


}
