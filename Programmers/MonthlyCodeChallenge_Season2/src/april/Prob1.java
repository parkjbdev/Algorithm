package april;

class Prob1
{
	private final int[] absolutes;
	private final boolean[] signs;
	public Prob1(int[] absolutes, boolean[] signs)
	{
		this.absolutes = absolutes;
		this.signs = signs;
	}
	public int solve()
	{
		int sum = 0;
		for (int i = 0; i < absolutes.length; i++)
		{
			if (signs[i]) sum += absolutes[i];
			else sum -= absolutes[i];
		}
		return sum;
	}
}
