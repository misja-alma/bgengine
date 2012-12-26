package org.misja.bg.util;

import org.misja.bg.game.GameContext;
import org.misja.bg.game.Player;
import org.misja.bg.model.Side;

public class GameContextUtils {
  public static GameContext createPlayer12GameContext() {
    GameContext gameContext = new GameContext();
    Player player1 = new Player("Player1");
    player1.setSide(Side.THIS_SIDE);
    Player player2 = new Player("Player2");
    player2.setSide(Side.OTHER_SIDE);
    gameContext.setPlayer1(player1);
    gameContext.setPlayer2(player2);
    return gameContext;
  }
}
