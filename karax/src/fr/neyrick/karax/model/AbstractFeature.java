package fr.neyrick.karax.model;

import javax.xml.bind.annotation.XmlTransient;


@XmlTransient
public abstract class AbstractFeature implements CharacterFeature {

	private ContainerFeature container = null;
	
	private String key;
	
	private FeatureCalculator calculator = null;

	public Number getNumericValue() {
		return calculator.calculate(this);
	}
	
	public FeatureCalculator getCalculator() {
		return calculator;
	}

	public void setCalculator(FeatureCalculator calculator) {
		this.calculator = calculator;
	}

	public String getKey() {
		return key;
	}
	
	public ContainerFeature getContainer() {
		return container;
	}

	public void setContainer(ContainerFeature container) {
		this.container = container;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public AbstractFeature(ContainerFeature container, String key) {
		super();
		this.key = key;
		this.container = container;
	}
}
