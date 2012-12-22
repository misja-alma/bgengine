package org.misja.bg.game;

import org.misja.bg.model.Action;
import org.misja.bg.model.DiceRoll;
import org.misja.bg.model.Side;

public interface Watcher {

  void watchGameStart(GameContext gameContext, Side openingRollWinner);

  void watchGameEnd(GameResult gameResult);

  void watchAction(GameState gameState, Player player, Action action);
}
