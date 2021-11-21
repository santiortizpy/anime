package com.orsami.anime.anime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRepo extends JpaRepository<Anime, Long> {

}
