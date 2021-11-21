package com.orsami.anime.studio.web;

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

import com.orsami.anime.studio.Studio;
import com.orsami.anime.studio.StudioService;

@RestController
@RequestMapping("/studio")
public class StudioController {
	 private final StudioService service;
	 
	 private StudioController(StudioService service) {
		 this.service = service;
	 }
	 
	 @GetMapping("/{id}")
	 @ResponseBody
	 public StudioView getStudio(@PathVariable Long id) {
		 return service.getStudio(id);
	 }

	 @GetMapping
	 @ResponseBody
	 public Page<StudioView> getAllStudios(@PageableDefault(sort="id", direction=Sort.Direction.ASC) Pageable pageable){

		 return service.findAllCharacter(pageable);}
	 
	 @PostMapping
	 @ResponseStatus(HttpStatus.CREATED)
	 @ResponseBody
	 public StudioView create(@RequestBody @Valid StudioBaseReq req) {
		 return service.create(req);
	 }
	 
	 @DeleteMapping("/{id}")
	 @ResponseStatus(HttpStatus.NO_CONTENT)
	 public void delete(@PathVariable Long id) {
		 service.delete(id);
	 }
	 @PutMapping("/{id}")
	 public StudioView update(@PathVariable(name="id") Long id,
			 @RequestBody @Valid StudioBaseReq req) {
		 Studio studio = service.FindStudioOrThrow(id);
		 return service.update(studio, req);
		 
	 }
	 
}
