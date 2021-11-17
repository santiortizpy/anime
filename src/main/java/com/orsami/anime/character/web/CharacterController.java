package com.orsami.anime.character.web;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.orsami.anime.character.Character;
import com.orsami.anime.character.CharacterService;



@RestController
@RequestMapping("/character")
public class CharacterController {
	
	private final CharacterService service;

    public CharacterController(CharacterService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CharacterView getCoach(@PathVariable Long id) {
        return service.getCharacter(id);
    }

    @GetMapping
    @ResponseBody
    public Page<CharacterView> getAllCharacters(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllCharacters(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CharacterView create(@RequestBody @Valid CharacterBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoach(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public CharacterView updateCoach(@PathVariable(name = "id") Long id,
                                 @RequestBody @Valid CharacterBaseReq req) {
        Character character = service.findCharacterOrThrow(id);
        return service.update(character, req);
    }

}
