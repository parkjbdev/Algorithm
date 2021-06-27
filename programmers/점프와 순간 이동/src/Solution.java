import java.util.*;

public class Solution
{
	public static void main(String[] args)
	{
		System.out.println(new Prob_12980(10000000).solve());
	}
}

class Prob_12980
{
	public final int N;
	public int cost = 0;

	public Prob_12980(int n)
	{
		this.N = n;
	}

	public int solve()
	{
		for (int i = N; i >= 1; i /= 2)
			if (isOdd(i)) cost++;

		return cost;
	}
	public boolean isOdd(int num)
	{
		return num % 2 != 0;
	}
}