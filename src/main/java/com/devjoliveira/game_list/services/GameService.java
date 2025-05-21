package com.devjoliveira.game_list.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devjoliveira.game_list.dto.GameMinDto;
import com.devjoliveira.game_list.entities.Game;
import com.devjoliveira.game_list.repositories.GameRepository;

@Service
public class GameService {

  private final GameRepository gameRepository;

  public GameService(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  public List<GameMinDto> findAll() {
    List<Game> games = gameRepository.findAll();
    return games.stream().map(GameMinDto::new).toList();

  }

}
