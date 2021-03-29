import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

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

		int N = input.readLineIntArgument();
		int[] arr1 = input.readLineIntArguments();
		int[] arr2 = input.readLineIntArguments();
		input.close();

		Arrays.sort(arr1);
		Arrays.sort(arr2);

		int sum = 0;
		for (int i = 0; i < N; i++) sum += arr1[i] * arr2[N - i - 1];
		output.write(sum);
		output.close();
	}
}
