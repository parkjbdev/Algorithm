import java.util.ArrayList;

public class Prog42862 {
  public final int n;
  public final int[] lost;
  public final int[] reserve;
  private int answer;
  private final ArrayList<Integer> lostList = new ArrayList<>();
  private final ArrayList<Integer> reserveList = new ArrayList<>();

  public Prog42862(int n, int[] lost, int[] reserve) {
    this.n = n;
    this.lost = lost;
    this.reserve = reserve;
    for (int i : lost) lostList.add(i);
    for (int i : reserve) reserveList.add(i);
  }

  public int solve() {
    answer = n - lost.length;

    for (int lostStudent : lost)
      if (reserveList.contains(lostStudent)) answer(lostStudent);

    for (int lostStudent : lostList)
      if (reserveList.contains(lostStudent - 1)) answer(lostStudent - 1);
      else if (reserveList.contains(lostStudent + 1)) answer(lostStudent + 1);

    return answer;
  }

  private void answer(int removeStudent) {
    answer++;
    reserveList.remove(Integer.valueOf(removeStudent));
    lostList.remove(Integer.valueOf(removeStudent));
  }
}