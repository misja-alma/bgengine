package org.misja.bg.model;

import com.google.common.collect.Lists;
import sun.plugin.dom.exception.InvalidStateException;

import java.util.List;

public class PositionBuilder {
  private List<Integer> playerCheckers = Lists.newArrayList();
  private List<Integer> opponentCheckers = Lists.newArrayList();
  private Side cubeOwner;

  public Position build() {
    if(playerCheckers.size() != 15) {
      throw new InvalidStateException("Wrong number of checkers for Side!");
    }
    if(opponentCheckers.size() != 15) {
      throw new InvalidStateException("Wrong number of checkers for Opponent!");
    }
    return new Position(playerCheckers, opponentCheckers, cubeOwner);
  }

  public PositionBuilder addCheckers(Side side, int... points) {
    addCheckers(getCheckersForPlayer(side), points);
    return this;
  }

  public PositionBuilder addCheckers(Side side, List<Integer> checkers) {
    getCheckersForPlayer(side).addAll(checkers);
    return this;
  }

  public PositionBuilder addCubeOwner(Side owner) {
    cubeOwner = owner;
    return this;
  }

  private List<Integer> getCheckersForPlayer(Side side) {
    switch(side) {
      case THIS_SIDE:
        return playerCheckers;
      case OTHER_SIDE:
        return opponentCheckers;
      default: throw new IllegalArgumentException("Unknown side type: " + side);
    }
  }

  private void addCheckers(List<Integer> playerCheckers, int... points) {
    for(int point: points) {
      playerCheckers.add(point);
    }
  }

  public Position buildInitialPosition() {
    addCheckers(Side.THIS_SIDE, 24, 24, 13, 13, 13, 13, 13, 8, 8, 8, 6, 6, 6, 6, 6);
    addCheckers(Side.OTHER_SIDE, 24, 24, 13, 13, 13, 13, 13, 8, 8, 8, 6, 6, 6, 6, 6);
    return build();
  }
}
