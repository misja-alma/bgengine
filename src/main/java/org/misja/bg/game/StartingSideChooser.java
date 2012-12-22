package org.misja.bg.game;

import org.misja.bg.model.Side;

import java.util.Random;

public class StartingSideChooser {
  private Random random = new Random();

  public Side chooseStartingSide() {
    int choice = random.nextInt(2);
    return choice == 0? Side.THIS_SIDE: Side.OTHER_SIDE;
  }
}
