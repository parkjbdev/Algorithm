import java.util.*;

public class Prob3
{
	ArrayList<Integer>[][][][] info = new ArrayList[4][3][3][3];
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
			addNewInfo(infoSplit);
		}
	}

	private void addNewInfo(String[] splitedString)
	{
		int[] location = new int[4];
		int[] map = {Language.valueOf(splitedString[0]).ordinal(), Apply.valueOf(splitedString[1]).ordinal(), Career.valueOf(splitedString[2]).ordinal(), SoulFood.valueOf(splitedString[3]).ordinal()};

		for (int combNum = 0; combNum < 16; combNum++)
		{
			if ((combNum & 1) != 0) location[0] = map[0];
			else location[0] = 0;

			if ((combNum & 2) != 0) location[1] = map[1];
			else location[1] = 0;

			if ((combNum & 4) != 0) location[2] = map[2];
			else location[2] = 0;

			if ((combNum & 8) != 0) location[3] = map[3];
			else location[3] = 0;

			if (info[location[0]][location[1]][location[2]][location[3]] == null)
				info[location[0]][location[1]][location[2]][location[3]] = new ArrayList<>();

			int score = Integer.parseInt(splitedString[4]);
			int idx = lowerBound(info[location[0]][location[1]][location[2]][location[3]], score);
			info[location[0]][location[1]][location[2]][location[3]].add(idx, score);
		}
	}

	private void saveQuery(String[] queries)
	{
		this.queries = new int[queries.length][5];

		for (int i = 0; i < queries.length; i++)
		{
			String[] splitQuery = queries[i].replaceAll("-", "none").split("( and )| ");

			this.queries[i][0] = Language.valueOf(splitQuery[0]).ordinal();
			this.queries[i][1] = Apply.valueOf(splitQuery[1]).ordinal();
			this.queries[i][2] = Career.valueOf(splitQuery[2]).ordinal();
			this.queries[i][3] = SoulFood.valueOf(splitQuery[3]).ordinal();
			this.queries[i][4] = Integer.parseInt(splitQuery[4]);
		}
	}

	public int[] solve()
	{
		int[] queryResult = new int[queries.length];
		for (int i = 0; i < queries.length; i++)
			queryResult[i] = counter(queries[i], queries[i][4]);

		return queryResult;
	}

	private int counter(int[] location, int targetScore)
	{
		int cnt = 0;
		List<Integer> scoreArr = info[location[0]][location[1]][location[2]][location[3]];
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
}

enum Language {none, cpp, java, python}
enum Apply {none, backend, frontend}
enum Career {none, junior, senior}
enum SoulFood {none, chicken, pizza}