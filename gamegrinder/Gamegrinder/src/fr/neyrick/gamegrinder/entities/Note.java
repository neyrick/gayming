package fr.neyrick.gamegrinder.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Note
 * 
 */
@Entity
@Table(name = "notes")
public class Note implements Serializable {

	@Id
	private Long id;
	
	private String author;
	
	private String text;
	
	@ManyToOne
	private Game game;
	
	private static final long serialVersionUID = 1L;

	public Note() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Note(String author, String text, Game game) {
		super();
		this.author = author;
		this.text = text;
		this.game = game;
	}

}
