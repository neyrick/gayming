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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Setting other = (Setting) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
   
}
