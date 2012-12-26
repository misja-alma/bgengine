package org.misja.bg.game;

public class GameResult {
  private Player winner;
  private int pointsWon;

  public Player getWinner() {
    return winner;
  }

  public void setWinner(Player winner) {
    this.winner = winner;
  }

  public int getPointsWon() {
    return pointsWon;
  }

  public void setPointsWon(int pointsWon) {
    this.pointsWon = pointsWon;
  }
}
