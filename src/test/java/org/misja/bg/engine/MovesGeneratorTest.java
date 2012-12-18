package org.misja.bg.engine;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.misja.bg.model.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.misja.bg.model.MoveBuilder.move;

public class MovesGeneratorTest {
  private MovesGenerator movesGenerator = new MovesGenerator();

  @Test
  public void testGenerateAllMoves_allMovesForPlayerReturned() throws Exception {
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

    List<Move> result = movesGenerator.generateAllMoves(initialPosition, Player.PLAYER, new DiceRoll(6, 1));

    assertThat(result, containsInAnyOrder(expected.toArray()));
    assertThat(result.size(), is(expected.size()));
  }

  private Position createInitialPosition() {
    PositionBuilder builder = new PositionBuilder();
    builder.addCheckers(Player.PLAYER, 24, 24, 13, 13, 13, 13, 13, 8, 8, 8, 6, 6, 6, 6, 6);
    builder.addCheckers(Player.OPPONENT, 24, 24, 13, 13, 13, 13, 13, 8, 8, 8, 6, 6, 6, 6, 6);
    return builder.build();
  }
}
