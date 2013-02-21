package fr.neyrick.gamegrinder.jsf;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class Visitor implements Serializable {

	private String name;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isKnown() {
		return (name != null);
	}

	public String clear() {
		this.name = null;
		return null;
	}
	
	public Visitor() {
	}

}
