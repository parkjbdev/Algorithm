import java.util.*;

public class Prob3
{
	Map<Integer, ArrayList<Integer>> infoMap = new HashMap<>();
	int[][] queries;

	public Prob3(String[] infos, String[] queries)
	{
		saveInfo(infos);
		saveQuery(queries);
	}

	private void saveInfo(String[] infos)
	{
		for (String info : infos)
		{
			String[] infoSplit = info.split(" ");

			int hash = Hash.createHash(infoSplit);
			int score = Integer.parseInt(infoSplit[4]);

			if (!this.infoMap.containsKey(hash))
			{
				this.infoMap.put(hash, new ArrayList<>()
				{{
					add(score);
				}});
			}
			else
			{
				ArrayList<Integer> scoreArr = this.infoMap.get(hash);
				scoreArr.add(score);
				Collections.sort(scoreArr);
			}
		}
	}

	private void saveQuery(String[] queries)
	{
		int cnt = 0;
		this.queries = new int[queries.length][2];

		for (String query : queries)
		{
			String[] splitQuery = query.split("( and )| ");
			this.queries[cnt][0] = Hash.createZeroHash(splitQuery);
			this.queries[cnt++][1] = Integer.parseInt(splitQuery[4]);
		}
	}

	public int[] solve()
	{
		int[] answer = new int[queries.length];
		for (int i = 0; i < queries.length; i++)
		{
			int hash = queries[i][0];
			int score = queries[i][1];

			answer[i] += counter(hash, score);
		}

		return answer;
	}

	private int counter(int zeroHash, int targetScore)
	{
		int cnt = 0;
		Integer[] hashCombination = Hash.combineZeroHash(zeroHash);

		for (int hash : hashCombination)
		{
			List<Integer> scoreArr = infoMap.get(hash);
			if (scoreArr == null) continue;
			int lowerCount = Collections.binarySearch(scoreArr, targetScore);
			if (lowerCount < 0) lowerCount = -lowerCount - 1;
			else if (scoreArr.get(lowerCount) == targetScore)
				while (lowerCount > 0 && scoreArr.get(lowerCount - 1) == targetScore)
					lowerCount--;

			cnt += scoreArr.size() - lowerCount;
		}

		return cnt;
	}

	private static class Hash
	{
		public static int createHash(String[] split)
		{
			return Language.valueOf(split[0]).ordinal() * 1000 +
					Apply.valueOf(split[1]).ordinal() * 100 +
					Career.valueOf(split[2]).ordinal() * 10 +
					SoulFood.valueOf(split[3]).ordinal();
		}

		public static int createZeroHash(String[] split)
		{
			for (int i = 0; i < split.length; i++)
				if (split[i].equals("-")) split[i] = "none";

			return createHash(split);
		}

		public static Integer[] combineZeroHash(int hash)
		{
			ArrayList<Integer> list = new ArrayList<>();
			recursion(hash, list);

			return list.toArray(new Integer[0]);
		}

		private static void recursion(int hash, ArrayList<Integer> save)
		{
			String hashString = String.format("%04d", hash);

			int language = Character.getNumericValue(hashString.charAt(0));
			int apply = Character.getNumericValue(hashString.charAt(1));
			int career = Character.getNumericValue(hashString.charAt(2));
			int soulFood = Character.getNumericValue(hashString.charAt(3));

			if (language != 0 && apply != 0 && career != 0 && soulFood != 0)
			{
				save.add(hash);
				return;
			}

			if (language == 0)
				for (int i = 1; i < 4; i++) recursion(hash + i * 1000, save);
			if (apply == 0 && language != 0)
				for (int i = 1; i < 3; i++) recursion(hash + i * 100, save);
			if (career == 0 && language != 0 && apply != 0)
				for (int i = 1; i < 3; i++) recursion(hash + i * 10, save);
			if (soulFood == 0 && language != 0 && apply != 0 && career != 0)
				for (int i = 1; i < 3; i++) recursion(hash + i, save);
		}
	}
}


enum Language
{none, cpp, java, python}

enum Apply
{none, backend, frontend}

enum Career
{none, junior, senior}

enum SoulFood
{none, chicken, pizza}