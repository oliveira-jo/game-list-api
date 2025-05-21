package com.devjoliveira.game_list.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Belonging Primary Key class.
 * This class is used to create a composite key
 * 
 * Many for many a third table is created
 * where the primary key of the third table
 * is the composition of the two foreign keys.
 * 
 * Embeddable a type represents two fields
 * in the relational database
 * 
 */
@Embeddable
public class BelongingPK {

  @ManyToOne
  @JoinColumn(name = "game_id")
  private Game game;
  @ManyToOne
  @JoinColumn(name = "list_id")
  private GameList list;

  public BelongingPK() {
  }

  public BelongingPK(Game game, GameList gameList) {
    this.game = game;
    this.list = gameList;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public GameList getList() {
    return list;
  }

  public void setList(GameList gameList) {
    this.list = gameList;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((game == null) ? 0 : game.hashCode());
    result = prime * result + ((list == null) ? 0 : list.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BelongingPK other = (BelongingPK) obj;
    if (game == null) {
      if (other.game != null)
        return false;
    } else if (!game.equals(other.game))
      return false;
    if (list == null) {
      if (other.list != null)
        return false;
    } else if (!list.equals(other.list))
      return false;
    return true;
  }

}
