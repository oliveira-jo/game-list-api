package com.devjoliveira.game_list.dto;

import com.devjoliveira.game_list.entities.Game;

public record GameMinDto(
    Long id,
    String title,
    Integer year,
    String imgUrl,
    String shortDescription) {

  public GameMinDto(Game entity) {
    this(
        entity.getId(),
        entity.getTitle(),
        entity.getYear(),
        entity.getImgUrl(),
        entity.getShortDescription());
  }
}
