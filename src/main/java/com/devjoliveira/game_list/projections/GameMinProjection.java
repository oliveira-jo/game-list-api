package com.devjoliveira.game_list.projections;

public interface GameMinProjection {

  Long getId();

  String getTitle();

  Integer getYear();

  String getImgUrl();

  String getShortDescription();

  Integer getPosition();

}
