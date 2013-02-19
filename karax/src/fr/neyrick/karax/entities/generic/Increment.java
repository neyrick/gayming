package fr.neyrick.karax.entities.generic;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Embeddable;

@Embeddable
public class Increment {

	public static final String CREATION = "CREATION";
	public static final String FREE = "FREE";
	public static final String FREEBIE = "FREEBIE";
	public static final String MODIFIER = "MODIFIER";
	public static final String EXPERIENCE = "EXPERIENCE";

	public static final List<String> REGULAR_COSTS = Arrays.asList(CREATION,
			FREE, FREEBIE, EXPERIENCE);

	private int amount;

	private String amountType;

	private float multiplier;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getAmountType() {
		return amountType;
	}

	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}

	public float getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(float multiplier) {
		this.multiplier = multiplier;
	}

	public Increment() {
	}

}
