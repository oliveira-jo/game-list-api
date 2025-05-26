package com.devjoliveira.game_list.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.game_list.dto.GameListDto;
import com.devjoliveira.game_list.entities.GameList;
import com.devjoliveira.game_list.projections.GameMinProjection;
import com.devjoliveira.game_list.repositories.GameListRepository;
import com.devjoliveira.game_list.repositories.GameRepository;

@Service
public class GameListService {

  private final GameRepository gameRepository;
  private final GameListRepository gameListRepository;

  public GameListService(GameListRepository gameListRepository, GameRepository gameRepository) {
    this.gameListRepository = gameListRepository;
    this.gameRepository = gameRepository;

  }

  @Transactional(readOnly = true)
  public List<GameListDto> findAll() {
    List<GameList> games = gameListRepository.findAll();
    return games.stream().map(GameListDto::new).toList();

  }

  @Transactional
  public void move(Long listId, int sourceIndex, int destinationIndex) {

    List<GameMinProjection> list = gameRepository.searchByList(listId);

    GameMinProjection obj = list.remove(sourceIndex);

    list.add(destinationIndex, obj);

    int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
    int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;

    for (int i = min; i <= max; i++) {
      gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
    }

  }

  public GameListDto addGameList(GameListDto request) {
    GameList newGameList = new GameList();
    newGameList.setName(request.name());

    return new GameListDto(this.gameListRepository.save(newGameList));

  }

  public void delete(Long id) {
    GameList fromDB = gameListRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Game list not found with id: " + id));

    gameListRepository.delete(fromDB);

  }

  public Object change(Long id, GameListDto request) {
    GameList fromDB = gameListRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("Game list not found with id: " + id));

    fromDB.setName(request.name());
    GameList updatedGameList = gameListRepository.save(fromDB);

    return new GameListDto(updatedGameList);

  }

}
