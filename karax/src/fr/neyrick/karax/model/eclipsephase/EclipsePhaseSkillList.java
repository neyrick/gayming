package fr.neyrick.karax.model.eclipsephase;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.entities.generic.CharacterEdit;
import fr.neyrick.karax.model.FeatureCalculator;
import fr.neyrick.karax.model.FeaturesCollection;
import fr.neyrick.karax.model.VariableFeaturesCollection;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class EclipsePhaseSkillList extends VariableFeaturesCollection<Skill> {

	public static final String SUBKEY_EXOTIC_MELEE = "EM:";
	public static final String SUBKEY_EXOTIC_RANGED = "ER:";
	public static final String SUBKEY_HARDWARE = "HW:";
	public static final String SUBKEY_MEDICINE = "ME:";
	public static final String SUBKEY_NETWORKING = "NW:";
	public static final String SUBKEY_PILOT = "PL:";
	public static final String SUBKEY_ACADEMICS = "AC:";
	public static final String SUBKEY_ART = "AR:";
	public static final String SUBKEY_INTEREST = "IN:";
	public static final String SUBKEY_LANGUAGE = "LA:";
	public static final String SUBKEY_PROFESSION = "PR:";
	
	public static final String CATEGORY_COMBAT = "COMBAT";
	public static final String CATEGORY_MOVEMENT = "MOVEMENT";
	public static final String CATEGORY_MENTAL = "MENTAL";
	public static final String CATEGORY_PSI = "PSI";
	public static final String CATEGORY_SOCIAL = "SOCIAL";
	public static final String CATEGORY_KNOWLEDGE = "KNOWLEDGE";
	
	private Map<String, SkillCalculator> calculatorsMap = new HashMap<>();

	public Map<String, SkillCalculator> getCalculatorsMap() {
		return calculatorsMap;
	}

	public void setCalculatorsMap(Map<String, SkillCalculator> calculatorsMap) {
		this.calculatorsMap = calculatorsMap;
	}

	protected FeatureCalculator getCalculator(String calculatorKey) {
		if (calculatorKey == null) return null;
		return calculatorsMap.get(calculatorKey);
	}
	
	@Override
	protected FeatureCalculator getExtraItemCalculator(CharacterEdit edit) {
		String subItemKey = getSubItemKey(edit);
		if (subItemKey.startsWith(SUBKEY_EXOTIC_MELEE)) return calculatorsMap.get(EclipsePhaseCharacter.KEY_COO);
		else if (subItemKey.startsWith(SUBKEY_EXOTIC_RANGED)) return calculatorsMap.get(EclipsePhaseCharacter.KEY_COO);
		else if (subItemKey.startsWith(SUBKEY_HARDWARE)) return calculatorsMap.get(EclipsePhaseCharacter.KEY_COG);
		else if (subItemKey.startsWith(SUBKEY_MEDICINE)) return calculatorsMap.get(EclipsePhaseCharacter.KEY_COG);
		else if (subItemKey.startsWith(SUBKEY_NETWORKING)) return calculatorsMap.get(EclipsePhaseCharacter.KEY_SAV);
		else if (subItemKey.startsWith(SUBKEY_PILOT)) return calculatorsMap.get(EclipsePhaseCharacter.KEY_REF);
		else if (subItemKey.startsWith(SUBKEY_ACADEMICS)) return calculatorsMap.get(EclipsePhaseCharacter.KEY_COG);
		else if (subItemKey.startsWith(SUBKEY_ART)) return calculatorsMap.get(EclipsePhaseCharacter.KEY_INT);
		else if (subItemKey.startsWith(SUBKEY_INTEREST)) return calculatorsMap.get(EclipsePhaseCharacter.KEY_COG);
		else if (subItemKey.startsWith(SUBKEY_LANGUAGE)) return calculatorsMap.get(EclipsePhaseCharacter.KEY_INT);
		else if (subItemKey.startsWith(SUBKEY_PROFESSION)) return calculatorsMap.get(EclipsePhaseCharacter.KEY_COG);
		else return calculatorsMap.get(edit.getTargetSubKey2());
	}

	protected String getExtraItemCategory(CharacterEdit edit) {
		String subItemKey = getSubItemKey(edit);
		if (subItemKey.startsWith(SUBKEY_EXOTIC_MELEE)) return CATEGORY_COMBAT;
		else if (subItemKey.startsWith(SUBKEY_EXOTIC_RANGED)) return CATEGORY_COMBAT;
		else if (subItemKey.startsWith(SUBKEY_HARDWARE)) return CATEGORY_MENTAL;
		else if (subItemKey.startsWith(SUBKEY_MEDICINE)) return CATEGORY_MENTAL;
		else if (subItemKey.startsWith(SUBKEY_NETWORKING)) return CATEGORY_SOCIAL;
		else if (subItemKey.startsWith(SUBKEY_PILOT)) return CATEGORY_MOVEMENT;
		else if (subItemKey.startsWith(SUBKEY_ACADEMICS)) return CATEGORY_KNOWLEDGE;
		else if (subItemKey.startsWith(SUBKEY_ART)) return CATEGORY_KNOWLEDGE;
		else if (subItemKey.startsWith(SUBKEY_INTEREST)) return CATEGORY_KNOWLEDGE;
		else if (subItemKey.startsWith(SUBKEY_LANGUAGE)) return CATEGORY_KNOWLEDGE;
		else if (subItemKey.startsWith(SUBKEY_PROFESSION)) return CATEGORY_KNOWLEDGE;
		else return edit.getTargetSubKey3();
	}

	public void addSkill(String key, String aptitude, boolean isNoDefault, String category) {
		addFeature(new Skill(key, aptitude, calculatorsMap.get(aptitude), isNoDefault, category));
	}
	
	public void addSkill(String key, String aptitude, String category) {
		this.addSkill(key, aptitude, false, category);
	}
	
	public EclipsePhaseSkillList(FeaturesCollection parent, String key, Map<String, SkillCalculator> calculatorsMap) {
		super(parent, key, Skill.class);
		this.calculatorsMap = calculatorsMap;
	}
	
	public EclipsePhaseSkillList(String key, Map<String, SkillCalculator> calculatorsMap) {
		super(key, Skill.class);
		this.calculatorsMap = calculatorsMap;
	}

	public EclipsePhaseSkillList() {
		super();
	}

}
