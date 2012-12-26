package org.misja.bg.game;

import org.misja.bg.model.Position;
import org.misja.bg.model.Side;

public class ResultCalculator {

  public Side calculateWinner(GameState gameState) {
    Position position = gameState.getPosition();
    if(position.getNrCheckersOnPoint(Side.THIS_SIDE, 0) == 15) {
      return Side.THIS_SIDE;
    }
    if(position.getNrCheckersOnPoint(Side.OTHER_SIDE, 0) == 15) {
      return Side.OTHER_SIDE;
    }
    return null;
  }

  public GameResult calculateResult(GameContext gameContext, GameState gameState) {
    GameResult result = new GameResult();
    Side winningSide = calculateWinner(gameState);
    if(winningSide == null) {
      return result;
    }
    result.setWinner(gameContext.getPlayerOnSide(winningSide));
    if(isSingleWin(gameState, winningSide)) {
      result.setPointsWon(1);
    } else {
      if(isBackgammon(gameState, winningSide)) {
        result.setPointsWon(3);
      } else {
        result.setPointsWon(2);
      }
    }
    return result;
  }

  private boolean isSingleWin(GameState gameState, Side winningSide) {
    return gameState.getPosition().getNrCheckersOnPoint(winningSide.invert(), 0) > 0;
  }

  private boolean isBackgammon(GameState gameState, Side winningSide) {
    return gameState.getPosition().getNrCheckersBeforePoint(winningSide.invert(), 18) > 0;
  }
}
