import java.util.ArrayList;

public class Prog42862 {
  public final int n;
  public final int[] lost;
  public final int[] reserve;
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
    for (int lostStudent : lost)
      if (reserveList.contains(lostStudent)) remove(lostStudent, lostStudent);

    int[] tmpLost = lostList.stream().mapToInt(i -> i).toArray();

    for (int lostStudent : tmpLost)
      if (isValidStudent(lostStudent - 1) && reserveList.contains(lostStudent - 1))
        remove(lostStudent - 1, lostStudent);
      else if (isValidStudent(lostStudent + 1) && reserveList.contains(lostStudent + 1))
        remove(lostStudent + 1, lostStudent);

    return n - lostList.size();
  }

  private boolean isValidStudent(int student) {
    return 1 <= student && student <= n;
  }

  private void remove(int reserveStudent, int lostStudent) {
    reserveList.remove(Integer.valueOf(reserveStudent));
    lostList.remove(Integer.valueOf(lostStudent));
  }
}