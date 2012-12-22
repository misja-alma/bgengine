package org.misja.bg.simulation;

import org.misja.bg.game.*;
import org.misja.bg.model.Side;

public class GamePlayer {

  public static void main(String[] args) {
    GamePlayer gamePlayer = new GamePlayer();
    gamePlayer.playGames(1, new Annotator());
  }

  public void playGames(int nrGames, Watcher... watchers) {
    Controller controller = new Controller();
    GameStateBuilder gameStateBuilder = new GameStateBuilder();
    GameContext gameContext = createGameContext();
    doPlayGames(nrGames, controller, gameStateBuilder, gameContext, watchers);
  }

  private void doPlayGames(int nrGames, Controller controller, GameStateBuilder gameStateBuilder, GameContext gameContext, Watcher[] watchers) {
    for(int i=0; i<nrGames; i++) {
      GameState gameState = gameStateBuilder.buildInitialGameState();
      controller.finishGame(gameContext, gameState, watchers);
    }
  }

  private GameContext createGameContext() {
    GameContext gameContext = new GameContext();
    Player player1Simulator = new Player("Player1");
    player1Simulator.setSide(Side.THIS_SIDE);
    Player player2Simulator = new Player("Player2");
    player2Simulator.setSide(Side.OTHER_SIDE);
    gameContext.setPlayer1(player1Simulator);
    gameContext.setPlayer2(player2Simulator);
    return gameContext;
  }
}
