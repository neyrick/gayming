package fr.neyrick.gamegrinder.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.neyrick.gamegrinder.entities.Note;

@Stateless
public class NotesManager {

	@PersistenceContext
	EntityManager em;
	
	public void storeNote(Note note) {
		
		em.persist(note);
		em.flush();
	}
	
	public List<Note> fetchNotes() {
		TypedQuery<Note> query = em.createQuery("SELECT n from Note n", Note.class);
		return query.getResultList();
	}

	public List<Note> fetchNotes(Date date) {
		TypedQuery<Note> query = em.createNamedQuery("Note.fetchNotesDate", Note.class);
		query.setParameter(1, date);
		return query.getResultList();
	}

	public List<Note> fetchNotes(Date minDate, Date maxDate) {
		TypedQuery<Note> query = em.createNamedQuery("Note.fetchNotesInterval", Note.class);
		query.setParameter(1, minDate);
		query.setParameter(2, maxDate);
		return query.getResultList();
	}
}
