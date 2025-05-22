package com.devjoliveira.game_list.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devjoliveira.game_list.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long> {

}
