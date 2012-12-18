package org.misja.bg.model;

import com.google.common.collect.Lists;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MoveTest {
  @Test
  public void testEquals_differentMoves_falseReturned() throws Exception {
    Move move1 = new Move(Lists.newArrayList(new Move.CheckerMove(24, 18), new Move.CheckerMove(24, 21)));
    Move move2 = new Move(Lists.newArrayList(new Move.CheckerMove(24, 18), new Move.CheckerMove(24, 23)));

    assertFalse(move1.equals(move2));
  }

  @Test
  public void testEquals_sameMoves_trueReturned() throws Exception {
    Move move1 = new Move(Lists.newArrayList(new Move.CheckerMove(24, 18), new Move.CheckerMove(24, 21)));
    Move move2 = new Move(Lists.newArrayList(new Move.CheckerMove(24, 18), new Move.CheckerMove(24, 21)));

    assertTrue(move1.equals(move2));
  }

  @Test
  public void testEquals_sameMovesDifferentOrder_trueReturned() throws Exception {
    Move move1 = new Move(Lists.newArrayList(new Move.CheckerMove(24, 18), new Move.CheckerMove(24, 21)));
    Move move2 = new Move(Lists.newArrayList(new Move.CheckerMove(24, 21), new Move.CheckerMove(24, 18)));

    assertTrue(move1.equals(move2));
  }
}
