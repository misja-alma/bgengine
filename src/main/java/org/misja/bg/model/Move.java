
package org.misja.bg.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Move {
  private final List<CheckerMove> checkerMoves;

  public Move(CheckerMove... checkerMoves) {
    this(Arrays.asList(checkerMoves));
  }

  public Move(List<CheckerMove> checkerMoves) {
    this.checkerMoves = checkerMoves;
    Collections.sort(this.checkerMoves, descendingMoveComparator());
  }

  public List<CheckerMove> getCheckerMoves() {
    return checkerMoves;
  }

  @Override
  public String toString() {
    return checkerMoves.toString();
  }

  /**
   * This method assumes that the moves lists are sorted.
   *
   * @param o
   * @return
   */
  @Override
  public boolean equals(Object o) {
    if(!(o instanceof Move)) {
      return false;
    }
    Move other = (Move)o;
    return checkerMoves.equals(other.getCheckerMoves());
  }

  private static Comparator<CheckerMove> descendingMoveComparator() {
    return new Comparator<CheckerMove>() {
      @Override
      public int compare(CheckerMove checkerMove, CheckerMove checkerMove1) {
        int fromCompare = -Integer.valueOf(checkerMove.from).compareTo(Integer.valueOf(checkerMove1.from));
        if(fromCompare != 0) {
          return fromCompare;
        }
        return -Integer.valueOf(checkerMove.to).compareTo(Integer.valueOf(checkerMove1.to));
      }
    };
  }

  public static class CheckerMove {
    public final int from;
    public final int to;

    public CheckerMove(int from, int to) {
      this.from = from;
      this.to = to;
    }

    @Override
    public String toString() {
      return from + "-" + to;
    }

    @Override
    public boolean equals(Object o) {
      if(!(o instanceof CheckerMove)) {
        return false;
      }
      CheckerMove other = (CheckerMove)o;
      return from == other.from && to == other.to;
    }
  }
}
