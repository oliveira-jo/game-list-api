package com.devjoliveira.game_list.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devjoliveira.game_list.dto.GameListDto;
import com.devjoliveira.game_list.dto.ReplacementDto;
import com.devjoliveira.game_list.services.GameListService;
import com.devjoliveira.game_list.services.GameService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

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

  @GetMapping("/{listId}/games")
  public ResponseEntity<?> findByList(@PathVariable Long listId) {
    return ResponseEntity.ok(gameService.findByList(listId));
  }

  @PostMapping("/{listId}/replacement")
  public ResponseEntity<Void> move(@PathVariable Long listId, @RequestBody ReplacementDto replacementDto) {
    gameListService.move(listId, replacementDto.sourceIndex(), replacementDto.destinationIndex());
    return ResponseEntity.ok().build();
  }

  @PostMapping()
  public ResponseEntity<?> save(@RequestBody @Valid GameListDto request) {
    return ResponseEntity.ok().body(gameListService.addGameList(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable @Positive Long id) {
    gameListService.delete(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> change(@PathVariable @Positive Long id, @RequestBody @Valid GameListDto request) {
    return ResponseEntity.ok().body(gameListService.change(id, request));
  }

}
