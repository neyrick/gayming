package fr.neyrick.karax.model;

import javax.enterprise.util.AnnotationLiteral;

@SuppressWarnings("serial")
public class RulesetLiteral extends AnnotationLiteral<Ruleset> implements
		Ruleset {

	private final String value;

	public RulesetLiteral(String value) {
		this.value = value;
	}

	@Override
	public String value() {
		return value;
	}

}
