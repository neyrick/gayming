package fr.neyrick.gamegrinder.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-02-21T23:40:38.718+0100")
@StaticMetamodel(Note.class)
public class Note_ {
	public static volatile SingularAttribute<Note, Long> id;
	public static volatile SingularAttribute<Note, String> author;
	public static volatile SingularAttribute<Note, String> text;
	public static volatile SingularAttribute<Note, Date> postDate;
}
