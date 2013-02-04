package fr.neyrick.karax.model.eclipsephase;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import fr.neyrick.karax.entities.generic.MetaCharacter;
import fr.neyrick.karax.model.CharacterFactory;
import fr.neyrick.karax.model.FeatureCalculator;
import fr.neyrick.karax.model.GameCharacter;
import fr.neyrick.karax.model.Ruleset;
import fr.neyrick.karax.model.SimpleContainerFeature;
import fr.neyrick.karax.model.StringFeature;
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
		
		// APTITUDES
		
		FeatureCalculator<VariableNumericFeature> aptitudeBaseCalculator = new FeatureCalculator<VariableNumericFeature>() {
			
			@Override
			public Number calculate(VariableNumericFeature feature) {
				int result = feature.getCreationCost();
				result += (feature.getFreebieCost() / 10) + (feature.getExperienceCost() / 10) + (feature.getFreeCost() / 10);
				return result;
			}
		};
		
		List<VariableNumericFeature> aptitudes = new ArrayList<>(7);
		VariableNumericFeature aptitudeBaseCOG = new VariableNumericFeature("APT_BASE_COG", aptitudeBaseCalculator);
		VariableNumericFeature aptitudeBaseCOO = new VariableNumericFeature("APT_BASE_COO", aptitudeBaseCalculator);
		VariableNumericFeature aptitudeBaseINT = new VariableNumericFeature("APT_BASE_INT", aptitudeBaseCalculator);
		VariableNumericFeature aptitudeBaseSOM = new VariableNumericFeature("APT_BASE_SOM", aptitudeBaseCalculator);
		VariableNumericFeature aptitudeBaseWIL = new VariableNumericFeature("APT_BASE_WIL", aptitudeBaseCalculator);
		VariableNumericFeature aptitudeBaseREF = new VariableNumericFeature("APT_BASE_REF", aptitudeBaseCalculator);
		VariableNumericFeature aptitudeBaseSAV = new VariableNumericFeature("APT_BASE_SAV", aptitudeBaseCalculator);
		aptitudes.add(registerListener(aptitudeBaseCOG));
		aptitudes.add(registerListener(aptitudeBaseCOO));
		aptitudes.add(registerListener(aptitudeBaseINT));
		aptitudes.add(registerListener(aptitudeBaseREF));
		aptitudes.add(registerListener(aptitudeBaseSAV));
		aptitudes.add(registerListener(aptitudeBaseSOM));
		aptitudes.add(registerListener(aptitudeBaseWIL));
		character.setAptitudeBase(aptitudes);
		
		// TODO: MORPH LIMIT
		aptitudes = new ArrayList<>(7);
		AptitudeCalculator aptitudeTotalCalculator = new AptitudeCalculator(aptitudeBaseCOG, null);
		VariableNumericFeature aptitudeTotalCOG = new VariableNumericFeature("APT_TOTAL_COG", aptitudeTotalCalculator);
		aptitudeTotalCalculator = new AptitudeCalculator(aptitudeBaseCOO, null);
		VariableNumericFeature aptitudeTotalCOO = new VariableNumericFeature("APT_TOTAL_COO", aptitudeTotalCalculator);
		aptitudeTotalCalculator = new AptitudeCalculator(aptitudeBaseINT, null);
		VariableNumericFeature aptitudeTotalINT = new VariableNumericFeature("APT_TOTAL_INT", aptitudeTotalCalculator);
		aptitudeTotalCalculator = new AptitudeCalculator(aptitudeBaseSOM, null);
		VariableNumericFeature aptitudeTotalSOM = new VariableNumericFeature("APT_TOTAL_SOM", aptitudeTotalCalculator);
		aptitudeTotalCalculator = new AptitudeCalculator(aptitudeBaseWIL, null);
		VariableNumericFeature aptitudeTotalWIL = new VariableNumericFeature("APT_TOTAL_WIL", aptitudeTotalCalculator);
		aptitudeTotalCalculator = new AptitudeCalculator(aptitudeBaseREF, null);
		VariableNumericFeature aptitudeTotalREF = new VariableNumericFeature("APT_TOTAL_REF", aptitudeTotalCalculator);
		aptitudeTotalCalculator = new AptitudeCalculator(aptitudeBaseSAV, null);
		VariableNumericFeature aptitudeTotalSAV = new VariableNumericFeature("APT_TOTAL_SAV", aptitudeTotalCalculator);
		aptitudes.add(registerListener(aptitudeTotalCOG));
		aptitudes.add(registerListener(aptitudeTotalCOO));
		aptitudes.add(registerListener(aptitudeTotalINT));
		aptitudes.add(registerListener(aptitudeTotalREF));
		aptitudes.add(registerListener(aptitudeTotalSAV));
		aptitudes.add(registerListener(aptitudeTotalSOM));
		aptitudes.add(registerListener(aptitudeTotalWIL));
		character.setAptitudeTotal(aptitudes);
		
		// MOTIVATIONS
		
		character.setMotivations(registerListener(new SimpleContainerFeature<StringFeature>("MOTIVATION", StringFeature.class)));
		
		// MOXIE
		
		character.setMoxie(registerListener(new VariableNumericFeature("MOXIE", new FeatureCalculator<VariableNumericFeature>() {
			
			@Override
			public Number calculate(VariableNumericFeature feature) {
				int result = 1;
				result += (feature.getFreebieCost() / 15) + (feature.getExperienceCost() / 15) + (feature.getFreeCost() / 15) + (feature.getModifier());
				if (result > 10) return 10;
				return result;
			}
		})));

		// SKILLS (Active = ASK, Knowledge = KSK, PSI = PSK)
		
		SkillCalculator skillCalculatorCOG = new SkillCalculator(aptitudeTotalCOG);
		SkillCalculator skillCalculatorCOO = new SkillCalculator(aptitudeTotalCOO);
		SkillCalculator skillCalculatorINT = new SkillCalculator(aptitudeTotalINT);
		SkillCalculator skillCalculatorREF = new SkillCalculator(aptitudeTotalREF);
		SkillCalculator skillCalculatorSAV = new SkillCalculator(aptitudeTotalSAV);
		SkillCalculator skillCalculatorSOM = new SkillCalculator(aptitudeTotalSOM);
		SkillCalculator skillCalculatorWIL = new SkillCalculator(aptitudeTotalWIL);

		List<VariableNumericFeature> activeSkills = new ArrayList<>();
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_ANIMALHANDLING", skillCalculatorSAV)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_BEAMWEAPONS", skillCalculatorCOO)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_BLADES", skillCalculatorSOM)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_CLIMBING", skillCalculatorSOM)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_CLUBS", skillCalculatorSOM)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_DECEPTION", skillCalculatorSAV)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_DEMOLITIONS", skillCalculatorCOG)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_DISGUISE", skillCalculatorINT)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_FLIGHT", skillCalculatorSOM)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_FRAY", skillCalculatorREF)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_FREEFALL", skillCalculatorREF)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_FREERUNNING", skillCalculatorSOM)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_GUNNERY", skillCalculatorINT)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_IMPERSONATION", skillCalculatorSAV)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_INFILTRATION", skillCalculatorCOO)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_INFOSEC", skillCalculatorCOG)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_INTERFACING", skillCalculatorCOG)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_INTIMIDATION", skillCalculatorSAV)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_INVESTIGATION", skillCalculatorINT)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_KINESICS", skillCalculatorSAV)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_KINETICWEAPONS", skillCalculatorCOO)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_MEDICINE", skillCalculatorCOG)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_NAVIGATION", skillCalculatorINT)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_PALMING", skillCalculatorCOO)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_PERCEPTION", skillCalculatorINT)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_PERSUASION", skillCalculatorSAV)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_PROGRAMMING", skillCalculatorINT)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_PROTOCOL", skillCalculatorSAV)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_PSYCHOSURGERY", skillCalculatorINT)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_RESEARCH", skillCalculatorCOG)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_SCROUNGING", skillCalculatorINT)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_SEEKERWEAPONS", skillCalculatorCOO)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_SPRAYWEAPONS", skillCalculatorCOO)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_SWIMMING", skillCalculatorSOM)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_THROWINGWEAPONS", skillCalculatorCOO)));
		activeSkills.add(registerListener(new VariableNumericFeature("ASK_UNARMED", skillCalculatorSOM)));
		character.setActiveSkills(activeSkills);

		List<VariableNumericFeature>  knowledgeSkills = new ArrayList<>();
		character.setKnowledgeSkills(knowledgeSkills);
		
		List<VariableNumericFeature>  psiSkills = new ArrayList<>();
		psiSkills.add(registerListener(new VariableNumericFeature("PSK_CONTROL", skillCalculatorWIL)));
		psiSkills.add(registerListener(new VariableNumericFeature("PSK_ASSAULT", skillCalculatorWIL)));
		psiSkills.add(registerListener(new VariableNumericFeature("PSK_SENSE", skillCalculatorINT)));
		character.setPsiSkills(psiSkills);
		
		// Exotic Melee
		// Exotic Ranged
		// Hardware
		// Networking
		// Pilot
		
		// kno: Academics, Art, Interest, Language, Profession
// TODO Auto-generated method stub
		return character;
	}


}
