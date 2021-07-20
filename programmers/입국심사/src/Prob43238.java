public class Prob43238 {
  public final int n;
  public final int[] times;
  public final long MAX_VALUE;

  public Prob43238(int n, int[] times) {
    this.n = n;
    this.times = times;
    long tmpMax = times[0];
    for (int time : times) if (time > tmpMax) tmpMax = time;
    this.MAX_VALUE = tmpMax * n;
  }

  public long solve() {
    long start = 0, end = MAX_VALUE;
    long mid = (start + end) / 2;

    while (end - start != 1) {
      long sum = sum(mid);
      if (sum >= n) end = mid;
      else if (sum < n) start = mid;
      mid = (start + end + 1) / 2;
    }

    return mid;
  }

  private long sum(long minTime) {
    long sum = 0L;
    for (long time : times) sum += minTime / time;
    return sum;
  }
}