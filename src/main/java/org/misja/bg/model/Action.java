package org.misja.bg.model;

import org.misja.bg.game.GameState;

public interface Action {
  void applyTo(GameState gameState);
}
