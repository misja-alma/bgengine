
package org.misja.bg.model;

import com.google.common.collect.Lists;
import org.misja.bg.game.GameState;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// TODO checkermove.toString; somehow show hit
// TODO after a hit; the next move doesnt seem to enter from the bar.
public class Move implements Action {
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
  public void applyTo(GameState gameState) {
    Side currentSideOnRoll = gameState.getSideOnRoll();
    for(CheckerMove checkerMove: checkerMoves) {
      Position nextPosition = checkerMove.applyTo(gameState.getPosition(), currentSideOnRoll);
      gameState.setPosition(nextPosition);
    }
    gameState.nextTurn();
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    for(int i=0; i<checkerMoves.size(); i++) {
      result.append(checkerMoves.get(i).toString());
      if(i < checkerMoves.size() - 1) {
        result.append(' ');
      }
    }
    return result.toString();
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

    public Position applyTo(Position position, Side side) {
      List<Integer> checkers = getNewCheckerPositionsForPlayer(position, side);
      List<Integer> opponentCheckers = getNewCheckerPositionsForOpponent(position, side);

      PositionBuilder builder = new PositionBuilder();
      builder.addCheckers(side, checkers);
      builder.addCheckers(side.invert(), opponentCheckers);
      return builder.build();
    }

    private List<Integer> getNewCheckerPositionsForOpponent(Position position, Side side) {
      List<Integer> opponentCheckers = Lists.newArrayList(position.getCheckerPositions(side.invert()));
      if (position.getNrCheckersOnPoint(side.invert(), 25 - to) == 1) {
        opponentCheckers.remove(Integer.valueOf(25 - to));
        opponentCheckers.add(0);
      }
      return opponentCheckers;
    }

    private List<Integer> getNewCheckerPositionsForPlayer(Position position, Side side) {
      List<Integer> checkers = Lists.newArrayList(position.getCheckerPositions(side));
      checkers.remove(Integer.valueOf(from));
      checkers.add(to);
      return checkers;
    }

    @Override
    public String toString() {
      return from + "/" + to;
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
