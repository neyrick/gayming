package fr.neyrick.gamegrinder.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-01-24T14:49:44.119+0100")
@StaticMetamodel(PlayerAvailability.class)
public class PlayerAvailability_ {
	public static volatile SingularAttribute<PlayerAvailability, Long> id;
	public static volatile SingularAttribute<PlayerAvailability, String> playerName;
	public static volatile SingularAttribute<PlayerAvailability, Game> game;
	public static volatile SingularAttribute<PlayerAvailability, TimeFrame> timeFrame;
}