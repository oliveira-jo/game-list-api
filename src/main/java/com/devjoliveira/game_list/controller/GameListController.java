package com.devjoliveira.game_list.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devjoliveira.game_list.services.GameListService;

@RestController
@RequestMapping("/game-lists")
public class GameListController {

  private final GameListService gameListService;

  public GameListController(GameListService gameListService) {
    this.gameListService = gameListService;
  }

  @GetMapping
  public ResponseEntity<?> findAll() {
    return ResponseEntity.ok(gameListService.findAll());
  }

}
