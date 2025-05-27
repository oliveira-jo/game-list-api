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

import com.devjoliveira.game_list.dto.GameDto;
import com.devjoliveira.game_list.services.GameService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

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

  @PostMapping()
  public ResponseEntity<?> save(@RequestBody @Valid GameDto request) {
    return ResponseEntity.ok().body(gameService.save(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable @Positive Long id) {
    gameService.delete(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> change(@PathVariable @Positive Long id, @RequestBody @Valid GameDto request) {
    return ResponseEntity.ok().body(gameService.change(id, request));
  }

}
