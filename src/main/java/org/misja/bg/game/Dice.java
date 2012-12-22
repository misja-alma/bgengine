package org.misja.bg.game;

import org.misja.bg.model.DiceRoll;

import java.util.Random;

public class Dice {
  private Random random = new Random();

  public DiceRoll roll() {
    return new DiceRoll(rollDie(), rollDie());
  }

  private int rollDie() {
    return random.nextInt(6) + 1;
  }

  private int rollDieButNot(int die) {
    int semiDie = random.nextInt(5) + 1;
    if(semiDie >= die) {
      return semiDie + 1;
    }
    return semiDie;
  }

  public DiceRoll rollNonDoublet() {
    int die1 = rollDie();
    int die2 = rollDieButNot(die1);
    return new DiceRoll(die1, die2);
  }
}
