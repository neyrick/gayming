package fr.neyrick.karax.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.entities.generic.CharacterEdit;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class FixedBonus extends FixedNumericFeature {

	private static final BonusFormat bonusFormat = new BonusFormat();
    private String type;
	
	@Override
	public String getValue() {
		return bonusFormat.format(super.getAmount());
	}
	
	@XmlAttribute
	public String getType() {
		return type;
	}

	@Override
	public void recordEdit(CharacterEdit edit) {
		super.recordEdit(edit);
		this.type = edit.getIncrement().getAmountType();
	}

	public FixedBonus(FeaturesCollection container, String key) {
		super(container, key);
	}

	public FixedBonus(String key) {
		super(key);
	}

	public FixedBonus() {
	}

}
