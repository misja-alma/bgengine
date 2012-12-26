package org.misja.bg.game;

import org.misja.bg.model.Action;
import org.misja.bg.model.DiceRoll;
import org.misja.bg.model.Side;

public class Controller {
  private Dice dice = new Dice();
  private ResultCalculator resultCalculator = new ResultCalculator();
  private StartingSideChooser startingSideChooser = new StartingSideChooser();

  public GameResult finishGame(GameContext gameContext, GameState startingState, Watcher... watchers) {
    while(!startingState.isGameFinished() && resultCalculator.calculateWinner(startingState) == null) {
      performNextAction(gameContext, startingState, watchers);
    }
    GameResult result = resultCalculator.calculateResult(gameContext, startingState);
    if(result.getWinner() != null) {
      startingState.setWinner(result.getWinner().getSide());
    }
    reportGameEnd(result, watchers);
    return result;
  }

  public void performNextAction(GameContext gameContext, GameState gameState, Watcher... watchers) {
    DiceRoll diceRoll = rollOrStartGame(gameContext, gameState, watchers);
    Side sideOnRoll = gameState.getSideOnRoll();
    gameState.setDiceRoll(diceRoll);
    Player player = gameContext.getPlayerOnSide(sideOnRoll);
    Action action = player.generateAction(gameState);
    action.applyTo(gameState);
    reportMove(gameState, player, action, watchers);
  }

  private DiceRoll rollOrStartGame(GameContext gameContext, GameState gameState, Watcher[] watchers) {
    if(gameState.isStartOfNewGame()) {
      Side openingRollWinner = startingSideChooser.chooseStartingSide();
      reportGameStart(gameContext, openingRollWinner, watchers);
      gameState.setSideOnRoll(openingRollWinner);
      gameState.setMove(1);
      return dice.rollNonDoublet();
    } else {
      return dice.roll();
    }
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
