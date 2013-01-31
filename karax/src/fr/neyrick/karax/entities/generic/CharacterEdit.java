package fr.neyrick.karax.entities.generic;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class CharacterEdit {

	public enum ExpenseType { NONE, CREATION, FREE, EXPERIENCE};
	
	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.ORDINAL)
	private ExpenseType expenseType;
	
	private String targetKey;
	
	private String locator;
	
	private String value;
	
	private int spentAmount;
	
	private Date date;	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}

	public String getTargetKey() {
		return targetKey;
	}

	public void setTargetKey(String targetKey) {
		this.targetKey = targetKey;
	}
	
	public String getLocator() {
		return locator;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

	public int getSpentAmount() {
		return spentAmount;
	}

	public void setSpentAmount(int amount) {
		this.spentAmount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
