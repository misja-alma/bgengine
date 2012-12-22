package org.misja.bg.model;

import com.google.common.collect.Lists;

import java.util.List;

public class DiceRoll {
  private final int higherDie;
  private final int lowerDie;

  public DiceRoll(int die1, int die2) {
    if(die1 > die2) {
      higherDie = die1;
      lowerDie = die2;
    } else {
      higherDie = die2;
      lowerDie = die1;
    }
  }

  public int getHigherDie() {
    return higherDie;
  }

  public int getLowerDie() {
    return lowerDie;
  }

  public boolean isDoublet() {
    return higherDie == lowerDie;
  }

  public List<Integer> toCanonical() {
    if(!isDoublet()) {
      return Lists.newArrayList(higherDie, lowerDie);
    } else {
      return Lists.newArrayList(higherDie, higherDie, higherDie, higherDie);
    }
  }

  @Override
  public String toString() {
    return higherDie +
        "" + lowerDie;
  }
}
