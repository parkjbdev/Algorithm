import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Prob64061Test {
  @Test
  void case1() {
    assertEquals(4,
      new Prob64061(new int[][]{{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}},
        new int[]{1, 5, 3, 5, 1, 2, 1, 4}).solve());
  }
}