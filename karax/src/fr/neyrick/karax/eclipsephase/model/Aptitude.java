package fr.neyrick.karax.eclipsephase.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import fr.neyrick.karax.entities.generic.CharacterEdit;
import fr.neyrick.karax.model.FeatureCalculator;
import fr.neyrick.karax.model.FeaturesCollection;
import fr.neyrick.karax.model.VariableNumericFeature;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Aptitude extends VariableNumericFeature {

	static public final String MORPH_BASE_MAX = "MORPH_BASE_MAX";
	
	static public final String MORPH_MAX = "MORPH_MAX";
	
	static public final String EGO_MAX = "EGO_MAX";

	private int EGO_BASE_MAX = 30;
	
	private int base;
	
	private int newMorphMax = 200;

	private int morphBaseMax;

	private int newEgoMax = 200;

	private int effectiveMorphBonus = 0;
	
	
	@XmlAttribute
	public int getEffectiveMorphBonus() {
		return effectiveMorphBonus;
	}

	public void setEffectiveMorphBonus(int effectiveMorphBonus) {
		this.effectiveMorphBonus = effectiveMorphBonus;
	}

	@XmlAttribute
	public String getDisplay() {
		return tryTranslation(getKey());
	}

	@XmlAttribute
	public int getBase() {
		refresh();
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	@XmlAttribute
	public int getMorphBonus() {
		return getCost("MORPH");
	}

	public int getMorphMax() {
		int result = (newMorphMax == 200 ? morphBaseMax : newMorphMax);
		return result + getMorphMaxMod();
	}
	
	public int getMorphMaxMod() {
		return getCost("MORPH_MAX_MOD");
	}
	
	public int getEgoMax() {
		return (newEgoMax == 200 ? EGO_BASE_MAX : newEgoMax);
	}
	
	@XmlAttribute
	@Override
	public String getKey() {
		return super.getKey();
	}

	@Override
	@XmlValue
	public String getValue() {
		return super.getValue();
	}

	public Aptitude() {
		this(null, null);
	}

	@Override
	public void recordEdit(CharacterEdit edit) {
		String amountType = edit.getIncrement().getAmountType();
		int newValue = edit.getIncrement().getAmount();
		if (EGO_MAX.equals(amountType)) newEgoMax = newValue;
		else if (MORPH_MAX.equals(amountType)) newMorphMax = (newMorphMax > newValue ? newValue : newMorphMax);
		else if (MORPH_BASE_MAX.equals(amountType)) morphBaseMax = newValue;
		else super.recordEdit(edit);
	}

	public Aptitude(FeaturesCollection container, String key,
			FeatureCalculator calculator) {
		super(container, key, calculator);
	}

	public Aptitude(String key, FeatureCalculator calculator) {
		super(key, calculator);
	}

	
}
