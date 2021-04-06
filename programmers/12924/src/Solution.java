import java.util.Scanner;

public class Solution
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(new Prob12924(sc.nextInt()).solve());
		sc.close();
	}
}

class Prob12924
{
	int number;

	public Prob12924(int n)
	{
		this.number = n;
	}

	public int solve()
	{
		int ans = 0;

		for (int i = 1; i <= number; i++)
		{
			int startNum;
			if (i % 2 != 0) startNum = number / i - i / 2;
			else startNum = number / i - i / 2 + 1;
			if(startNum <= 0)	break;
			if(sum(startNum, startNum + i - 1) == number) ans++;
		}

		return ans;
	}

	private int sum(int start, int end)
	{
		int sumStart = start * (start - 1) / 2;
		int sumEnd = end * (end + 1) / 2;
		return sumEnd - sumStart;
	}
}