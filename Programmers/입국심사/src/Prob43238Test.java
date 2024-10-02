import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Prob43238Test {

  @Test
  void solve() {
    assertEquals(28, new Prob43238(6, new int[]{7, 10}).solve());
  }

  @Test
  void case1() {
    assertEquals(280000000L, new Prob43238(6, new int[]{70000000, 100000000}).solve());
  }
}