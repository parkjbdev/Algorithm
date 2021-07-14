import java.util.Arrays;
import java.util.OptionalInt;

public class Solution {
  public int solution(int n, int[][] edge) {
    return new Prob49189(n, edge).solve();
  }
}

class Prob49189 {
  public final int N;
  public final boolean[][] isConnect;
  private final int MAX_VALUE = 1000000;

  public Prob49189(int n, int[][] edge) {
    this.N = n;

    // Adjacent Matrix
    this.isConnect = new boolean[this.N][this.N];
    for (int[] node : edge) {
      this.isConnect[node[0] - 1][node[1] - 1] = true;
      this.isConnect[node[1] - 1][node[0] - 1] = true;
    }
  }

  private int[] dijkstra(int startVertex) {
    int[] minDistance = new int[N];
    for (int i = 0; i < N; i++) minDistance[i] = MAX_VALUE;
    minDistance[startVertex] = 0;

    boolean[] isVisit = new boolean[N];

    while (true) {
      int min = MAX_VALUE, idx = -1;
      for (int i = 0; i < N; i++) {
        if (isVisit[i]) continue;
        if (min > minDistance[i]) {
          min = minDistance[i];
          idx = i;
        }
      }
      if (idx < 0) break;

      isVisit[idx] = true;

      for (int i = 0; i < N; i++) {
        if (!isVisit[i] && isConnect[idx][i])
          minDistance[i] = Math.min(minDistance[i], minDistance[idx] + 1);
      }
    }
    return minDistance;
  }

  public int solve() {
    int[] arr = dijkstra(0);
    OptionalInt tmp = Arrays.stream(arr).max();
    int maxValue = tmp.isPresent() ? tmp.getAsInt() : -1;
    System.out.println("max = " + maxValue);

    int cnt = 0;
    for (int i = 0; i < N; i++)
      if (arr[i] == maxValue) cnt++;

    return cnt;
  }
}
