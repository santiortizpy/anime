package com.orsami.anime.character.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.orsami.anime.character.web.CharacterView;
import com.orsami.anime.character.Character;

@Component
public class CharacterToCharacterViewConverter implements Converter<Character, CharacterView> {

	
	 @Override
	    public CharacterView convert(@NonNull Character character) {
		 CharacterView view = new CharacterView();
	        view.setId(character.getId());
	        view.setName(character.getName());
	        view.setEyeColor(character.getEyesColor());
	        view.setHairColor(character.getHairColor());
	        view.setGender(character.getGender());
	        view.setType(character.getType());
	        view.setTags(character.getTags());
	        return view;
	    }
}
