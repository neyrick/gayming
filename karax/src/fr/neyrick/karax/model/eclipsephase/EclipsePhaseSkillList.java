package fr.neyrick.karax.model.eclipsephase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import fr.neyrick.karax.entities.generic.CharacterEdit;
import fr.neyrick.karax.model.ComplexContainerFeature;
import fr.neyrick.karax.model.ContainerFeature;
import fr.neyrick.karax.model.FeatureCalculator;
import fr.neyrick.karax.model.SimpleContainerFeature;
import fr.neyrick.karax.model.VariableNumericFeature;

@XmlTransient
public abstract class EclipsePhaseSkillList extends ComplexContainerFeature {

	protected SimpleContainerFeature<VariableNumericFeature> createFlexibleSkillsList(String subitemKey, String calculatorKey) {
		return new SimpleContainerFeature<>(this, subitemKey, VariableNumericFeature.class, calculatorsMap.get(calculatorKey));
	}
	
	private Map<String, FeatureCalculator> calculatorsMap = new HashMap<>();

	private List<VariableNumericFeature> generalSkills = new ArrayList<>();
	
	protected VariableNumericFeature addGeneralSkill(String subItemKey, CharacterEdit edit) {
		VariableNumericFeature feature;
		if (edit == null) feature = new VariableNumericFeature(this, subItemKey, null);
		else feature = new VariableNumericFeature(this, subItemKey, getCalculator(edit.getTargetSubKey3()));
		generalSkills.add(feature);
		return feature;
	}
	
	public Collection<VariableNumericFeature> getFixedSkills() {
		return generalSkills;
	}

	public Map<String, FeatureCalculator> getCalculatorsMap() {
		return calculatorsMap;
	}

	public void setCalculatorsMap(Map<String, FeatureCalculator> calculatorsMap) {
		this.calculatorsMap = calculatorsMap;
	}

	protected FeatureCalculator getCalculator(String calculatorKey) {
		if (calculatorKey == null) return null;
		return calculatorsMap.get(calculatorKey);
	}
	
	public void initFeature(String subItemKey, String calculatorKey) {
		VariableNumericFeature feature = (VariableNumericFeature)addFeature(subItemKey, null);
		feature.setCalculator(calculatorsMap.get(calculatorKey));
	}
	
	public EclipsePhaseSkillList(ContainerFeature parent, String key) {
		super(parent, key);
	}
	
	public EclipsePhaseSkillList(String key) {
		super(key);
	}

	public EclipsePhaseSkillList() {
		super(null);
	}

}
