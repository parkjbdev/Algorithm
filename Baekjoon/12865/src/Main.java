import java.io.*;
import java.util.Arrays;

public class Main
{
	static class IO
	{
		public static class Input extends BufferedReader
		{
			public Input()
			{
				super(new InputStreamReader(System.in));
			}

			public String[] readLineArguments() throws IOException
			{
				return super.readLine().split(" ");
			}

			public int readLineIntArgument() throws IOException
			{
				return readLineIntArguments()[0];
			}

			public int[] readLineIntArguments() throws IOException
			{
				return Arrays.stream(readLineArguments()).mapToInt(Integer::parseInt).toArray();
			}
		}

		public static class Output extends BufferedWriter
		{
			public Output()
			{
				super(new OutputStreamWriter(System.out));
			}

			public void write(Object obj) throws IOException
			{
				super.write(obj.toString());
				super.write('\n');
			}

			@Override
			public void write(int c) throws IOException
			{
				write(String.valueOf(c));
			}

			@Override
			public void write(String str) throws IOException
			{
				super.write(str);
				super.write('\n');
			}
		}
	}

	public static void main(String[] args) throws IOException
	{
		IO.Input input = new IO.Input();

		int[] arg = input.readLineIntArguments();

		int N = arg[0];
		int K = arg[1];
		int[] argW = new int[N];
		int[] argV = new int[N];

		for (int i = 0; i < N; i++)
		{
			arg = input.readLineIntArguments();
			argW[i] = arg[0];
			argV[i] = arg[1];
		}
		input.close();

		IO.Output output = new IO.Output();
		output.write(new BOJ_12865(N, K, argW, argV).solve());
		output.close();
	}
}

class BOJ_12865
{
	final int N, K;
	final int[] weight, value;

	int[][] dp;

	public BOJ_12865(int N, int K, int[] weight, int[] value)
	{
		this.N = N;
		this.K = K;
		this.weight = weight;
		this.value = value;
		this.dp = new int[N + 1][K + 1];
	}

	public int solve()
	{
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= K; j++) bestBag(i, j);

		return dp[N][K];
	}

	public void bestBag(int n, int capacity)
	{
		if (weight[n - 1] <= capacity)
			dp[n][capacity] = Math.max(value[n - 1] + dp[n - 1][capacity - weight[n - 1]], dp[n - 1][capacity]);
		else
			dp[n][capacity] = dp[n - 1][capacity];
	}
}