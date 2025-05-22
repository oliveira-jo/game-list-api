package com.devjoliveira.game_list.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.game_list.dto.GameListDto;
import com.devjoliveira.game_list.entities.GameList;
import com.devjoliveira.game_list.repositories.GameListRepository;

@Service
public class GameListService {

  private final GameListRepository gameListRepository;

  public GameListService(GameListRepository gameListRepository) {
    this.gameListRepository = gameListRepository;
  }

  @Transactional(readOnly = true)
  public List<GameListDto> findAll() {
    List<GameList> games = gameListRepository.findAll();
    return games.stream().map(GameListDto::new).toList();

  }

}
