import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {
  Dijkstra dijkstra = new Dijkstra(6, new int[][]{
    {0, 10, 30, 15, Dijkstra.MAX_VALUE, Dijkstra.MAX_VALUE},
    {Dijkstra.MAX_VALUE, 0, Dijkstra.MAX_VALUE, Dijkstra.MAX_VALUE, 20, Dijkstra.MAX_VALUE},
    {Dijkstra.MAX_VALUE, Dijkstra.MAX_VALUE, 0, Dijkstra.MAX_VALUE, Dijkstra.MAX_VALUE, 5},
    {Dijkstra.MAX_VALUE, Dijkstra.MAX_VALUE, 5, 0, Dijkstra.MAX_VALUE, 20},
    {Dijkstra.MAX_VALUE, Dijkstra.MAX_VALUE, Dijkstra.MAX_VALUE, Dijkstra.MAX_VALUE, 0, 20},
    {Dijkstra.MAX_VALUE, Dijkstra.MAX_VALUE, Dijkstra.MAX_VALUE, 20, Dijkstra.MAX_VALUE, 0}
  });

  @Test
  void simple() {
    assertArrayEquals(new int[]{0, 10, 20, 15, 30, 25}, dijkstra.solveSimple(0));
  }

  @Test
  void priorityQueue() {
    assertArrayEquals(new int[]{0, 10, 20, 15, 30, 25}, dijkstra.solvePQ(0));
  }
}