package org.misja.bg.util;

import org.misja.bg.model.Player;
import org.misja.bg.model.Position;
import org.misja.bg.model.PositionBuilder;

public class PositionTestUtils {

  public static Position createOneOnBarPosition() {
    PositionBuilder builder = new PositionBuilder();
    builder.addCheckers(Player.PLAYER, 25, 24, 13, 13, 13, 13, 13, 8, 8, 8, 6, 6, 6, 6, 6);
    builder.addCheckers(Player.OPPONENT, 24, 24, 13, 13, 13, 13, 13, 8, 8, 8, 6, 6, 6, 6, 6);
    return builder.build();
  }

  public static Position createTwoOnBarPosition() {
    PositionBuilder builder = new PositionBuilder();
    builder.addCheckers(Player.PLAYER, 25, 25, 13, 13, 13, 13, 13, 8, 8, 8, 6, 6, 6, 6, 6);
    builder.addCheckers(Player.OPPONENT, 24, 24, 13, 13, 13, 13, 13, 8, 8, 8, 6, 6, 6, 6, 6);
    return builder.build();
  }

  public static Position createInitialPosition() {
    PositionBuilder builder = new PositionBuilder();
    builder.addCheckers(Player.PLAYER, 24, 24, 13, 13, 13, 13, 13, 8, 8, 8, 6, 6, 6, 6, 6);
    builder.addCheckers(Player.OPPONENT, 24, 24, 13, 13, 13, 13, 13, 8, 8, 8, 6, 6, 6, 6, 6);
    return builder.build();
  }

  public static Position createPrimedPosition() {
    PositionBuilder builder = new PositionBuilder();
    builder.addCheckers(Player.PLAYER, 24, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
    builder.addCheckers(Player.OPPONENT, 2, 2, 4, 4, 5, 5, 6, 6, 6, 9, 9, 10, 10, 11, 11);
    return builder.build();
  }

  public static Position createShotPosition() {
    PositionBuilder builder = new PositionBuilder();
    builder.addCheckers(Player.PLAYER, 24, 24, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
    builder.addCheckers(Player.OPPONENT, 2, 13, 13, 13, 13, 13, 13, 13, 13, 9, 9, 10, 10, 11, 11);
    return builder.build();
  }

  public static Position createBearOffPosition() {
    PositionBuilder builder = new PositionBuilder();
    builder.addCheckers(Player.PLAYER, 5, 5, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0);
    builder.addCheckers(Player.OPPONENT, 2, 13, 13, 13, 13, 13, 13, 13, 13, 9, 9, 10, 10, 11, 11);
    return builder.build();
  }
}
