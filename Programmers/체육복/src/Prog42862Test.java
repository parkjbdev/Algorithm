import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Prog42862Test {
  @Test
  void case1() {
    Prog42862 case1 = new Prog42862(5, new int[]{2, 4}, new int[]{1, 3, 5});
    assertEquals(5, case1.solve());
  }

  @Test
  void case2() {
    Prog42862 case2 = new Prog42862(5, new int[]{2, 4}, new int[]{3});
    assertEquals(4, case2.solve());
  }

  @Test
  void case3() {
    Prog42862 case3 = new Prog42862(3, new int[]{3}, new int[]{1});
    assertEquals(2, case3.solve());
  }

  @Test
  void case4() {
    Prog42862 case4 = new Prog42862(5, new int[]{2, 3, 4}, new int[]{3, 4, 5});
    assertEquals(4, case4.solve());
  }
}