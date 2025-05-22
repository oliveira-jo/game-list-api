package com.devjoliveira.game_list.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devjoliveira.game_list.services.GameListService;
import com.devjoliveira.game_list.services.GameService;

@RestController
@RequestMapping("/game-lists")
public class GameListController {

  private final GameListService gameListService;
  private final GameService gameService;

  public GameListController(GameListService gameListService, GameService gameService) {
    this.gameListService = gameListService;
    this.gameService = gameService;
  }

  @GetMapping
  public ResponseEntity<?> findAll() {
    return ResponseEntity.ok(gameListService.findAll());
  }

  // http://localhost:8080/game-lists/1/games
  @GetMapping("/{listId}/games")
  public ResponseEntity<?> findByList(@PathVariable Long listId) {
    return ResponseEntity.ok(gameService.findByList(listId));
  }

}
