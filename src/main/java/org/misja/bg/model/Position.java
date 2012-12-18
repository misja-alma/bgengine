package org.misja.bg.model;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

import static org.misja.bg.CollectionUtil.count;

public class Position {
  private final List<Integer> playerCheckers;
  private final List<Integer> opponentCheckers;

  public Position(List<Integer> playerCheckers, List<Integer> opponentCheckers) {
    this.playerCheckers = playerCheckers;
    this.opponentCheckers = opponentCheckers;
  }

  /**
   * Position: 0 = off, 25 = bar, 1..24 is the position of the checker from the player's point of view.
   *
   * @param player
   * @return the positions
   */
  public List<Integer> getCheckerPositions(Player player) {
    switch(player) {
      case PLAYER: return playerCheckers;
      case OPPONENT: return opponentCheckers;
      default: throw new IllegalArgumentException("Unknown player type: " + player);
    }
  }

  public boolean hasCheckersOnBar(Player player) {
    return getCheckerPositions(player).contains(25);
  }

  public boolean hasPoint(Player player, int point) {
    return count(getCheckerPositions(player), point) >= 2;
  }
}
