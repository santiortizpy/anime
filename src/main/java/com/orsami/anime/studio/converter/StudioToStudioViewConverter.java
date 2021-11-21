package com.orsami.anime.studio.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import com.orsami.anime.studio.Studio;
import com.orsami.anime.studio.web.StudioView;

public class StudioToStudioViewConverter  implements Converter<Studio, StudioView>{
	
	@Override
    public StudioView convert(@NonNull Studio studio) {
		StudioView view = new StudioView();
        view.setId(studio.getId());
        view.setName(studio.getName());
        view.setDescription(studio.getDescription());
        return view;
    }

}
