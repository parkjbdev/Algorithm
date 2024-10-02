import java.util.*;

public class Solution {
  public int solution(int n, int[][] edge) {
    return new Prob49189_BFS(n, edge).solve();
  }
}

class Prob49189_Dijkstra {
  public final int N;
  public final boolean[][] isConnect;
  private final int MAX_VALUE = 1000000;

  public Prob49189_Dijkstra(int n, int[][] edge) {
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

    return (int) Arrays.stream(arr).filter(n -> n == maxValue).count();
  }
}

class Prob49189_BFS {
  public final int N;
  public final ArrayList<Integer>[] adjacentList;
  private final Queue<Integer> queue = new LinkedList<>();
  boolean[] isVisit;
  private int[] minDistance;

  public Prob49189_BFS(int n, int[][] edge) {
    this.N = n;
    adjacentList = new ArrayList[n];
    isVisit = new boolean[N];
    minDistance = new int[N];

    for (int i = 0; i < n; i++) adjacentList[i] = new ArrayList<>();

    for (int[] node : edge) {
      adjacentList[node[0] - 1].add(node[1] - 1);
      adjacentList[node[1] - 1].add(node[0] - 1);
    }
  }

  public int solve() {
    bfs(0);
    OptionalInt tmpMax = Arrays.stream(minDistance).max();
    int max = tmpMax.isPresent() ? tmpMax.getAsInt() : 0;
    return (int) Arrays.stream(minDistance).filter(a -> a == max).count();
  }

  private void bfs(int start) {
    int cntVisit = 0;
    Queue<Integer> levelQueue = new LinkedList<>();

    visit(start);
    levelQueue.offer(0);

    while (!queue.isEmpty()) {
      if (cntVisit >= N) break;

      int currentIdx = queue.poll();
      int level = levelQueue.poll();
      minDistance[currentIdx] = level;

      for (int nextIdx : adjacentList[currentIdx])
        if (!isVisit[nextIdx]) {
          visit(nextIdx);
          levelQueue.offer(level + 1);
          cntVisit++;
        }
    }
  }

  private void visit(int idx) {
    queue.offer(idx);
    isVisit[idx] = true;
  }
}