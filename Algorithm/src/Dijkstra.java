import java.util.PriorityQueue;

public class Dijkstra {
  public static final int MAX_VALUE = 100000000;
  private final int n;
  private final int[][] distance;
  private final boolean[] isVisit;
  private final int[] minDistance;

  public Dijkstra(int n, int[][] distance) {
    this.n = n;
    this.distance = distance;
    this.isVisit = new boolean[n];
    this.minDistance = new int[n];
  }

  public int[] solveSimple(int startVertex) {
    SimpleMethod dijkstra = new SimpleMethod();
    return dijkstra.minDistance(startVertex);
  }

  public int[] solvePQ(int startVertex) {
    PriorityQueueMethod dijkstra = new PriorityQueueMethod();
    return dijkstra.minDistance(startVertex);
  }

  class SimpleMethod {
    public int[] minDistance(int startVertex) {
      for (int i = 0; i < n; i++) minDistance[i] = MAX_VALUE;
      minDistance[startVertex] = 0;

      for (int vertex = startVertex; vertex >= 0; vertex = getNextVertex()) {
        isVisit[vertex] = true;

        for (int i = 0; i < n; i++)
          if (!isVisit[i])
            minDistance[i] = Math.min(minDistance[i], minDistance[vertex] + distance[vertex][i]);
      }

      return minDistance;
    }

    private int getNextVertex() {
      int min = MAX_VALUE, idx = -1;
      for (int i = 0; i < n; i++) {
        if (isVisit[i]) continue;
        if (min > minDistance[i]) {
          min = minDistance[i];
          idx = i;
        }
      }
      return idx;
    }
  }

  class PriorityQueueMethod {
    private final PriorityQueue<Node> priorityQueue;

    private PriorityQueueMethod() {
      priorityQueue = new PriorityQueue<>();
    }

    public int[] minDistance(int startVertex) {
      for (int i = 0; i < n; i++) minDistance[i] = MAX_VALUE;
      minDistance[startVertex] = 0;
      priorityQueue.add(new Node(startVertex, 0));

      while (!priorityQueue.isEmpty()) {
        int vertex = priorityQueue.poll().vertex;
        isVisit[vertex] = true;
        for (int i = 0; i < n; i++)
          if (!isVisit[i] && minDistance[i] > minDistance[vertex] + distance[vertex][i]) {
            minDistance[i] = minDistance[vertex] + distance[vertex][i];
            priorityQueue.add(new Node(i, minDistance[i]));
          }
      }
      return minDistance;
    }

    class Node implements Comparable<Node> {
      public final int vertex, minDistance;

      public Node(int vertex, int minDistance) {
        this.vertex = vertex;
        this.minDistance = minDistance;
      }

      @Override
      public int compareTo(Node o) {
        return this.minDistance - o.minDistance;
      }
    }
  }
}