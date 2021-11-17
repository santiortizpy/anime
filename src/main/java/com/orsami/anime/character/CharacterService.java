package com.orsami.anime.character;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.orsami.anime.character.converter.CharacterToCharacterViewConverter;
import com.orsami.anime.character.web.CharacterBaseReq;
import com.orsami.anime.character.web.CharacterView;
import com.orsami.anime.error.EntityNotFoundException;
import com.orsami.anime.util.MessageUtil;

@Service
public class CharacterService {
	
	private final CharacterRepo repo;
	private final CharacterToCharacterViewConverter converter;
	private final MessageUtil messageUtil;
	
	public CharacterService(CharacterRepo characterRepo,
			CharacterToCharacterViewConverter characterToCharacterViewConverter,
            MessageUtil messageUtil) {
this.repo = characterRepo;
this.converter = characterToCharacterViewConverter;
this.messageUtil = messageUtil;
}

public Character findCharacterOrThrow(Long id) {
return repo.findById(id)
    .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("character.NotFound", id)));
}

public CharacterView getCharacter(Long id) {
Character character = findCharacterOrThrow(id);
return converter.convert(character);
}

public CharacterView create(CharacterBaseReq req) {
Character cha = new Character();
this.prepare(cha,req);
Character chaSave = repo.save(cha);
return converter.convert(chaSave);
}

public Page<CharacterView> findAllCharacters(Pageable pageable) {
Page<Character> characters = repo.findAll(pageable);
List<CharacterView> characterViews = new ArrayList<>();
characters.forEach(character -> {
	CharacterView characterView =converter.convert(character);
	characterViews.add(characterView);
});
return new PageImpl<>(characterViews, pageable, characters.getTotalElements());
}

@Transactional
public void delete(Long id) {
try {
repo.deleteById(id);
} catch (EmptyResultDataAccessException e) {
throw new EntityNotFoundException(messageUtil.getMessage("character.NotFound", id));
}
}

public CharacterView update(Character character, CharacterBaseReq req) {
	Character newCharacter = this.prepare(character, req);
	Character characterForSave = repo.save(newCharacter);
return converter.convert(characterForSave);
}

private Character prepare(Character character, CharacterBaseReq req) {
	
	character.setName(req.getName());
	character.setEyesColor(req.getEyesColor());
	character.setHairColor(req.getHairColor());
	character.setGender(req.getGender());
	character.setType(req.getType());
	character.setTags(req.getTags());
	return character;
}

}
