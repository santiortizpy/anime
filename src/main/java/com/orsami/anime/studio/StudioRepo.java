package com.orsami.anime.studio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepo  extends JpaRepository<Studio, Long>{

}
