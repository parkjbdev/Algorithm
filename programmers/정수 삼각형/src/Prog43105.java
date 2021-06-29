import java.util.Arrays;

public class Prog43105 {
  public final int[] triangle;
  private int[] sum;

  public Prog43105(int[][] triangle) {
    this.triangle = Arrays.stream(triangle).flatMapToInt(Arrays::stream).toArray();
    this.sum = new int[this.triangle.length];
  }

  public int solve() {
    return maxSum(0);
  }

  private int maxSum(int idx) {
    if (idx >= triangle.length) return 0;
    if (sum[idx] == 0) sum[idx] = triangle[idx] + Math.max(maxSum(leftIdx(idx)), maxSum(rightIdx(idx)));
    return sum[idx];
  }

  private int getFloor(int idx) {
    return (int) ((1 + Math.sqrt(1 + 8 * idx)) / 2);
  }

  private int leftIdx(int idx) {
    return getFloor(idx) + idx;
  }

  private int rightIdx(int idx) {
    return getFloor(idx) + idx + 1;
  }
}