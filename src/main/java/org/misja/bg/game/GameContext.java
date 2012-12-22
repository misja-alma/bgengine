package org.misja.bg.game;

import org.misja.bg.model.Side;

public class GameContext {
  private Player player1;
  private Player player2;

  public Player getPlayerOnSide(Side side) {
    if(player1.getSide() == side) {
      return player1;
    }
    return player2;
  }

  public void setPlayer1(Player player1) {
    this.player1 = player1;
  }

  public void setPlayer2(Player player2) {
    this.player2 = player2;
  }
}
