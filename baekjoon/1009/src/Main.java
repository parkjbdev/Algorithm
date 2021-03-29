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
		IO.Input in = new IO.Input();
		IO.Output out = new IO.Output();

		int testCase = Integer.parseInt(in.readLine());
		for (int i = 0; i < testCase; i++)
		{
			int[] input = in.readLineIntArguments();
			out.write(new BOJ1009(input[0], input[1]).solve());
			out.flush();
		}
		in.close();
		out.close();
	}
}

class BOJ1009
{
	private static final int[][] table = {
			{10},
			{1},
			{2, 4, 8, 6},
			{3, 9, 7, 1},
			{4, 6},
			{5},
			{6},
			{7, 9, 3, 1},
			{8, 4, 2, 6},
			{9, 1}
	};
	private final int a, b;

	public BOJ1009(int a, int b)
	{
		this.a = a;
		this.b = b;
	}

	public int solve()
	{
		int len = table[a % 10].length;
		return table[a % 10][(b - 1) % len];
	}
}