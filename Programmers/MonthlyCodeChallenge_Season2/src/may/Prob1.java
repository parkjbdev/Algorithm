package may;

public class Prob1
{
	private final int start;
	private final int end;
	public Prob1(int left, int right)
	{
		this.start = left;
		this.end = right;
	}

	public int solve()
	{
		int sum = 0;
		for (int num = start; num <= end; num++)
		{
			if(isSquare(num)) sum -= num;
			else sum += num;
		}

		return sum;
	}

	private boolean isSquare(int num)
	{
		double sqrt = Math.sqrt(num);
		return sqrt == Math.floor(sqrt);
	}
}
