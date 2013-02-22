package fr.neyrick.gamegrinder.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.neyrick.gamegrinder.entities.Setting;

@Stateless
public class SettingsManager {

	@PersistenceContext
	EntityManager em;
	
	public void storeSetting(Setting setting) {
		
		em.persist(setting);
		em.flush();
	}
	
	public List<Setting> fetchSettings(boolean open) {
		TypedQuery<Setting> query = em.createQuery("SELECT s from Setting s where s.open = ?1", Setting.class);
		query.setParameter(1, open);
		return query.getResultList();
	}

	public List<Setting> fetchSettings() {
		TypedQuery<Setting> query = em.createQuery("SELECT s from Setting s", Setting.class);
		return query.getResultList();
	}
}
