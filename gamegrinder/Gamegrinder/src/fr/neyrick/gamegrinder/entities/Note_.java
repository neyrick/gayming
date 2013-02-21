package fr.neyrick.gamegrinder.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-02-21T14:17:20.568+0100")
@StaticMetamodel(Note.class)
public class Note_ {
	public static volatile SingularAttribute<Note, Long> id;
	public static volatile SingularAttribute<Note, String> author;
	public static volatile SingularAttribute<Note, String> text;
	public static volatile SingularAttribute<Note, Game> game;
}
