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
		IO.Output output = new IO.Output();
		int testCase = input.readLineIntArgument();
		for (int i = 0; i < testCase; i++)
		{
			int[] inputs = input.readLineIntArguments();
			output.write(combination(inputs[1], inputs[0]));
		}
		input.close();
		output.close();
	}
	public static int combination(int n, int r)
	{
		if(r > (n - r))	r = n - r;

		long mul1 = 1, mul2 = 1;
		for (int i = 0; i < r; i++)
		{
			mul1 *= (n - i);
			mul2 *= (i + 1);
		}

		return (int)(mul1 / mul2);
	}
}
