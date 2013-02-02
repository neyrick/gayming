package fr.neyrick.karax.rest;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
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
import fr.neyrick.karax.model.GameCharacter;
import fr.neyrick.karax.model.RulesetLiteral;

@Path("/charsnapshot")
@RequestScoped
public class CharacterSnapshotRESTService {

    @Inject
    private MetaCharacterRepository repository;

    @Inject @Any
    private Instance<CharacterFactory> characterFactoryInstance;
    
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_XML)
    public GameCharacter lookupCharacterById(@PathParam("id") long id) {
    	MetaCharacter metaCharacter = repository.findCompleteById(id);
        if (metaCharacter == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
		RulesetLiteral literal = new RulesetLiteral(metaCharacter.getGame().getRuleset());
		CharacterFactory factory = characterFactoryInstance.select(literal).get();
		if (factory == null) {
			throw new WebApplicationException(new IllegalArgumentException("No factory for this character"));
		}
        return factory.createCharacter(metaCharacter);
    }

}
