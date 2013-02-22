package fr.neyrick.gamegrinder.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Setting
 *
 */
@Entity

public class Setting implements Serializable {

	   
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String color;
	
	private boolean open;
	
	private static final long serialVersionUID = 1L;

	public Setting() {
		super();
	}   
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	
	public String getLetter() {
		return name.substring(0, 1).toUpperCase();
	}
	
	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
   
}
