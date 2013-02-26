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
	
	public void storeGame(Game game, Collection<Long> playerIds) {
		TypedQuery<Long> idquery = em.createQuery("SELECT id FROM PlayerAvailability pa WHERE pa.setting = ?1 and pa.playerName = ?2 and pa.timeFrame = ?3", Long.class);
		idquery.setParameter(1, game.getSetting());
		idquery.setParameter(2, game.getGmname());
		idquery.setParameter(3, game.getTimeFrame());
		List<Long> idList = idquery.getResultList();
		
		if (idList.size() ==  0) {
			PlayerAvailability pa = new PlayerAvailability();
			pa.setPlayerName(game.getGmname());
			pa.setSetting(game.getSetting());
			pa.setTimeFrame(game.getTimeFrame());
			em.persist(pa);
			playerIds.add(pa.getId());
		}
		else {
			playerIds.addAll(idList);
		}
		
		em.persist(game);

		Query query = em.createQuery("UPDATE PlayerAvailability pa SET pa.game = ?1 WHERE pa.id IN (?2)");
		query.setParameter(1, game);
		query.setParameter(2, playerIds);
		
		query.executeUpdate();
		
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

	public void clearAvailability(String playerName, TimeFrame timeframe) {
		Query query = em.createNamedQuery("deletePlayerAvailabilityForTimeFrame");
		query.setParameter(1, playerName);
		query.setParameter(2, timeframe);
		query.executeUpdate();
	}

	public void removeGame(Game game) {
		Query query = em.createQuery("UPDATE PlayerAvailability pa SET pa.game = null WHERE pa.game = ?1");
		query.setParameter(1, game);
		query.executeUpdate();
		query = em.createQuery("DELETE FROM Game g WHERE g.id = ?1");
		query.setParameter(1, game.getId());
		query.executeUpdate();
	}
}
