package com.devjoliveira.game_list.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.game_list.dto.GameListDto;
import com.devjoliveira.game_list.dto.GameMinDto;
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

}
