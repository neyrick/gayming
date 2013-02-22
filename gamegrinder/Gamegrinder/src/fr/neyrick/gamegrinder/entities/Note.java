package fr.neyrick.gamegrinder.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Note
 * 
 */
@Entity
@Table(name = "notes")
@NamedQueries({@NamedQuery(name = "fetchNotesInterval", query = "select n from Note n where n.postDate between ?1 and ?2"),
	@NamedQuery(name = "fetchNotesDate", query = "select n from Note n where n.postDate = ?1")})
public class Note implements Serializable {

	@Id
	private Long id;
	
	private String author;
	
	private String text;
	
	private Date postDate;
	
	private static final long serialVersionUID = 1L;

	public Note() {
		super();
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
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

	public Note(String author, String text, Date postDate) {
		super();
		this.author = author;
		this.text = text;
		this.postDate = postDate;
	}

}
