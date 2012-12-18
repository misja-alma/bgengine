package org.misja.bg.model;

import com.google.common.collect.Lists;
import sun.plugin.dom.exception.InvalidStateException;

import java.util.List;

public class PositionBuilder {
  private List<Integer> playerCheckers = Lists.newArrayList();
  private List<Integer> opponentCheckers = Lists.newArrayList();

  public Position build() {
    if(playerCheckers.size() != 15) {
      throw new InvalidStateException("Wrong number of checkers for Player!");
    }
    if(opponentCheckers.size() != 15) {
      throw new InvalidStateException("Wrong number of checkers for Opponent!");
    }
    return new Position(playerCheckers, opponentCheckers);
  }

  public PositionBuilder addCheckers(Player player, int... points) {
    addCheckers(getCheckersForPlayer(player), points);
    return this;
  }

  public PositionBuilder addCheckers(Player player, List<Integer> checkers) {
    getCheckersForPlayer(player).addAll(checkers);
    return this;
  }

  private List<Integer> getCheckersForPlayer(Player player) {
    switch(player) {
      case PLAYER:
        return playerCheckers;
      case OPPONENT:
        return opponentCheckers;
      default: throw new IllegalArgumentException("Unknown player type: " + player);
    }
  }

  private void addCheckers(List<Integer> playerCheckers, int... points) {
    for(int point: points) {
      playerCheckers.add(point);
    }
  }
}
