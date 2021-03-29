import java.util.Scanner;

public class QualificationRound2021_Prob1
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();
		for (int i = 0; i < testCase; i++)
		{
			int N = sc.nextInt();
			int[] arr = new int[N];
			for (int j = 0; j < N; j++) arr[j] = sc.nextInt();
			int answer = new Prob1(arr).solve();
			System.out.println("Case #" + (i + 1) + ": " + answer);
		}
	}
}

class Prob1
{
	int[] arr;

	public Prob1(int[] L)
	{
		arr = L;
	}

	public int solve()
	{
		return reverseSort(arr);
	}

	public int reverseSort(int[] arr)
	{
		int cnt = 0;

		for (int i = 0; i < arr.length; i++)
		{
			int minPos = minPosition(i, arr.length);
			if (i != arr.length - 1)
				cnt += reverse(i, minPos);
		}
		return cnt;
	}

	private int minPosition(int start, int end)
	{
		int minIdx = start;
		for (int i = start + 1; i < end; i++)
			if (arr[minIdx] > arr[i]) minIdx = i;

		return minIdx;
	}

	private int reverse(int idx1, int idx2)
	{
		int len = idx1 + (idx2 - idx1) / 2;

		for (int i = idx1; i < len; i++)
		{
			int tmp = arr[i];
			arr[i] = arr[idx1 + idx2 - i];
			arr[idx1 + idx2 - i] = tmp;
		}
		return idx2 - idx1 + 1;
	}
}