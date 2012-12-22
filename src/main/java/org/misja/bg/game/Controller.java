package org.misja.bg.game;

import org.misja.bg.model.Action;
import org.misja.bg.model.DiceRoll;
import org.misja.bg.model.Side;

public class Controller {
  private Dice dice = new Dice();
  private StartingSideChooser startingSideChooser = new StartingSideChooser();

  public GameResult finishGame(GameContext gameContext, GameState startingState, Watcher... watchers) {
    while(startingState.calculateWinner() == null) {
      performNextAction(gameContext, startingState, watchers);
    }
    startingState.setWinner(startingState.calculateWinner());
    GameResult result = calculateResult(gameContext, startingState);
    reportGameEnd(result, watchers);
    return result;
  }

  public GameResult calculateResult(GameContext gameContext, GameState gameState) {
    GameResult result = new GameResult();
    Side winningSide = gameState.getWinner();
    if(winningSide == null) {
      return result;
    }
    result.setWinner(gameContext.getPlayerOnSide(winningSide));
    return result;
  }

  public void performNextAction(GameContext gameContext, GameState gameState, Watcher... watchers) {
    DiceRoll diceRoll = checkForGameStart(gameContext, gameState, watchers);
    Side sideOnRoll = gameState.getSideOnRoll();
    gameState.setDiceRoll(diceRoll);
    Player player = gameContext.getPlayerOnSide(sideOnRoll);
    Action action = player.generateAction(gameState);
    if(action != null) {
      action.applyTo(gameState);
    }
    reportMove(gameState, player, action, watchers);
  }

  private DiceRoll checkForGameStart(GameContext gameContext, GameState gameState, Watcher[] watchers) {
    DiceRoll diceRoll;
    if(gameState.isStartOfNewGame()) {
      Side openingRollWinner = startingSideChooser.chooseStartingSide();
      reportGameStart(gameContext, openingRollWinner, watchers);
      gameState.setSideOnRoll(openingRollWinner);
      diceRoll = dice.rollNonDoublet();
      gameState.nextMove();
    } else {
      diceRoll = dice.roll();
    }
    return diceRoll;
  }

  private void reportMove(GameState gameState, Player player, Action action, Watcher[] watchers) {
    for(Watcher watcher: watchers) {
      watcher.watchAction(gameState, player, action);
    }
  }

  private void reportGameStart(GameContext gameContext, Side openingRollWinner, Watcher[] watchers) {
    for(Watcher watcher: watchers) {
      watcher.watchGameStart(gameContext, openingRollWinner);
    }
  }

  private void reportGameEnd(GameResult gameResult, Watcher... watchers) {
    for(Watcher watcher: watchers) {
      watcher.watchGameEnd(gameResult);
    }
  }
}
