package com.devjoliveira.game_list.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.game_list.dto.GameDto;
import com.devjoliveira.game_list.dto.GameMinDto;
import com.devjoliveira.game_list.entities.Game;
import com.devjoliveira.game_list.projections.GameMinProjection;
import com.devjoliveira.game_list.repositories.GameRepository;

@Service
public class GameService {

  private final GameRepository gameRepository;

  public GameService(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  @Transactional(readOnly = true)
  public GameDto findById(Long id) {

    Optional<Game> game = gameRepository.findById(id);

    if (!game.isPresent()) {
      throw new RuntimeException("Game not found");
    }

    return new GameDto(game.get());

  }

  @Transactional(readOnly = true)
  public List<GameMinDto> findByList(Long listId) {

    List<GameMinProjection> games = gameRepository.searchByList(listId);

    return games.stream().map(GameMinDto::new).toList();

  }

  @Transactional(readOnly = true)
  public List<GameMinDto> findAll() {
    List<Game> games = gameRepository.findAll();
    return games.stream().map(GameMinDto::new).toList();

  }

}
