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

  public GameDto save(GameDto request) {

    return new GameDto(
        this.gameRepository.save(
            new Game(request)));

  }

  public void delete(Long id) {
    Game fromDB = gameRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Game not found with id: " + id));

    gameRepository.delete(fromDB);

  }

  public GameDto change(Long id, GameDto request) {
    Game fromDB = gameRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("Game list not found with id: " + id));

    fromDB.setTitle(request.title());
    fromDB.setYear(request.year());
    fromDB.setGenre(request.genre());
    fromDB.setPlatforms(request.platforms());
    fromDB.setScore(request.score());
    fromDB.setImgUrl(request.imgUrl());
    fromDB.setShortDescription(request.shortDescription());
    fromDB.setLongDescription(request.longDescription());

    return new GameDto(
        gameRepository.save(fromDB));

  }

}
