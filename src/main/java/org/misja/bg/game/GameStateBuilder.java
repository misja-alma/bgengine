package org.misja.bg.game;

import org.misja.bg.model.Position;
import org.misja.bg.model.PositionBuilder;

public class GameStateBuilder {
  private Position position;

  public GameState buildInitialGameState() {
    addPosition(new PositionBuilder().buildInitialPosition());
    return build();
  }

  public GameStateBuilder addPosition(Position position) {
    this.position = position;
    return this;
  }

  public GameState build() {
    if(position == null) {
      throw new IllegalStateException("No position set!");
    }
    GameState result = new GameState();
    result.setPosition(position);
    return result;
  }
}
