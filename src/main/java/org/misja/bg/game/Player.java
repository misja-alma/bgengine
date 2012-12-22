package org.misja.bg.game;

import org.misja.bg.engine.MovesGenerator;
import org.misja.bg.model.Action;
import org.misja.bg.model.Move;
import org.misja.bg.model.Side;

import java.util.List;

public class Player {
  private MovesGenerator movesGenerator = new MovesGenerator();
  private Side side;
  private final String name;

  public Player(String name) {
    this.name = name;
  }

  public Side getSide() {
    return side;
  }

  public void setSide(Side side) {
    this.side = side;
  }

  public Action generateAction(GameState gameState) {
    List<Move> moves = movesGenerator.generateAllMoves(gameState.getPosition(), side, gameState.getDiceRoll());
    return selectBestMove(moves);
  }

  private Action selectBestMove(List<Move> moves) {
    return moves.isEmpty()? null: moves.get(0); // TODO
  }

  @Override
  public String toString() {
    return name;
  }
}
