package org.misja.bg.engine;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.misja.bg.model.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.misja.bg.model.MoveBuilder.move;
import static org.misja.bg.util.PositionTestUtils.*;

public class MovesGeneratorTest {
  private MovesGenerator movesGenerator = new MovesGenerator();

  @Test
  public void testGenerateAllMoves_initialPosition_allMovesForPlayerReturned() throws Exception {
    Position initialPosition = createInitialPosition();
    List<Move> expected = Lists.newArrayList(
        move(24, 18, 24, 23),
        move(24, 18, 8, 7),
        move(24, 18, 6, 5),
        move(13, 7, 24, 23),
        move(13, 7, 8, 7),
        move(13, 7, 7, 6),
        move(13, 7, 6, 5),
        move(8, 2, 24, 23),
        move(8, 2, 8, 7),
        move(8, 2, 6, 5));

    List<Move> result = movesGenerator.generateAllMoves(initialPosition, Side.THIS_SIDE, new DiceRoll(6, 1));

    assertThat(result, containsInAnyOrder(expected.toArray()));
    assertThat(result.size(), is(expected.size()));
  }

  @Test
  public void testGenerateAllMoves_checkerOnBarOnePointBlocked_allMovesForPlayerReturned() throws Exception {
    Position initialPosition = createOneOnBarPosition();
    List<Move> expected = Lists.newArrayList(
        move(25, 24, 24, 18),
        move(25, 24, 13, 7),
        move(25, 24, 8, 2));

    List<Move> result = movesGenerator.generateAllMoves(initialPosition, Side.THIS_SIDE, new DiceRoll(6, 1));

    assertThat(result, containsInAnyOrder(expected.toArray()));
    assertThat(result.size(), is(expected.size()));
  }

  @Test
  public void testGenerateAllMoves_checkerOnBar_allMovesForPlayerReturned() throws Exception {
    Position initialPosition = createOneOnBarPosition();
    List<Move> expected = Lists.newArrayList(
        move(25, 24, 13, 11),
        move(25, 24, 8, 6),
        move(25, 24, 6, 4),
        move(25, 23, 24, 23),
        move(25, 23, 23, 22),
        move(25, 23, 8, 7),
        move(25, 23, 6, 5));

    List<Move> result = movesGenerator.generateAllMoves(initialPosition, Side.THIS_SIDE, new DiceRoll(2, 1));

    assertThat(result, containsInAnyOrder(expected.toArray()));
    assertThat(result.size(), is(expected.size()));
  }

  @Test
  public void testGenerateAllMoves_twoCheckersOnBar_allMovesForPlayerReturned() throws Exception {
    Position initialPosition = createTwoOnBarPosition();
    List<Move> expected = Lists.newArrayList(
        move(25, 23, 25, 24));

    List<Move> result = movesGenerator.generateAllMoves(initialPosition, Side.THIS_SIDE, new DiceRoll(2, 1));

    assertThat(result, containsInAnyOrder(expected.toArray()));
    assertThat(result.size(), is(expected.size()));
  }

  @Test
  public void testGenerateAllMoves_dancing_emptyMoveReturned() throws Exception {
    Position initialPosition = createOneOnBarPosition();

    List<Move> result = movesGenerator.generateAllMoves(initialPosition, Side.THIS_SIDE, new DiceRoll(6, 6));

    assertThat(result.size(), is(1));
    assertThat(result.get(0).getCheckerMoves().size(), is(0));
  }

  @Test
  public void testGenerateAllMoves_twoOnBarOneEnters_partialMoveReturned() throws Exception {
    Position initialPosition = createTwoOnBarPosition();
    List<Move> expected = Lists.newArrayList(
        new Move(new Move.CheckerMove(25, 24)));

    List<Move> result = movesGenerator.generateAllMoves(initialPosition, Side.THIS_SIDE, new DiceRoll(6, 1));

    assertThat(result, containsInAnyOrder(expected.toArray()));
    assertThat(result.size(), is(expected.size()));
  }

  @Test
  public void testGenerateAllMoves_oneDieBlocked_highestDieMoveReturned() throws Exception {
    Position initialPosition = createPrimedPosition();
    List<Move> expected = Lists.newArrayList(
        new Move(new Move.CheckerMove(24, 18)));

    List<Move> result = movesGenerator.generateAllMoves(initialPosition, Side.THIS_SIDE, new DiceRoll(6, 2));

    assertThat(result, containsInAnyOrder(expected.toArray()));
    assertThat(result.size(), is(expected.size()));
  }

  @Test
  public void testGenerateAllMoves_highestDieBlocked_lowerDieMoveReturned() throws Exception {
    Position initialPosition = createPrimedPosition();
    List<Move> expected = Lists.newArrayList(
        new Move(new Move.CheckerMove(24, 22)));

    List<Move> result = movesGenerator.generateAllMoves(initialPosition, Side.THIS_SIDE, new DiceRoll(3, 2));

    assertThat(result, containsInAnyOrder(expected.toArray()));
    assertThat(result.size(), is(expected.size()));
  }

  @Test
  public void testGenerateAllMoves_hitPossible_hitsAndNonHitsReturned() throws Exception {
    Position initialPosition = createShotPosition();
    List<Move> expected = Lists.newArrayList(
        move(24, 19, 19, 18),
        move(24, 23, 23, 18),
        move(24, 23, 24, 19));

    List<Move> result = movesGenerator.generateAllMoves(initialPosition, Side.THIS_SIDE, new DiceRoll(5, 1));

    assertThat(result, containsInAnyOrder(expected.toArray()));
    assertThat(result.size(), is(expected.size()));
  }

  @Test
  public void testGenerateAllMoves_doublet_allMovesReturned() throws Exception {
    Position initialPosition = createInitialPosition();
    List<Move> expected = Lists.newArrayList(
        move(13, 8, 13, 8, 13, 8, 13, 8),
        move(13, 8, 13, 8, 13, 8, 8, 3),
        move(13, 8, 13, 8, 8, 3, 8, 3),
        move(13, 8, 8, 3, 8, 3, 8, 3));

    List<Move> result = movesGenerator.generateAllMoves(initialPosition, Side.THIS_SIDE, new DiceRoll(5, 5));

    assertThat(result, containsInAnyOrder(expected.toArray()));
    assertThat(result.size(), is(expected.size()));
  }

  @Test
  public void testGenerateAllMoves_bearOff_possibleMovesReturned() throws Exception {
    Position initialPosition = createBearOffPosition();
    List<Move> expected = Lists.newArrayList(
        move(5, 0, 1, 0),
        move(5, 0, 5, 4),
        move(5, 0, 2, 1));

    List<Move> result = movesGenerator.generateAllMoves(initialPosition, Side.THIS_SIDE, new DiceRoll(5, 1));

    assertThat(result, containsInAnyOrder(expected.toArray()));
    assertThat(result.size(), is(expected.size()));
  }

  @Test
  public void testGenerateAllMoves_bearOffWithOverage_possibleMovesReturned() throws Exception {
    Position initialPosition = createBearOffPosition();
    List<Move> expected = Lists.newArrayList(
        move(5, 0, 1, 0),
        move(5, 0, 5, 4),
        move(5, 0, 2, 1));

    List<Move> result = movesGenerator.generateAllMoves(initialPosition, Side.THIS_SIDE, new DiceRoll(6, 1));

    assertThat(result, containsInAnyOrder(expected.toArray()));
    assertThat(result.size(), is(expected.size()));
  }
}
