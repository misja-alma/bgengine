package org.misja.bg.simulation;

import org.junit.Before;
import org.junit.Test;
import org.misja.bg.game.GameState;
import org.misja.bg.game.Player;
import org.misja.bg.model.DiceRoll;
import org.misja.bg.model.Move;
import org.misja.bg.model.Side;

import java.io.BufferedWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.misja.bg.model.MoveBuilder.move;

public class AnnotatorTest {
  private Annotator annotator;

  private StringWriter outputCaptor;

  private Player thisPlayer = new Player("This");

  private Player otherPlayer = new Player("Other");

  @Before
  public void setUp() {
    thisPlayer.setSide(Side.THIS_SIDE);
    otherPlayer.setSide(Side.OTHER_SIDE);
    outputCaptor = new StringWriter();
    annotator = new Annotator(new BufferedWriter(outputCaptor));
  }

  @Test
  public void testWatchAction_moveOnThisSide_correctActionLogged() throws Exception {
    DiceRoll roll = new DiceRoll(6, 1);
    Move move1 = move(24, 18, 18, 17);
    GameState gameState = new GameState();
    gameState.setDiceRoll(roll);
    gameState.nextMove();

    annotator.watchAction(gameState, thisPlayer, move1);

    assertEquals("  1) 61: 24/18 18/17            ", outputCaptor.toString());
  }

  @Test
  public void testWatchAction_moveOnOtherSide_correctActionLogged() throws Exception {
    DiceRoll roll = new DiceRoll(6, 1);
    Move move1 = move(24, 18, 18, 17);
    GameState gameState = new GameState();
    gameState.setDiceRoll(roll);
    gameState.nextMove();

    annotator.watchAction(gameState, otherPlayer, move1);

    assertEquals(" 61: 24/18 18/17\r\n", outputCaptor.toString());
  }
}
