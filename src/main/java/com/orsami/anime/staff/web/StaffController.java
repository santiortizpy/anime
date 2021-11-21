package com.orsami.anime.staff.web;

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

import com.orsami.anime.staff.Staff;
import com.orsami.anime.staff.StaffService;

@RestController
@RequestMapping("/staffs")
public class StaffController {
 private final StaffService service;

public StaffController(StaffService st) {
	super();
	this.service = st;
}

@GetMapping("/{id}")
@ResponseBody
public StaffView getStaff (@PathVariable Long id) {
	return service.getStaff(id);
}

@GetMapping
@ResponseBody
public Page<StaffView> getAllStafs(@PageableDefault(sort="id", direction=Sort.Direction.ASC)Pageable pageable){
	return service.findAllStaff(pageable);
}

@PostMapping
@ResponseStatus(HttpStatus.CREATED)
@ResponseBody
public StaffView create(@RequestBody @Valid StaffBaseReq req) {
	return service.create(req);
}

@PutMapping("/{id}")
public StaffView updateStaff (@PathVariable(name="id")Long id,
		@RequestBody @Valid StaffBaseReq req) {
	Staff staff = service.findStaffOrThrow(id);
	return service.update(staff, req);
}

@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deleteStaff(@PathVariable Long id) {
	service.delete(id);
}
 
 
}
