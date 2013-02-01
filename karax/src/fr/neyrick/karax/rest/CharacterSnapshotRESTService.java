package fr.neyrick.karax.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.neyrick.karax.data.MetaCharacterRepository;
import fr.neyrick.karax.entities.generic.MetaCharacter;
import fr.neyrick.karax.model.CharacterFactory;
import fr.neyrick.karax.model.CharacterFactoryManager;
import fr.neyrick.karax.model.GameCharacter;

@Path("/charsnapshot")
@RequestScoped
public class CharacterSnapshotRESTService {

    @Inject
    private MetaCharacterRepository repository;

    @Inject
    private CharacterFactoryManager factoryManager;

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_XML)
    public GameCharacter lookupCharacterById(@PathParam("id") long id) {
    	MetaCharacter metaCharacter = repository.findCompleteById(id);
        if (metaCharacter == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        CharacterFactory<? extends GameCharacter> factory = factoryManager.getValidFactory(metaCharacter);
        if (factory == null) {
            throw new WebApplicationException(new IllegalArgumentException("No valid factory found for this character"));
        }
        return factory.createCharacter(metaCharacter);
    }

}
