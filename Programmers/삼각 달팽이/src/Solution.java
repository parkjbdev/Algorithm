import java.util.Arrays;

public class Solution
{
	private int n;
	private int len;
	private int[] answer;

	public Solution(int n)
	{
		this.n = n;
		this.len = n * (n + 1) / 2;
		answer = new int[len];
	}

	private int xy(int x, int y)
	{
		return x * (x + 1) / 2 + y;
	}

	public int[] solve()
	{
		int x = 0, y = 0;
		int cnt = 1;

		while(cnt <= len)
		{
			// down
			
			// right

			// up


			answer[xy(x, y)] = cnt++;
		}


		return answer;
	}
}
