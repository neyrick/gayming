package fr.neyrick.karax.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.neyrick.karax.entities.generic.MetaCharacter;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class MetaCharacterRegistration {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<MetaCharacter> characterEventSrc;

    public void register(MetaCharacter character) throws Exception {
        log.info("Registering " + character.getName());
        em.persist(character);
        characterEventSrc.fire(character);
    }
}
