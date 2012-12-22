package org.misja.bg.engine;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.misja.bg.model.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MovesGenerator {

  public List<Move> generateAllMoves(Position position, Side side, DiceRoll dice) {
    List<Integer> dies = dice.toCanonical();
    List<Move.CheckerMove> moves = Lists.newArrayList();
    Set<Position> reachedPositions = Sets.newHashSet();
    List<Move> highDieFirstMoves = filterMaxLengthMoves(findMovesStartingWith(position, side, dies, moves, 0, reachedPositions));
    if (dice.isDoublet()) {
      return highDieFirstMoves;
    }
    List<Move> lowDieFirstMoves = filterMaxLengthMoves(findMovesStartingWith(position, side, Lists.reverse(dies), moves, 0, reachedPositions));
    int diesPlayedHighDieFirst = countDiesPlayed(highDieFirstMoves);
    int diesPlayedLowDieFirst = countDiesPlayed(lowDieFirstMoves);
    if (diesPlayedHighDieFirst < 2) {
      if (diesPlayedLowDieFirst > diesPlayedHighDieFirst) {
        return lowDieFirstMoves;
      } else {
        return highDieFirstMoves; // for incomplete moves with equal dice played, the higher die first move is the valid one.
      }
    }
    List<Move> result = Lists.newArrayList(highDieFirstMoves);
    if (diesPlayedLowDieFirst == diesPlayedHighDieFirst) {
      result.addAll(lowDieFirstMoves);
    }
    return result;
  }

  private List<Move> filterMaxLengthMoves(List<Move> moves) {
    List<Move> result = Lists.newArrayList();
    int longestMoveSoFar = 0;
    for (Move move : moves) {
      int sizeOfMove = move.getCheckerMoves().size();
      if (sizeOfMove > longestMoveSoFar) {
        longestMoveSoFar = sizeOfMove;
        result.clear();
      }
      if (sizeOfMove == longestMoveSoFar) {
        result.add(move);
      }
    }
    return result;
  }

  private int countDiesPlayed(List<Move> moves) {
    if (moves.isEmpty()) {
      return 0;
    }
    return moves.get(0).getCheckerMoves().size();
  }

  private boolean isValid(Position position, Side side, Move.CheckerMove checkerMove, int die) {
    if (position.hasPoint(side.invert(), 25 - checkerMove.to)) {
      return false;
    }
    if (checkerMove.to <= 0) {
      if (!position.isBearingOff(side)) {
        return false;
      }
      if(die > (checkerMove.from - checkerMove.to)) {
        return position.getHighestPoint(side) == checkerMove.from;
      }
      return true;
    }
    return true;
  }

  private List<Move> findMovesStartingWith(Position position, Side side, List<Integer> dies, List<Move.CheckerMove> checkerMoves, int currentDie, Set<Position> reachedPositions) {
    List<Move> result = Lists.newArrayList();
    if (checkerMoves.size() > 0) {
      result.add(new Move(Lists.newArrayList(checkerMoves))); // add partial move, just in case no more die can be played.
    }
    for (int checker : getPossibleCheckersToMove(position, side)) {
      Move.CheckerMove checkerMove = new Move.CheckerMove(checker, Math.max(0, checker - dies.get(currentDie)));
      if (isValid(position, side, checkerMove, dies.get(currentDie))) {
        Position newPosition = checkerMove.applyTo(position, side);
        if (reachedPositions.add(newPosition)) {
          result.addAll(getMovesAfterNewCheckerMove(side, dies, checkerMoves, currentDie, reachedPositions, checkerMove, newPosition));
        }
      }
    }
    return result;
  }

  private List<Move> getMovesAfterNewCheckerMove(Side side, List<Integer> dies, List<Move.CheckerMove> checkerMoves, int currentDie, Set<Position> reachedPositions, Move.CheckerMove checkerMove, Position newPosition) {
    List<Move> result;
    checkerMoves.add(checkerMove);
    if (currentDie == dies.size() - 1) {
      result = Lists.newArrayList(new Move(Lists.newArrayList(checkerMoves)));
    } else {
      result = findMovesStartingWith(newPosition, side, dies, checkerMoves, currentDie + 1, reachedPositions);
    }
    checkerMoves.remove(checkerMove);
    return result;
  }

  private static HashSet<Integer> getPossibleCheckersToMove(Position position, Side side) {
    if (position.hasCheckersOnBar(side)) {
      return Sets.newHashSet(25);
    }
    return distinct(position.getCheckerPositions(side));
  }

  private static HashSet<Integer> distinct(List<Integer> items) {
    return Sets.newHashSet(items);
  }
}
