package com.orsami.anime.studio;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.orsami.anime.character.web.CharacterView;
import com.orsami.anime.error.EntityNotFoundException;
import com.orsami.anime.studio.converter.StudioToStudioViewConverter;
import com.orsami.anime.studio.web.StudioBaseReq;
import com.orsami.anime.studio.web.StudioView;
import com.orsami.anime.util.MessageUtil;

@Service
public class StudioService {
	
	private final StudioRepo  repo;
	private final StudioToStudioViewConverter converter;
	private final MessageUtil messageUtil;
	
	public StudioService(StudioRepo studioRepo, StudioToStudioViewConverter studioToStudioViewConverter, MessageUtil messageUtil) {
		this.repo = studioRepo;
		this.converter = studioToStudioViewConverter;
		this.messageUtil = messageUtil;
	}
	
	public Studio FindStudioOrThrow(Long id) {
		return repo.findById(id).orElseThrow(()->new EntityNotFoundException(messageUtil.getMessage("studio.NotFound",id)));
	}
	
	public StudioView getStudio(Long id) {
		Studio studio = FindStudioOrThrow(id);
		return converter.convert(studio);
	}
	
	public Page<StudioView> findAllCharacter(Pageable pageable){
		Page<Studio> studios = repo.findAll(pageable);
		List<StudioView> studioViews= new ArrayList<>();
		studios.forEach(studio -> {
			StudioView studioview = converter.convert(studio);
			studioViews.add(studioview);
		});
		return new PageImpl<>(studioViews,pageable, studios.getTotalElements());
	}
	public StudioView create (StudioBaseReq req) {
		Studio studio = new Studio();
		this.prepare(studio, req);
		Studio studioSave = repo.save(studio);
		return converter.convert(studioSave);
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			repo.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(messageUtil.getMessage("studio.NotFound",id));
		}
		
	}
	
	public StudioView update(Studio studio, StudioBaseReq req) {
		Studio newStudio = this.prepare(studio, req);
		Studio studioSave = repo.save(newStudio);
		return converter.convert(studioSave);
	}
	
	
	public Studio prepare(Studio studio, StudioBaseReq req) {
		studio.setName(req.getName());
		studio.setDescription(req.getDescription());
		return studio;
	}
}
