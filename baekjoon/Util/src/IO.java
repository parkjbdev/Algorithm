import java.io.*;
import java.util.Arrays;

public class IO
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