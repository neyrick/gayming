package fr.neyrick.karax.pathfinder.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.model.FeaturesCollection;
import fr.neyrick.karax.model.SimpleVariable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Load extends SimpleVariable implements AbilityCapper {

	public static final String LOAD_LIGHT = "LIGHT";
	public static final String LOAD_MEDIUM = "MEDIUM";
	public static final String LOAD_HEAVY = "HEAVY";
	public static final String LOAD_OVER = "OVER";
	
	private float maxLight = 0;
	private float maxMedium = 0;
	private float maxHeavy = 0;
    private int penalty = 0;
    private int maxDex = 100;
    private String state;
	
	@XmlAttribute
	public float getMaxLight() {
		refresh();
		return maxLight;
	}

	@XmlAttribute
	public float getMaxMedium() {
		refresh();
		return maxMedium;
	}

	@XmlAttribute
	public float getMaxHeavy() {
		refresh();
		return maxHeavy;
	}

	@XmlAttribute
	public int getPenalty() {
		refresh();
		return penalty;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}

	@XmlAttribute
	public int getMaxDex() {
		refresh();
		return maxDex;
	}

	@Override
	public int getCap() {
		refresh();
		return maxDex;
	}

	public void setMaxDex(int maxDex) {
		this.maxDex = maxDex;
	}

	@XmlAttribute
	public String getState() {
		refresh();
		return state;
	}	

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String getValue() {
		return super.getValue();
	}

	public void setMaxLight(float maxLight) {
		this.maxLight = maxLight;
	}

	public void setMaxMedium(float maxMedium) {
		this.maxMedium = maxMedium;
	}

	public void setMaxHeavy(float maxHeavy) {
		this.maxHeavy = maxHeavy;
	}

	public Load() {
		this(null, null);
	}

	public Load(FeaturesCollection container, String key,
			LoadCalculator calculator) {
		super(container, key, calculator);
	}

	public Load(String key, LoadCalculator calculator) {
		super(key, calculator);
	}

	
}
