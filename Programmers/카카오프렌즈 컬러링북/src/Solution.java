public class Solution {
  public int[] solution(int m, int n, int[][] picture) {
    return new Prob1829(m, n, picture).solve();
  }
}

class Prob1829 {
  private final int m, n;
  private final int[][] picture;
  private boolean[][] isVisit;

  public Prob1829(int m, int n, int[][] picture) {
    this.m = m;
    this.n = n;
    this.picture = picture;
    isVisit = new boolean[m][n];
  }

  public int[] solve() {
    int numberOfArea = 0;
    int maxSizeOfOneArea = 0;

    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
        if (picture[i][j] != 0 && !isVisit[i][j]) {
          numberOfArea++;
          int count = countArea(i, j, picture[i][j]);
          if (count > maxSizeOfOneArea)  maxSizeOfOneArea = count;
        }
    
    return new int[]{numberOfArea, maxSizeOfOneArea};
  }

  private boolean isRange(int m, int n) {
    return 0 <= m && m < this.m && 0 <= n && n < this.n;
  }

  private int countArea(int m, int n, int num) {
    if (!isRange(m, n))  return 0;
    if (picture[m][n] != num || picture[m][n] == 0)  return 0;
    if (isVisit[m][n])  return 0;
    isVisit[m][n] = true;
    return 1 + countArea(m + 1, n, picture[m][n]) + countArea(m, n + 1, picture[m][n])
        + countArea(m - 1, n, picture[m][n]) + countArea(m, n - 1, picture[m][n]);
  }
}