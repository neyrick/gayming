package fr.neyrick.karax.model.eclipsephase;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;

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

	@Override
	protected GameCharacter initCharacter(MetaCharacter metaCharacter) {
		EclipsePhaseCharacter character = new EclipsePhaseCharacter();
		
		// GENERAL INFORMATION
		
		character.setBackground(registerListener(new StringFeature("BACKGROUND")));
		character.setFaction(registerListener(new StringFeature("FACTION")));
		character.setActualAge(registerListener(new FixedNumericFeature("ACTUAL_AGE")));
		
		// TRAITS
		
		character.setTraits(registerListener(new StaticFeaturesCollection<>("TRAIT", FixedNumericFeature.class)));
		
		// MORPH
		
		Morph currentMorph = new Morph();
		currentMorph.setOrigin(registerListener(new StringFeature("MORPH_ORIGIN")));
		currentMorph.setType(registerListener(new StringFeature("MORPH_TYPE")));
		currentMorph.setDurability(registerListener(new FixedNumericFeature("MORPH_DURABILITY")));
		currentMorph.setAptitudeMax(registerListener(new FixedNumericFeature("MORPH_APT_MAX")));
		currentMorph.setSpeedModifier(registerListener(new FixedNumericFeature("MORPH_SPEED_MOD")));
		currentMorph.setEnhancements(registerListener(new StaticFeaturesCollection<>("MORPH_ENH", StringFeature.class)));
		currentMorph.setTraits(registerListener(new StaticFeaturesCollection<>("MORPH_TRAITS", StringFeature.class)));
		character.setCurrentMorph(currentMorph);
		
		// APTITUDES
		
		FeatureCalculator aptitudeBaseCalculator = new FeatureCalculator() {
			
			@Override
			public Number calculate(CharacterFeature feature) {
				Aptitude targetFeature = (Aptitude)feature;
				int result = targetFeature.getCreationCost();
				result += (targetFeature.getFreebieCost() / 10) + (targetFeature.getExperienceCost() / 10) + (targetFeature.getFreeCost() / 10);
				return result;
			}
		};
		
		// TODO: morph limit
		VariableFeaturesCollection<Aptitude> aptitudes = registerListener(new VariableFeaturesCollection<>("APT", Aptitude.class));
		Aptitude aptitudeCOG = aptitudes.addFeature(new Aptitude("COG", 30, aptitudeBaseCalculator));
		Aptitude aptitudeCOO = aptitudes.addFeature(new Aptitude("COO", 30, aptitudeBaseCalculator));
		Aptitude aptitudeINT = aptitudes.addFeature(new Aptitude("INT", 30, aptitudeBaseCalculator));
		Aptitude aptitudeSOM = aptitudes.addFeature(new Aptitude("SOM", 30, aptitudeBaseCalculator));
		Aptitude aptitudeWIL = aptitudes.addFeature(new Aptitude("WIL", 30, aptitudeBaseCalculator));
		Aptitude aptitudeREF = aptitudes.addFeature(new Aptitude("REF", 30, aptitudeBaseCalculator));
		Aptitude aptitudeSAV = aptitudes.addFeature(new Aptitude("SAV", 30, aptitudeBaseCalculator));
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
		activeSkills.addSkill("ANIMALHANDLING", EclipsePhaseCharacter.KEY_SAV);
		activeSkills.addSkill("BEAMWEAPONS", EclipsePhaseCharacter.KEY_COO);
		activeSkills.addSkill("BLADES", EclipsePhaseCharacter.KEY_SOM);
		activeSkills.addSkill("CLIMBING", EclipsePhaseCharacter.KEY_SOM);
		activeSkills.addSkill("CLUBS", EclipsePhaseCharacter.KEY_SOM);
		activeSkills.addSkill("DECEPTION", EclipsePhaseCharacter.KEY_SAV);
		activeSkills.addSkill("DEMOLITIONS", EclipsePhaseCharacter.KEY_COG, true);
		activeSkills.addSkill("DISGUISE", EclipsePhaseCharacter.KEY_INT);
		activeSkills.addSkill("FLIGHT", EclipsePhaseCharacter.KEY_SOM);
		activeSkills.addSkill("FRAY", EclipsePhaseCharacter.KEY_REF);
		activeSkills.addSkill("FREEFALL", EclipsePhaseCharacter.KEY_REF);
		activeSkills.addSkill("FREERUNNING", EclipsePhaseCharacter.KEY_SOM);
		activeSkills.addSkill("GUNNERY", EclipsePhaseCharacter.KEY_INT);
		activeSkills.addSkill("IMPERSONATION", EclipsePhaseCharacter.KEY_SAV);
		activeSkills.addSkill("INFILTRATION", EclipsePhaseCharacter.KEY_COO);
		activeSkills.addSkill("INFOSEC", EclipsePhaseCharacter.KEY_COG, true);
		activeSkills.addSkill("INTERFACING", EclipsePhaseCharacter.KEY_COG);
		activeSkills.addSkill("INTIMIDATION", EclipsePhaseCharacter.KEY_SAV);
		activeSkills.addSkill("INVESTIGATION", EclipsePhaseCharacter.KEY_INT);
		activeSkills.addSkill("KINESICS", EclipsePhaseCharacter.KEY_SAV);
		activeSkills.addSkill("KINETICWEAPONS", EclipsePhaseCharacter.KEY_COO);
		activeSkills.addSkill("MEDICINE", EclipsePhaseCharacter.KEY_COG);
		activeSkills.addSkill("NAVIGATION", EclipsePhaseCharacter.KEY_INT);
		activeSkills.addSkill("PALMING", EclipsePhaseCharacter.KEY_COO);
		activeSkills.addSkill("PERCEPTION", EclipsePhaseCharacter.KEY_INT);
		activeSkills.addSkill("PERSUASION", EclipsePhaseCharacter.KEY_SAV);
		activeSkills.addSkill("PROGRAMMING", EclipsePhaseCharacter.KEY_INT, true);
		activeSkills.addSkill("PROTOCOL", EclipsePhaseCharacter.KEY_SAV);
		activeSkills.addSkill("PSYCHOSURGERY", EclipsePhaseCharacter.KEY_INT);
		activeSkills.addSkill("RESEARCH", EclipsePhaseCharacter.KEY_COG);
		activeSkills.addSkill("SCROUNGING", EclipsePhaseCharacter.KEY_INT);
		activeSkills.addSkill("SEEKERWEAPONS", EclipsePhaseCharacter.KEY_COO);
		activeSkills.addSkill("SPRAYWEAPONS", EclipsePhaseCharacter.KEY_COO);
		activeSkills.addSkill("SWIMMING", EclipsePhaseCharacter.KEY_SOM);
		activeSkills.addSkill("THROWINGWEAPONS", EclipsePhaseCharacter.KEY_COO);
		activeSkills.addSkill("UNARMED", EclipsePhaseCharacter.KEY_SOM);
		
		
		EclipsePhaseSkillList  knowledgeSkills = registerListener(new EclipsePhaseSkillList("KSK", skillCalculatorsMap));
		character.setKnowledgeSkills(knowledgeSkills);
		
		EclipsePhaseSkillList  psiSkills = registerListener(new EclipsePhaseSkillList("PSK", skillCalculatorsMap));
		psiSkills.addSkill("CONTROL", EclipsePhaseCharacter.KEY_WIL, true);
		psiSkills.addSkill("ASSAULT", EclipsePhaseCharacter.KEY_WIL, true);
		psiSkills.addSkill("SENSE", EclipsePhaseCharacter.KEY_INT, true);
		character.setPsiSkills(psiSkills);
		
		// IDENTITIES
		
		character.setIdentities(registerListener(new StaticFeaturesCollection<Identity>("IDENTITY", Identity.class)));

		// MUSE
		
		Muse muse = new Muse();
		muse.setName(registerListener(new StringFeature("MUSE_NAME")));
		muse.setAptitudes(registerListener(new StaticFeaturesCollection<>("MUSE_APT", FixedNumericFeature.class)));
		muse.setSkills(registerListener(new StaticFeaturesCollection<>("MUSE_SKILLS", FixedNumericFeature.class)));
		
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
		
		character.setCredits(registerListener(new SimpleVariable("INITIATIVE", new InitiativeCalculator(aptitudeINT, aptitudeREF))));
		
		// PSI SLEIGHTS
		
		character.setPsiSleights(registerListener(new StaticFeaturesCollection<>("PSI_SLEIGHT", FixedNumericFeature.class)));
		
		
		return character;
	}


}
