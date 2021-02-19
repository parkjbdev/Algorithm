import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Test
{
	public static class Prob2
	{
		static String[] language = {"-", "cpp", "java", "python"};
		static String[] apply = {"-", "backend", "frontend"};
		static String[] career = {"-", "junior", "senior"};
		static String[] soulFood = {"-", "chicken", "pizza"};

		public static String[] createInfo(int max)
		{
			String[] info = new String[max];
			for (int i = 0; i < max; i++)
			{
				StringBuilder strBuilder = new StringBuilder();

				strBuilder.append(language[(int) (Math.random() * 10) % (language.length - 1) + 1]);
				strBuilder.append(" ");
				strBuilder.append(apply[(int) (Math.random() * 10) % (apply.length - 1) + 1]);
				strBuilder.append(" ");
				strBuilder.append(career[(int) (Math.random() * 10) % (career.length - 1) + 1]);
				strBuilder.append(" ");
				strBuilder.append(soulFood[(int) (Math.random() * 10) % (soulFood.length - 1) + 1]);
				strBuilder.append(" ");
				strBuilder.append((int) (Math.random() * 100000));
				info[i] = strBuilder.toString();
			}

			return info;
		}

		public static String[] createQuery(int max)
		{
			String[] query = new String[max];
			for (int i = 0; i < max; i++)
			{
				StringBuilder strBuilder = new StringBuilder();

				if ((int) (Math.random() * 10) % 2 == 0)
					strBuilder.append(language[(int) (Math.random() * 10) % (language.length)]);
				else strBuilder.append("-");
				strBuilder.append(" and ");

				if ((int) (Math.random() * 10) % 2 == 0)
					strBuilder.append(apply[(int) (Math.random() * 10) % (apply.length)]);
				else strBuilder.append("-");
				strBuilder.append(" and ");

				if ((int) (Math.random() * 10) % 2 == 0)
					strBuilder.append(career[(int) (Math.random() * 10) % (career.length)]);
				else strBuilder.append("-");
				strBuilder.append(" and ");

				if ((int) (Math.random() * 10) % 2 == 0)
					strBuilder.append(soulFood[(int) (Math.random() * 10) % (soulFood.length)]);
				else strBuilder.append("-");
				strBuilder.append(" ");

				strBuilder.append((int) (Math.random() * 100000));
				query[i] = strBuilder.toString();
			}

			return query;
		}

		public static String[] createWhiteQuery(int max)
		{
			String[] query = new String[max];
			for (int i = 0; i < max; i++)
			{
				StringBuilder strBuilder = new StringBuilder();

				strBuilder.append("-");
				strBuilder.append(" and ");

				strBuilder.append("-");
				strBuilder.append(" and ");

				strBuilder.append("-");
				strBuilder.append(" and ");

				strBuilder.append("-");
				strBuilder.append(" ");

				strBuilder.append((int) (Math.random() * 100000));
				query[i] = strBuilder.toString();
			}

			return query;
		}
	}
}

public class Solution
{
	public static final boolean debug = true;
	public static String input()
	{
		String str = "";
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		try
		{
			str = bf.readLine();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return str;
	}

	public static void main(String[] args)
	{
		int[] n = {6, 7, 8};
		int[] s = {4, 3, 4};
		int[] a = {6, 4, 5};
		int[] b = {2, 1, 6};
		int[][][] fares = {
				{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}},
				{{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}},
				{{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}}
		};

		long beforeTime, afterTime;

		for(int i = 0;i < 3;i++)
		{
			beforeTime = System.currentTimeMillis();
			System.out.println(new Prob4(n[i], s[i], a[i], b[i], fares[i]).solve());
			afterTime = System.currentTimeMillis();
			System.out.println((afterTime - beforeTime) + " ms");
		}
	}
}
