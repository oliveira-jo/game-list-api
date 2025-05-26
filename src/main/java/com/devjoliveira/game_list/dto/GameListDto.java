package com.devjoliveira.game_list.dto;

import com.devjoliveira.game_list.entities.GameList;

import jakarta.validation.constraints.NotBlank;

public record GameListDto(
    Long id,
    @NotBlank String name) {

  public GameListDto(GameList entity) {
    this(
        entity.getId(),
        entity.getName());
  }
}
