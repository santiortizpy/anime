package com.orsami.anime.character;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepo  extends JpaRepository<Character, Long>{

}
