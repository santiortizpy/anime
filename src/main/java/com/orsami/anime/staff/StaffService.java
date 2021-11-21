package com.orsami.anime.staff;

import java.util.List;

import javax.transaction.Transactional;

import java.util.ArrayList;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.orsami.anime.error.EntityNotFoundException;
import com.orsami.anime.staff.converter.StaffToStaffViewConverter;
import com.orsami.anime.staff.web.StaffBaseReq;
import com.orsami.anime.staff.web.StaffView;
import com.orsami.anime.util.MessageUtil;

@Service
public class StaffService {

	private final StaffRepo repo;
	private final StaffToStaffViewConverter converter;
	private final MessageUtil messageUtil;
	
	public StaffService(StaffRepo repo, StaffToStaffViewConverter converter, MessageUtil messageUtil) {
		
		this.repo = repo;
		this.converter = converter;
		this.messageUtil = messageUtil;
	}
	
	public Staff findStaffOrThrow(Long id) {
		return repo.findById(id).orElseThrow(()-> new EntityNotFoundException(messageUtil.getMessage("staff.NotFound",id)));
	}
	
	public StaffView getStaff(Long id) {
		Staff staff = findStaffOrThrow(id);
		return converter.convert(staff);
	}
	
	public StaffView create(StaffBaseReq req) {
		Staff staff = new Staff();
		this.prepare(staff,req);
		Staff staffSave = repo.save(staff);
		return converter.convert(staffSave);
	}
	public Page<StaffView> findAllStaff(Pageable pageable){
		Page<Staff> staffs = repo.findAll(pageable);
		List<StaffView> staffViews = new ArrayList<>();
		staffs.forEach(staff ->{
			StaffView staffView = converter.convert(staff);
			staffViews.add(staffView);
		});
		return new PageImpl<>(staffViews, pageable, staffs.getTotalElements());
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			repo.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(messageUtil.getMessage("staff.NotFound",id));
		}
	}
	
	public StaffView update(Staff staff, StaffBaseReq req) {
		Staff newStaff = this.prepare(staff, req);
		Staff saveStaff = repo.save(newStaff);
		return converter.convert(saveStaff);
	}
	
	public Staff prepare(Staff staff, StaffBaseReq req) {
		staff.setName(req.getName());
		staff.setDescription(req.getDescription());
		staff.setBirthPlace(req.getBirthPlace());
		staff.setBirthDay(req.getBirthDay());
		return staff;
	}
	
	
}
