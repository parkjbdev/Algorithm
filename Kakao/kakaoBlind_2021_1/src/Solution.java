import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
		String[] info = Test.Prob2.createInfo(50000);
//		String[] query = Test.Prob2.createQuery(100000);
		String[] query = Test.Prob2.createWhiteQuery(100000);

		long beforeTime, afterTime;

		beforeTime = System.currentTimeMillis();
		new Prob3(info, query).solve();
		afterTime = System.currentTimeMillis();
		System.out.println((afterTime - beforeTime) + " ms");

		beforeTime = System.currentTimeMillis();
		new Prob3Test().solution(info, query);
		afterTime = System.currentTimeMillis();
		System.out.println((afterTime - beforeTime) + " ms");

//		System.out.println(Arrays.toString(new Prob3(info, query).solve()));
	}
}
