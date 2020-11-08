import java.util.Scanner;

class Pair
{
	public final int pair1, pair2;
	Pair(int pair1, int pair2)
	{
		this.pair1 = pair1;
		this.pair2 = pair2;
	}
}

class Picnic
{
	private int studentNum;
	private Pair[] pairs;
	private int Answer = 0;
	static boolean[] isPaired;

	public Picnic(Scanner sc)
	{
		input(sc);
		solve();
		output();
	}

	public void input(Scanner sc)
	{
		studentNum = sc.nextInt();
		pairs = new Pair[sc.nextInt()];
		isPaired = new boolean[studentNum];

		for(int i = 0;i < pairs.length;i++)
			pairs[i] = new Pair(sc.nextInt(), sc.nextInt());
	}

	public void output()
	{
		System.out.println(Answer);
	}

	public void solve()
	{
		if(studentNum % 2 != 0)	return;
		recursive(isPaired, 0);
	}

	public void recursive(boolean[] isPaired, int pairIdx)
	{
		// 모두 각자 짝이 있을 경우
		if(countTrue(isPaired) == studentNum)
		{
			Answer++;
			return;
		}
		// 모든 경우를 순회했을 경우
		if(pairIdx == pairs.length)	return;

		// 둘 다 짝이 없는 경우
		// isPaired true로 할당 후 다음 pairIdx로 넘어감, return 후에는 원래대로 false로 할당
		if(!isPaired[pairs[pairIdx].pair1] && !isPaired[pairs[pairIdx].pair2])
		{
			isPaired[pairs[pairIdx].pair1] = true;
			isPaired[pairs[pairIdx].pair2] = true;

			recursive(isPaired, pairIdx + 1);

			isPaired[pairs[pairIdx].pair1] = false;
			isPaired[pairs[pairIdx].pair2] = false;
		}

		// 어느 하나라도 짝이 있는 경우 - 다음 pairIdx로 넘어감
		else recursive(isPaired, pairIdx + 1);
	}

	public int countTrue(boolean[] array)
	{
		int cnt = 0;
		for (boolean b : array) if (b) cnt++;
		return cnt;
	}
}

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int testCaseCnt = sc.nextInt();
		for(int testCase = 0;testCase < testCaseCnt;testCase++)	new Picnic(sc);
		sc.close();
	}
}