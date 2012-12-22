package org.misja.bg.simulation;

import org.misja.bg.game.*;
import org.misja.bg.model.Action;
import org.misja.bg.model.DiceRoll;
import org.misja.bg.model.Side;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Annotator implements Watcher {
  private static final String LINE_BREAK = "\r\n";

  private final BufferedWriter output;

  public Annotator() {
    output = new BufferedWriter(new OutputStreamWriter(System.out));
  }

  public Annotator(BufferedWriter output) {
    this.output = output;
  }

  @Override
  public void watchGameStart(GameContext gameContext, Side openingRollWinner) {
    writeTextForThisPlayer(" " + normalizePlayerName(gameContext.getPlayerOnSide(Side.THIS_SIDE)));
    writeTextForOtherPlayer(normalizePlayerName(gameContext.getPlayerOnSide(Side.OTHER_SIDE)));
    if(openingRollWinner != Side.THIS_SIDE) {
      writeTextForThisPlayer(getMoveNumberString(1));
    }
  }

  private String normalizePlayerName(Player playerOnSide) {
    return playerOnSide.toString().replace(" ", "_");
  }

  private void writeTextForOtherPlayer(String otherPlayerLine) {
    writeOutput(otherPlayerLine + LINE_BREAK);
  }

  private void writeTextForThisPlayer(String thisPlayerLine) {
    writeOutput(fixedWidth(thisPlayerLine, 32, true));
  }

  @Override
  public void watchAction(GameState gameState, Player player, Action action) {
    if(player.getSide() == Side.THIS_SIDE) {
      writeTextForThisPlayer(getActionLineForThisPlayer(gameState, action));
    } else {
      writeTextForOtherPlayer(" " + getActionString(gameState, action));
    }
  }

  @Override
  public void watchGameEnd(GameResult gameResult) {
    if(gameResult.getWinner() != null) {
      String winString = "      Wins 1 point";
      Player winner = gameResult.getWinner();
      if(winner.getSide() == Side.THIS_SIDE) {
        writeOutput(LINE_BREAK + winString);
      } else {
        writeTextForThisPlayer("");
        writeOutput(winString);
      }
    }
  }

  private String getActionLineForThisPlayer(GameState gameState, Action action) {
    return getMoveNumberString(gameState.getMove()) + getActionString(gameState, action);
  }

  private String getMoveNumberString(int move) {
    return fixedWidth(String.valueOf(move), 3, false) + ") ";
  }

  private String getActionString(GameState gameState, Action action) {
    return gameState.getDiceRoll() + ": " + action;
  }

  private String fixedWidth(String string, int width, boolean appendToEnd) {
    StringBuilder padding = new StringBuilder();
    while(padding.length() < width - string.length()) {
      padding.append(' ');
    }
    if(appendToEnd) {
      return string + padding.toString();
    }
    return padding.toString() + string;
  }

  private void writeOutput(String text) {
    try {
      output.write(text);
      output.flush();
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
