package fr.neyrick.karax.model;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import fr.neyrick.karax.entities.generic.MetaCharacter;

public abstract class AbstractCharacterFactory<T extends GameCharacter> implements CharacterFactory<T> {

	private Class<T> characterClass;
	
	@Inject
	private CharacterFactoryManager manager;
	
	@Override
	public T createCharacter(MetaCharacter metaCharacter)  {
		T character;
		try {
			character = characterClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		initCharacter(character, metaCharacter);
		initCustomCharacter(character, metaCharacter);
		return character;
	}

	protected abstract void initCustomCharacter(T character, MetaCharacter metaCharacter);

	private void initCharacter(T character, MetaCharacter metaCharacter) {
		character.setCreationDate(metaCharacter.getCreationDate());
		character.setGame(metaCharacter.getGame());
		character.setMetaId(metaCharacter.getId());
		character.setName(metaCharacter.getName());
		character.setPlayerName(metaCharacter.getPlayerName());
		character.setExperience(metaCharacter.getExperienceTotal());
		character.setLastUpdate(metaCharacter.getLastUpdate());
	}

	@PostConstruct
	private void register() {
		manager.registerFactory(this);
	}
	
	@PreDestroy
	private void unregister() {
		manager.unregisterFactory(this);
	}
}
