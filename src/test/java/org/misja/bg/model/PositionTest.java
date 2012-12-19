package org.misja.bg.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.misja.bg.util.PositionTestUtils.createInitialPosition;

public class PositionTest {
  @Test
  public void testGetId_initialPosition_correctGnuIdReturned() throws Exception {
    Position initialPosition = createInitialPosition();

    assertEquals("4HPwATDgc/ABMA", initialPosition.getId());
  }
}
