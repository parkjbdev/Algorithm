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

		public static String[] createProb2Info(int max)
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

		public static String[] createProb2Query(int max)
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
		String[] info = Test.Prob2.createProb2Info(50000);
		String[] query = Test.Prob2.createProb2Query(100000);
//		String[] info = {"java backend junior pizza 150", "java backend junior pizza 150","java backend junior pizza 100","java backend junior pizza 150","java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
//		String[] query= {"java and backend and junior and pizza 125","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		long beforeTime = System.currentTimeMillis();
		int[] result = new Prob3(info, query).solve();
		long afterTime = System.currentTimeMillis();
		System.out.println(Arrays.toString(result));
		System.out.println((afterTime - beforeTime) + " ms");
//		System.out.println(Arrays.toString(new Prob3(info, query).solve()));
	}
}
