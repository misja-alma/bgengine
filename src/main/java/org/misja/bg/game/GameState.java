package org.misja.bg.game;

import org.misja.bg.model.DiceRoll;
import org.misja.bg.model.Position;
import org.misja.bg.model.Side;

public class GameState {
  private Position position;
  private DiceRoll diceRoll;
  private Side sideOnRoll;
  private Side winner;
  private int move;

  public boolean isGameFinished() {
    return winner != null;
  }

  public Side calculateWinner() {
    if(position.getNrCheckersOnPoint(Side.THIS_SIDE, 0) == 15) {
      return Side.THIS_SIDE;
    }
    if(position.getNrCheckersOnPoint(Side.OTHER_SIDE, 0) == 15) {
      return Side.OTHER_SIDE;
    }
    return null;
  }

  public DiceRoll getDiceRoll() {
    return diceRoll;
  }

  public void setDiceRoll(DiceRoll diceRoll) {
    this.diceRoll = diceRoll;
  }

  public Side getSideOnRoll() {
    return sideOnRoll;
  }

  public void setSideOnRoll(Side side) {
    sideOnRoll = side;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public int getMove() {
    return move;
  }

  public void nextMove() {
    move++;
  }

  public void nextTurn() {
    sideOnRoll = sideOnRoll.invert();
    if(sideOnRoll == Side.THIS_SIDE) {
      nextMove();
    }
  }

  public boolean isStartOfNewGame() {
    return move == 0;
  }

  public Side getWinner() {
    return winner;
  }

  public void setWinner(Side winner) {
    this.winner = winner;
  }
}
