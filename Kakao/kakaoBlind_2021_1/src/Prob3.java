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

			addNewInfo(hash, score);
		}
	}

	private void addNewInfo(int newHash, int score)
	{
		// TODO: hash combination
		int[] hashCombination = hashCombiner(newHash);

		for(int hash: hashCombination)
		{
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
				int idx = Collections.binarySearch(scoreArr, score);
				if (idx < 0) idx = -idx - 1;
				scoreArr.add(idx, score);
			}
		}
	}

	private int[] hashCombiner(int hash)
	{
		int[] hashes = new int[16];
		String hashStr = String.format("%04d", hash);
		for(int i = 0;i < 16;i++)
		{
			String bin = String.format("%04d" , Integer.parseInt(Integer.toBinaryString(i)));
			StringBuilder newString = new StringBuilder();
			for(int j = 0;j < 4;j++)
			{
				if(j < bin.length() && bin.charAt(j) != '0')	newString.append(hashStr.charAt(j));
				else newString.append('0');
			}
			hashes[i] = Integer.parseInt(newString.toString());
		}
		return hashes;
	}

	private void saveQuery(String[] queries)
	{
		int cnt = 0;
		this.queries = new int[queries.length][2];

		for (String query : queries)
		{
			String[] splitQuery = query.split("( and )| ");
			this.queries[cnt][0] = Hash.createZeroHash(splitQuery);    // Hash
			this.queries[cnt++][1] = Integer.parseInt(splitQuery[4]);    // Score
		}
	}

	public int[] solve()
	{
		int[] queryResult = new int[queries.length];
		for (int i = 0; i < queries.length; i++)
			queryResult[i] = counter(queries[i][0], queries[i][1]);

		return queryResult;
	}

	private int counter(int hash, int targetScore)
	{
		int cnt = 0;
		List<Integer> scoreArr = infoMap.get(hash);
		if (scoreArr == null) return cnt;

		int lowerCount = lowerBound(scoreArr, targetScore);
		cnt = scoreArr.size() - lowerCount;

		return cnt;
	}

	private int lowerBound(List<Integer> list, int key)
	{
		int low = 0;
		int high = list.size();
		while (low < high)
		{
			int mid = low + (high - low) / 2;
			if (key <= list.get(mid)) high = mid;
			else low = mid + 1;
		}
		return low;
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