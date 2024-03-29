package org.misja.bg.model;

import com.google.common.collect.Lists;

import java.util.List;

public class MoveBuilder {
  private List<Move.CheckerMove> checkerMoves = Lists.newArrayList();

  public Move build() {
    return new Move(checkerMoves);
  }

  public MoveBuilder addCheckerMove(Move.CheckerMove checkerMove) {
    checkerMoves.add(checkerMove);
    return this;
  }

  public static Move move(int from1, int to1, int from2, int to2) {
    return new Move(new Move.CheckerMove(from1, to1), new Move.CheckerMove(from2, to2));
  }

  public static Move move(int from1, int to1, int from2, int to2, int from3, int to3, int from4, int to4) {
    return new Move(new Move.CheckerMove(from1, to1), new Move.CheckerMove(from2, to2), new Move.CheckerMove(from3, to3), new Move.CheckerMove(from4, to4));
  }
}
