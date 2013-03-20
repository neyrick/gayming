package fr.neyrick.karax.eclipsephase.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import fr.neyrick.karax.eclipsephase.entities.MorphDefinition;

@ApplicationScoped
public class MorphRepository {

    @Inject
    private EntityManager em;

    public MorphDefinition findById(Long id) {
        return em.find(MorphDefinition.class, id);
    }

    public MorphDefinition findCompleteByKey(String key) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MorphDefinition> criteria = cb.createQuery(MorphDefinition.class);
        Root<MorphDefinition> character = criteria.from(MorphDefinition.class);
        character.fetch("edits", JoinType.LEFT);
        criteria.select(character).where(cb.equal(character.get("key"), key));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<MorphDefinition> findAllOrderedByKey() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MorphDefinition> criteria = cb.createQuery(MorphDefinition.class);
        Root<MorphDefinition> character = criteria.from(MorphDefinition.class);
        criteria.select(character).orderBy(cb.asc(character.get("key")));
        return em.createQuery(criteria).getResultList();
    }
}
