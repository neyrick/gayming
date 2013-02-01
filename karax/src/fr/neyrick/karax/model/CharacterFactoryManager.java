package fr.neyrick.karax.model;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import fr.neyrick.karax.entities.generic.MetaCharacter;

@ApplicationScoped
public class CharacterFactoryManager {

	private Set<CharacterFactory<? extends GameCharacter>> factories = new HashSet<CharacterFactory<?>>();
	
	public void registerFactory(CharacterFactory<? extends GameCharacter> factory) {
		factories.add(factory);
	}
	
	public void unregisterFactory(CharacterFactory<? extends GameCharacter> factory) {
		factories.remove(factory);
	}

	public void clear() {
		factories.clear();
	}
	
	public CharacterFactory<? extends GameCharacter> getValidFactory(MetaCharacter character) {
		for(CharacterFactory<?> factory : factories) {
			if (factory.isValid(character)) return factory;
		}
		return null;
	}
}
