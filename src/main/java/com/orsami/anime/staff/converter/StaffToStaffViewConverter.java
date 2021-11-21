package com.orsami.anime.staff.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.orsami.anime.staff.Staff;
import com.orsami.anime.staff.web.StaffView;

@Component
public class StaffToStaffViewConverter  implements Converter<Staff, StaffView>{

	@Override
	public StaffView convert(Staff source) {
		StaffView view = new StaffView();
		view.setId(source.getId());
		view.setName(source.getName());
		view.setDescription(source.getDescription());
		view.setBirthPlace(source.getBirthPlace());
		view.setBirthDay(source.getBirthDay());
		return view;
	}

}
