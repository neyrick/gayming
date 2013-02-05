package fr.neyrick.karax.model.eclipsephase;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.entities.generic.CharacterEdit;
import fr.neyrick.karax.model.CharacterFeature;
import fr.neyrick.karax.model.ContainerFeature;
import fr.neyrick.karax.model.SimpleContainerFeature;
import fr.neyrick.karax.model.VariableNumericFeature;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class EclipsePhaseActiveSkillsList extends EclipsePhaseSkillList {

	public static final String SUBKEY_EXOTIC_MELEE = "EXOTIC_MELEE";
	public static final String SUBKEY_EXOTIC_RANGED = "EXOTIC_RANGED";
	public static final String SUBKEY_HARDWARE = "HARDWARE";
	public static final String SUBKEY_NETWORKING = "NETWORKING";
	public static final String SUBKEY_PILOT = "PILOT";
	
	private SimpleContainerFeature<VariableNumericFeature> exoticMeleeSkills;
	
	private SimpleContainerFeature<VariableNumericFeature> exoticRangedSkills;
	
	private SimpleContainerFeature<VariableNumericFeature> networkingSkills;
	
	private SimpleContainerFeature<VariableNumericFeature> pilotSkills;
	
	@XmlElement(name="skill")
	public Collection<VariableNumericFeature> getExoticMeleeSkills() {
		if (exoticMeleeSkills == null) return null;
		return exoticMeleeSkills.getActualSubFeatures();
	}

	@Override
	@XmlElement(name="skill")
	public Collection<VariableNumericFeature> getFixedSkills() {
		return super.getFixedSkills();
	}

	@XmlElement(name="skill")
	public Collection<VariableNumericFeature> getExoticRangedSkills() {
		if (exoticRangedSkills == null) return null;
		return exoticRangedSkills.getActualSubFeatures();
	}

	@XmlElement(name="skill")
	public Collection<VariableNumericFeature> getNetworkingSkills() {
		if (networkingSkills == null) return null;
		return networkingSkills.getActualSubFeatures();
	}

	@XmlElement(name="skill")
	public Collection<VariableNumericFeature> getPilotSkills() {
		if (pilotSkills == null) return null;
		return pilotSkills.getActualSubFeatures();
	}

	public EclipsePhaseActiveSkillsList(ContainerFeature parent, String key) {
		super(parent, key);
	}
	
	public EclipsePhaseActiveSkillsList(String key) {
		super(key);
	}

	public EclipsePhaseActiveSkillsList() {
		super(null);
	}

	@Override
	protected CharacterFeature createSubFeature(String subItemKey, CharacterEdit edit) {
		switch(subItemKey) {
			case SUBKEY_EXOTIC_MELEE:return (exoticMeleeSkills = createFlexibleSkillsList(SUBKEY_EXOTIC_MELEE, "COO"));
			case SUBKEY_EXOTIC_RANGED:return (exoticRangedSkills = createFlexibleSkillsList(SUBKEY_EXOTIC_MELEE, "COO"));
			case SUBKEY_NETWORKING:return (networkingSkills = createFlexibleSkillsList(SUBKEY_EXOTIC_MELEE, "SAV"));
			case SUBKEY_PILOT:return (pilotSkills = createFlexibleSkillsList(SUBKEY_EXOTIC_MELEE, "INT"));
			default:return addGeneralSkill(subItemKey, edit);
		}
	}
	
}
