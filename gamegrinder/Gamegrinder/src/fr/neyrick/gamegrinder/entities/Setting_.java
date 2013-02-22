package fr.neyrick.gamegrinder.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-02-21T23:59:51.213+0100")
@StaticMetamodel(Setting.class)
public class Setting_ {
	public static volatile SingularAttribute<Setting, Long> id;
	public static volatile SingularAttribute<Setting, String> name;
	public static volatile SingularAttribute<Setting, String> color;
	public static volatile SingularAttribute<Setting, Boolean> open;
}
