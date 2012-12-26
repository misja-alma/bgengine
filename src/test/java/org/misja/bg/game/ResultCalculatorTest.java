package org.misja.bg.game;

import org.junit.Test;
import org.misja.bg.model.Position;
import org.misja.bg.model.Side;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.misja.bg.util.GameContextUtils.createPlayer12GameContext;
import static org.misja.bg.util.PositionTestUtils.*;

public class ResultCalculatorTest {
  private ResultCalculator resultCalculator = new ResultCalculator();
  private GameContext gameContext = createPlayer12GameContext();

  @Test
  public void testCalculateResult_singleWinForThisSide() throws Exception {
    GameState gameState = new GameState();
    Position position = createWonPositionForThisSide();
    gameState.setPosition(position);

    GameResult result = resultCalculator.calculateResult(gameContext, gameState);

    assertThat(result.getWinner(), is(gameContext.getPlayerOnSide(Side.THIS_SIDE)));
    assertThat(result.getPointsWon(), is(1));
  }

  @Test
  public void testCalculateResult_singleWinForOtherSide() throws Exception {
    GameState gameState = new GameState();
    Position position = createWonPositionForOtherSide();
    gameState.setPosition(position);

    GameResult result = resultCalculator.calculateResult(gameContext, gameState);

    assertThat(result.getWinner(), is(gameContext.getPlayerOnSide(Side.OTHER_SIDE)));
    assertThat(result.getPointsWon(), is(1));
  }

  @Test
  public void testCalculateResult_gammonForThisSide() throws Exception {
    GameState gameState = new GameState();
    Position position = createGammonPositionForThisSide();
    gameState.setPosition(position);

    GameResult result = resultCalculator.calculateResult(gameContext, gameState);

    assertThat(result.getWinner(), is(gameContext.getPlayerOnSide(Side.THIS_SIDE)));
    assertThat(result.getPointsWon(), is(2));
  }

  @Test
  public void testCalculateResult_backgammonForThisSide() throws Exception {
    GameState gameState = new GameState();
    Position position = createBackgammonPositionForThisSide();
    gameState.setPosition(position);

    GameResult result = resultCalculator.calculateResult(gameContext, gameState);

    assertThat(result.getWinner(), is(gameContext.getPlayerOnSide(Side.THIS_SIDE)));
    assertThat(result.getPointsWon(), is(3));
  }
}
