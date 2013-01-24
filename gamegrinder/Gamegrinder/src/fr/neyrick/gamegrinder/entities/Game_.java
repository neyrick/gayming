package fr.neyrick.gamegrinder.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-01-24T14:49:44.734+0100")
@StaticMetamodel(Game.class)
public class Game_ {
	public static volatile SingularAttribute<Game, Long> id;
	public static volatile SingularAttribute<Game, String> setting;
	public static volatile SingularAttribute<Game, String> gmname;
	public static volatile SetAttribute<Game, PlayerAvailability> players;
	public static volatile SingularAttribute<Game, TimeFrame> timeFrame;
}
