import java.util.HashSet;
import java.util.Set;

public class Prog42895 {
  public final int MAX_SPLIT = 8;
  public final int N, number;
  public Set<Integer>[] sets = new Set[MAX_SPLIT];

  public Prog42895(int N, int number) {
    this.N = N;
    this.number = number;
  }

  public int solve() {
    int answer = -1;

    for (int nTimes = 1; nTimes <= MAX_SPLIT; nTimes++) {
      int setIdx = nTimes - 1;
      sets[setIdx] = new HashSet<>();

      // N이 nTimes 개수만큼 있는 숫자 추가
      int repeated = Integer.parseInt(Integer.toString(N).repeat(nTimes));
      sets[setIdx].add(repeated);

      // 두개의 Set ( 합해서 N을 총 nTimes 만큼 쓰는 Set )사칙연산한 결과값 추가
      for (int i = 1; i < nTimes; i++)
        sets[setIdx].addAll(arithmeticAddSet(sets[i - 1], sets[nTimes - i - 1]));

      // number와 일치값 존재하면 break
      if (sets[setIdx].contains(number)) {
        answer = nTimes;
        break;
      }
    }

    return answer;
  }

  private Set<Integer> arithmeticAddSet(Set<Integer> set1, Set<Integer> set2) {
    Set<Integer> tmpSet = new HashSet<>();
    for (int value1 : set1)
      for (int value2 : set2) {
        tmpSet.add(value1 + value2);
        tmpSet.add(value1 - value2);
        tmpSet.add(value1 * value2);
        if (value2 != 0) tmpSet.add(value1 / value2);
      }

    return tmpSet;
  }
}
