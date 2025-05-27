package com.devjoliveira.game_list.dto;

import com.devjoliveira.game_list.entities.Game;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record GameDto(

    Long id,
    @NotBlank String title,
    @NotNull @Positive Integer year,
    @NotBlank String genre,
    @NotBlank String platforms,
    @NotNull @Positive @Digits(integer = 2, fraction = 1) Double score,
    @NotBlank String imgUrl,
    @NotBlank String shortDescription,
    @NotBlank String longDescription) {

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
