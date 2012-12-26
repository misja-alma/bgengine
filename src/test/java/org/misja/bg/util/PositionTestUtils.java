package org.misja.bg.util;

import org.misja.bg.model.Side;
import org.misja.bg.model.Position;
import org.misja.bg.model.PositionBuilder;

public class PositionTestUtils {

  public static Position createOneOnBarPosition() {
    PositionBuilder builder = new PositionBuilder();
    builder.addCheckers(Side.THIS_SIDE, 25, 24, 13, 13, 13, 13, 13, 8, 8, 8, 6, 6, 6, 6, 6);
    builder.addCheckers(Side.OTHER_SIDE, 24, 24, 13, 13, 13, 13, 13, 8, 8, 8, 6, 6, 6, 6, 6);
    return builder.build();
  }

  public static Position createTwoOnBarPosition() {
    PositionBuilder builder = new PositionBuilder();
    builder.addCheckers(Side.THIS_SIDE, 25, 25, 13, 13, 13, 13, 13, 8, 8, 8, 6, 6, 6, 6, 6);
    builder.addCheckers(Side.OTHER_SIDE, 24, 24, 13, 13, 13, 13, 13, 8, 8, 8, 6, 6, 6, 6, 6);
    return builder.build();
  }

  public static Position createInitialPosition() {
    PositionBuilder builder = new PositionBuilder();
    builder.addCheckers(Side.THIS_SIDE, 24, 24, 13, 13, 13, 13, 13, 8, 8, 8, 6, 6, 6, 6, 6);
    builder.addCheckers(Side.OTHER_SIDE, 24, 24, 13, 13, 13, 13, 13, 8, 8, 8, 6, 6, 6, 6, 6);
    return builder.build();
  }

  public static Position createPrimedPosition() {
    PositionBuilder builder = new PositionBuilder();
    builder.addCheckers(Side.THIS_SIDE, 24, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
    builder.addCheckers(Side.OTHER_SIDE, 2, 2, 4, 4, 5, 5, 6, 6, 6, 9, 9, 10, 10, 11, 11);
    return builder.build();
  }

  public static Position createShotPosition() {
    PositionBuilder builder = new PositionBuilder();
    builder.addCheckers(Side.THIS_SIDE, 24, 24, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
    builder.addCheckers(Side.OTHER_SIDE, 2, 13, 13, 13, 13, 13, 13, 13, 13, 9, 9, 10, 10, 11, 11);
    return builder.build();
  }

  public static Position createBearOffPosition() {
    PositionBuilder builder = new PositionBuilder();
    builder.addCheckers(Side.THIS_SIDE, 5, 5, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0);
    builder.addCheckers(Side.OTHER_SIDE, 2, 13, 13, 13, 13, 13, 13, 13, 13, 9, 9, 10, 10, 11, 11);
    return builder.build();
  }

  public static Position createWonPositionForThisSide() {
    PositionBuilder builder = new PositionBuilder();
    builder.addCheckers(Side.THIS_SIDE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    builder.addCheckers(Side.OTHER_SIDE, 0, 13, 13, 13, 13, 13, 13, 13, 13, 9, 9, 10, 10, 11, 11);
    return builder.build();
  }

  public static Position createWonPositionForOtherSide() {
    PositionBuilder builder = new PositionBuilder();
    builder.addCheckers(Side.OTHER_SIDE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    builder.addCheckers(Side.THIS_SIDE, 0, 13, 13, 13, 13, 13, 13, 13, 13, 9, 9, 10, 10, 11, 11);
    return builder.build();
  }

  public static Position createGammonPositionForThisSide() {
    PositionBuilder builder = new PositionBuilder();
    builder.addCheckers(Side.THIS_SIDE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    builder.addCheckers(Side.OTHER_SIDE, 18, 13, 13, 13, 13, 13, 13, 13, 13, 9, 9, 10, 10, 11, 11);
    return builder.build();
  }

  public static Position createBackgammonPositionForThisSide() {
    PositionBuilder builder = new PositionBuilder();
    builder.addCheckers(Side.THIS_SIDE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    builder.addCheckers(Side.OTHER_SIDE, 19, 13, 13, 13, 13, 13, 13, 13, 13, 9, 9, 10, 10, 11, 11);
    return builder.build();
  }
}
