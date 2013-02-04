package fr.neyrick.karax.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.enterprise.context.RequestScoped;

import fr.neyrick.karax.entities.generic.CharacterEdit;
import fr.neyrick.karax.entities.generic.MetaCharacter;

@RequestScoped
public abstract class CharacterFactory {

	private Map<String, CharacterFeature> editListenersMap = new TreeMap<>();
	
	public GameCharacter createCharacter(MetaCharacter metaCharacter)  {
	    GameCharacter character = initCharacter(metaCharacter);
		setMetadata(character, metaCharacter);
		processEdits(metaCharacter.getEdits());
		return character;
	}
	
	protected abstract GameCharacter initCharacter(MetaCharacter metaCharacter);

	private void setMetadata(GameCharacter character, MetaCharacter metaCharacter) {
		character.setCreationDate(metaCharacter.getCreationDate());
		character.setGame(metaCharacter.getGame());
		character.setMetaId(metaCharacter.getId());
		character.setName(metaCharacter.getName());
		character.setPlayerName(metaCharacter.getPlayerName());
		character.setExperience(metaCharacter.getExperienceTotal());
		character.setLastUpdate(metaCharacter.getLastUpdate());
	}

	public <T extends CharacterFeature> T registerListener(T listener) {
		editListenersMap.put(listener.getKey(), listener);
		return listener;
	}

	public <T extends CharacterFeature> T unregisterListener(T listener) {
		editListenersMap.remove(listener.getKey());
		return listener;
	}
	
	public void processEdits(List<CharacterEdit> edits) {
		CharacterFeature listener = null;
		for(CharacterEdit edit : edits) {
			listener = editListenersMap.get(edit.getTargetKey());
			if (listener != null) listener.recordEdit(edit);
		}
	}
	
}
