package fr.neyrick.karax.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import fr.neyrick.karax.entities.generic.MetaCharacter;

@ApplicationScoped
public class MetaCharacterRepository {

    @Inject
    private EntityManager em;

    public MetaCharacter findById(Long id) {
        return em.find(MetaCharacter.class, id);
    }

    public MetaCharacter findCompleteById(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MetaCharacter> criteria = cb.createQuery(MetaCharacter.class);
        Root<MetaCharacter> character = criteria.from(MetaCharacter.class);
        character.fetch("edits", JoinType.LEFT).fetch("consequences", JoinType.LEFT);
        character.fetch("experienceGains", JoinType.LEFT);
        criteria.select(character).where(cb.equal(character.get("id"), id));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<MetaCharacter> findAllOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MetaCharacter> criteria = cb.createQuery(MetaCharacter.class);
        Root<MetaCharacter> character = criteria.from(MetaCharacter.class);
        criteria.select(character).orderBy(cb.asc(character.get("name")));
        return em.createQuery(criteria).getResultList();
    }
}
