import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Prog42895Test {
  @Test
  void case1() {
    Prog42895 test = new Prog42895(5, 12);
    assertEquals(4, test.solve());
  }

  @Test
  void case2() {
    Prog42895 test = new Prog42895(2, 11);
    assertEquals(3, test.solve());
  }

  @Test
  void case3() {
    Prog42895 test = new Prog42895(5, 31168);
    assertEquals(-1, test.solve());
  }
}