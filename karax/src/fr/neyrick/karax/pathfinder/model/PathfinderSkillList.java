package fr.neyrick.karax.pathfinder.model;

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
public class PathfinderSkillList extends VariableFeaturesCollection<Skill> {

	public static final String SUBKEY_PROFESSION = "PR:";
	public static final String SUBKEY_CRAFT = "CR:";
	public static final String SUBKEY_KNOWLEDGE = "KN:";
	public static final String SUBKEY_PERFORM = "PE:";
	
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
	
	private String getLinkedAbilityKey(CharacterEdit edit) {
		String subItemKey = getSubItemKey(edit);
		if (subItemKey.startsWith(SUBKEY_PROFESSION)) return PathfinderCharacter.KEY_WIS;
		else if (subItemKey.startsWith(SUBKEY_CRAFT)) return PathfinderCharacter.KEY_INT;
		else if (subItemKey.startsWith(SUBKEY_KNOWLEDGE)) return PathfinderCharacter.KEY_INT;
		else if (subItemKey.startsWith(SUBKEY_PERFORM)) return PathfinderCharacter.KEY_CHA;
		else return edit.getTargetSubKey2();
	}
	
	@Override
	protected FeatureCalculator getExtraItemCalculator(CharacterEdit edit) {
		return calculatorsMap.get(getLinkedAbilityKey(edit));
	}

	public void addSkill(String key, String ability, boolean isNoDefault, boolean armorpenalty) {
		addFeature(new Skill(key, ability, calculatorsMap.get(ability), isNoDefault, armorpenalty));
	}
	
	@Override
	protected void customizeExtraFeature(CharacterEdit edit, Skill newFeature) {
		super.customizeExtraFeature(edit, newFeature);
		newFeature.setLinkedAbility(getLinkedAbilityKey(edit));
	}

	public PathfinderSkillList(FeaturesCollection parent, String key, Map<String, SkillCalculator> calculatorsMap) {
		super(parent, key, Skill.class);
		this.calculatorsMap = calculatorsMap;
	}
	
	public PathfinderSkillList(String key, Map<String, SkillCalculator> calculatorsMap) {
		super(key, Skill.class);
		this.calculatorsMap = calculatorsMap;
	}

	public PathfinderSkillList() {
		super();
	}

}
