import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
  public static final int MAX_VALUE = 100000000;
  static int[][] map;
  static int[] minDistance;

  public static void main(String[] args) throws IOException {
    IO.Input reader = new IO.Input();

    int[] input = reader.readLineIntArguments();
    int V = input[0];
    int E = input[1];

    int startVertex = reader.readLineIntArgument() - 1;

    map = new int[V][V];
    for (int i = 0; i < map.length; i++)
      for (int j = 0; j < map.length; j++)
        map[i][j] = MAX_VALUE;

    for (int i = 0; i < E; i++) {
      input = reader.readLineIntArguments();
      int x = input[0] - 1;
      int y = input[1] - 1;
      int w = input[2];
      if (map[x][y] > w) map[x][y] = w;
    }

    minDistance = new int[V];
    dijkstra(startVertex);
    for (int i = 0; i < V; i++) {
      if (minDistance[i] != MAX_VALUE) System.out.println(minDistance[i]);
      else System.out.println("INF");
    }
  }

  public static void dijkstra(int startVertex) {
    PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
    final boolean[] isVisit = new boolean[map.length];

    for (int i = 0; i < map.length; i++) minDistance[i] = MAX_VALUE;
    minDistance[startVertex] = 0;
    priorityQueue.offer(new Node(startVertex, 0));

    while (!priorityQueue.isEmpty()) {
      int vertex = priorityQueue.poll().vertex;
      isVisit[vertex] = true;
      for (int i = 0; i < map.length; i++)
        if (!isVisit[i] && minDistance[i] > minDistance[vertex] + map[vertex][i]) {
          minDistance[i] = minDistance[vertex] + map[vertex][i];
          priorityQueue.offer(new Node(i, minDistance[i]));
        }
    }
  }

  static class IO {
    public static class Input extends BufferedReader {
      public Input() {
        super(new InputStreamReader(System.in));
      }

      public String[] readLineArguments() throws IOException {
        return super.readLine().split(" ");
      }

      public int readLineIntArgument() throws IOException {
        return readLineIntArguments()[0];
      }

      public int[] readLineIntArguments() throws IOException {
        return Arrays.stream(readLineArguments()).mapToInt(Integer::parseInt).toArray();
      }

      public void close() throws IOException {
        super.close();
      }
    }

    public static class Output extends BufferedWriter {
      public Output() {
        super(new OutputStreamWriter(System.out));
      }

      public void write(Object obj) throws IOException {
        super.write(obj.toString());
        super.write('\n');
      }

      @Override
      public void write(int c) throws IOException {
        write(String.valueOf(c));
      }

      @Override
      public void write(String str) throws IOException {
        super.write(str);
        super.write('\n');
      }
    }
  }

  static class Node implements Comparable<Node> {
    public final int vertex, minDistance;

    public Node(int vertex, int minDistance) {
      this.vertex = vertex;
      this.minDistance = minDistance;
    }

    @Override
    public int compareTo(Node o) {
      return minDistance - o.minDistance;
    }
  }
}
