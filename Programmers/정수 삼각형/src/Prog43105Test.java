import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Prog43105Test {
  @Test
  void case1() {
    Prog43105 test = new Prog43105(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}});
    assertEquals(30, test.solve());
  }
}