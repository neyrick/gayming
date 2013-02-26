package fr.neyrick.gamegrinder.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-02-25T20:47:29.708+0100")
@StaticMetamodel(Game.class)
public class Game_ {
	public static volatile SingularAttribute<Game, Long> id;
	public static volatile SingularAttribute<Game, Setting> setting;
	public static volatile SingularAttribute<Game, String> gmname;
	public static volatile ListAttribute<Game, PlayerAvailability> players;
	public static volatile SingularAttribute<Game, TimeFrame> timeFrame;
}
