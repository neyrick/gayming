package fr.neyrick.karax.model;

import java.text.DecimalFormat;

public class BonusFormat extends DecimalFormat {

	/**
	 * 
	 */
	private static final long serialVersionUID = -125706190637992929L;

	public String format(Number bonus) {
		if (bonus == null) return "NULL";
		if (bonus.equals(0)) return "0";
		else return super.format(bonus);
	}
	
	public String format(int bonus) {
		if (0 == bonus) return "0";
		else return super.format(bonus);
	}
	
	public BonusFormat() {
		super("+#,##0;-#");
	}

}
