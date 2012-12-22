package org.misja.bg.model;

import java.util.BitSet;
import java.util.List;

import static java.util.Collections.max;
import static org.misja.bg.CollectionUtil.count;

public class Position {
  private static final String BASE64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
  private static final int[] POSITIONS = {2, 3, 4, 5, 6, 7, 12, 13, 14, 15, 0, 1, 22, 23, 8, 9, 10, 11, 16, 17, 18, 19, 20, 21};

  private final List<Integer> playerCheckers;
  private final List<Integer> opponentCheckers;
  private transient String id;

  public Position(List<Integer> playerCheckers, List<Integer> opponentCheckers) {
    this.playerCheckers = playerCheckers;
    this.opponentCheckers = opponentCheckers;
  }

  /**
   * Position: 0 = off, 25 = bar, 1..24 is the position of the checker from the side's point of view.
   *
   * @param side
   * @return the positions
   */
  public List<Integer> getCheckerPositions(Side side) {
    switch(side) {
      case THIS_SIDE: return playerCheckers;
      case OTHER_SIDE: return opponentCheckers;
      default: throw new IllegalArgumentException("Unknown side type: " + side);
    }
  }

  public boolean hasCheckersOnBar(Side side) {
    return getCheckerPositions(side).contains(25);
  }

  public boolean hasPoint(Side side, int point) {
    return getNrCheckersOnPoint(side, point) >= 2;
  }

  public int getNrCheckersOnPoint(Side side, int point) {
    return count(getCheckerPositions(side), point);
  }

  public int getHighestPoint(Side side) {
    return max(getCheckerPositions(side));
  }

  public boolean isBearingOff(Side side) {
    return getHighestPoint(side) <= 6;
  }

  public String getId() {
    if(id == null) {
      id = getPositionId();
    }
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if(!(o instanceof Position)) {
      return false;
    }
    Position other = (Position) o;
    return getId().equals(other.getId());
  }

  @Override
  public int hashCode() {
    return getId().hashCode();
  }

  /**
   * Assumes that THIS_SIDE is always the one on roll. This matters for compatibility with the gnu id.
   *
   * @return the gnu position id
   */
  private String getPositionId() {
    BitSet bits = new BitSet();
    // Start with THIS_SIDE because it's assumed he's on roll.
    int pos = fillBitsForPlayer(Side.THIS_SIDE, bits, 0);
    fillBitsForPlayer(Side.OTHER_SIDE, bits, pos);
    return makeBase64String(bits, 14);
  }

  private int fillBitsForPlayer(Side side, BitSet bits, int pos) {
    for (int point = 1; point <= 25; point++) {
      int nr = getNrCheckersOnPoint(side, point);
      for (int t = 0; t < nr; t++) {
        bits.set(pos++, true);
      }
      pos++;
    }
    return pos;
  }

  private static String makeBase64String(BitSet bitString, int length){
    int[] result = new int[length];

    //  move every bit to its place
    for (int c = 0; c < 4; c++) {
      for (int d = 0; d < 24; d++) {
        int bitIndex = 24 * c + d;
        if (bitIndex < bitString.length()) { // we can have more bytes than there are bits.
          boolean bit = bitString.get(bitIndex);
          int bitPos = indexOf(POSITIONS, d);
          int bytePos = bitPos / 6;
          setBit(result, c * 4 + bytePos, bitPos % 6, bit);
        }
      }
    }

    // base 64 encoding
    char[] res = new char[result.length];
    for (int i = 0; i < result.length; i++) {
      res[i] = BASE64.charAt(result[i]);
    }
    return new String(res);
  }

  private static void setBit(int[] byteStr, int index, int bitPos, boolean value) {
    int mask = 1 << bitPos;
    if (value) {
      byteStr[index] = (byteStr[index] | mask);
    } else {
      byteStr[index] = (byteStr[index] & (~ mask));
    }
  }

  private static int indexOf(int[] array, int element) {
    for(int i=0; i<array.length; i++) {
      if(array[i] == element) {
        return i;
      }
    }
    return -1;
  }
}
