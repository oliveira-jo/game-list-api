package com.devjoliveira.game_list.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devjoliveira.game_list.services.GameService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/games")
public class GameController {

  private final GameService gameService;

  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @GetMapping
  public ResponseEntity<?> findAll() {
    return ResponseEntity.ok(gameService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(gameService.findById(id));
  }

}
