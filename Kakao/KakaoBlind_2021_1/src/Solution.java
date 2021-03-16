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
		String[] play_time = {"02:03:55", "99:59:59", "50:00:00"};
		String[] adv_time = {"00:14:15", "25:00:00", "50:00:00"};
		String[][] logs = {
				{"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"},
				{"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"},
				{"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"}
		};

		long beforeTime, afterTime;

		for(int i = 0;i < 3;i++)
		{
			beforeTime = System.currentTimeMillis();
			System.out.println(new Prob5_Sliding(play_time[i], adv_time[i], logs[i]).solve());
			afterTime = System.currentTimeMillis();
			System.out.println((afterTime - beforeTime) + " ms");
		}
	}
}
