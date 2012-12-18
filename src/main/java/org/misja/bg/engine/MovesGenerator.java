package org.misja.bg.engine;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.misja.bg.model.*;

import java.util.HashSet;
import java.util.List;

public class MovesGenerator {
  public List<Move> generateAllMoves(Position position, Player player, DiceRoll dice) {
    List<Integer> dies = dice.toCanonical();
    if(position.hasCheckersOnBar(player)) {
      return null; // TODO
    } else {
      return findMovesStartingWith(position, player, dies, Lists.<Move.CheckerMove>newArrayList(), 0);  // TODO doublets will have duplicates
    }
  }

  private boolean isValid(Position position, Player player, Move.CheckerMove checkerMove) {
    if(position.hasPoint(player.invert(), 25 - checkerMove.to)) {
      return false;
    }
    if(checkerMove.to <= 0) {
      return false; // TODO bearoff
    }
    return true; // TODO
  }

  private Position applyMove(Position position, Player player, Move.CheckerMove checkerMove) {
    List<Integer> checkers = Lists.newArrayList(position.getCheckerPositions(player));
    checkers.remove(Integer.valueOf(checkerMove.from));
    checkers.add(Integer.valueOf(checkerMove.to)); // TODO it might be a hit!
    PositionBuilder builder = new PositionBuilder();
    builder.addCheckers(player, checkers);
    builder.addCheckers(player.invert(), position.getCheckerPositions(player.invert()));
    return builder.build();
  }

  private List<Move> findMovesStartingWith(Position position, Player player, List<Integer> dies, List<Move.CheckerMove> checkerMoves, int currentDie) {
    List<Move> result = Lists.newArrayList();
    for(int checker: distinctCheckerPositions(position, player)) {
      Move.CheckerMove checkerMove = new Move.CheckerMove(checker, checker - dies.get(currentDie));
      if(isValid(position, player, checkerMove)) {
        checkerMoves.add(checkerMove);
        if(currentDie == dies.size() - 1) {
          result.add(new Move(Lists.newArrayList(checkerMoves)));
        } else {
          result.addAll(findMovesStartingWith(applyMove(position, player, checkerMove), player, dies, checkerMoves, currentDie + 1));
        }
        checkerMoves.remove(checkerMove);
      }
    }
    return result;
  }

  private static HashSet<Integer> distinctCheckerPositions(Position position, Player player) {
    return Sets.newHashSet(position.getCheckerPositions(player));
  }
}
