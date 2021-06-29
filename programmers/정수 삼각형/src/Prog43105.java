public class Prog43105 {
  public final int[][] triangle;
  private final int[][] sum;

  public Prog43105(int[][] triangle) {
    this.triangle = triangle;
    this.sum = new int[triangle.length][];
    for (int i = 0; i < triangle.length; i++) this.sum[i] = new int[triangle[i].length];
  }

  public int solve() {
    return maxSum(0, 0);
  }

  private int maxSum(int idx1, int idx2) {
    if (idx1 >= triangle.length || idx2 >= triangle[idx1].length) return 0;
    if (sum[idx1][idx2] == 0)
      sum[idx1][idx2] = triangle[idx1][idx2] + Math.max(maxSum(idx1 + 1, idx2), maxSum(idx1 + 1, idx2 + 1));
    return sum[idx1][idx2];
  }
}