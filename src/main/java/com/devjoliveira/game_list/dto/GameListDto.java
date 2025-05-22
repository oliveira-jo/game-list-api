package com.devjoliveira.game_list.dto;

import com.devjoliveira.game_list.entities.GameList;

public record GameListDto(
    Long id,
    String name) {

  public GameListDto(GameList entity) {
    this(
        entity.getId(),
        entity.getName());
  }
}
