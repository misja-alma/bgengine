package org.misja.bg.model;

public enum Side {
  THIS_SIDE, OTHER_SIDE;

  public Side invert() {
    if(this == THIS_SIDE) {
      return OTHER_SIDE;
    } else {
      return THIS_SIDE;
    }
  }
}
