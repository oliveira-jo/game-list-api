package com.devjoliveira.game_list.dto;

import com.devjoliveira.game_list.entities.Game;

public record GameDto(

    Long id,
    String title,
    Integer year,
    String genre,
    String platforms,
    Double score,
    String imgUrl,
    String shortDescription,
    String longDescription) {

  public GameDto(Game entity) {
    this(
        entity.getId(),
        entity.getTitle(),
        entity.getYear(),
        entity.getGenre(),
        entity.getPlatforms(),
        entity.getScore(),
        entity.getImgUrl(),
        entity.getShortDescription(),
        entity.getLongDescription());
  }
}
