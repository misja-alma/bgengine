package org.misja.bg.model;

public enum Player {
  PLAYER, OPPONENT;

  public Player invert() {
    if(this == PLAYER) {
      return OPPONENT;
    } else {
      return PLAYER;
    }
  }
}
