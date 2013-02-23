package fr.neyrick.gamegrinder.dao;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.neyrick.gamegrinder.entities.Game;
import fr.neyrick.gamegrinder.entities.PlayerAvailability;
import fr.neyrick.gamegrinder.entities.Setting;
import fr.neyrick.gamegrinder.entities.TimeFrame;

@Stateless
public class GameManager {

	@PersistenceContext
	EntityManager em;
	
	public void storeAvailability(PlayerAvailability avail) {
		
		em.persist(avail);
		em.flush();
	}
	
	public void deleteAvailability(String playerName, Setting setting, TimeFrame timeframe) {
		Query query = em.createNamedQuery("deletePlayerAvailability");
		query.setParameter(1, playerName);
		query.setParameter(2, setting);
		query.setParameter(3, timeframe);
		query.executeUpdate();
	}
	
	public void storeAvailabilities(Collection<PlayerAvailability> avails) {
		Iterator<PlayerAvailability> iter = avails.iterator();
		while(iter.hasNext()) {
			em.persist(iter.next());
		}		
		em.flush();
	}
	
	public void storeGame(Game game) {
		
		em.persist(game);
		em.flush();
	}
	
	public List<PlayerAvailability> fetchPlayers(Date minDate, Date maxDate) {
		TypedQuery<PlayerAvailability> query = em.createNamedQuery("fetchPlayers", PlayerAvailability.class);
		query.setParameter(1, minDate);
		query.setParameter(2, maxDate);
		return query.getResultList();
	}
	
	public List<Game> fetchGames(Date minDate, Date maxDate) {
		TypedQuery<Game> query = em.createNamedQuery("fetchGames", Game.class);
		query.setParameter(1, minDate);
		query.setParameter(2, maxDate);
		return query.getResultList();
	}
}
