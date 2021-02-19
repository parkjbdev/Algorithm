import java.util.ArrayList;

import java.util.*;

public class Prob3Test
{
	static ArrayList<Integer>[][][][] list = new ArrayList[4][3][3][3];

	public int[] solution(String[] info, String[] query)
	{
		int cnt = 0;
		int[] answer = new int[query.length];

		for (String f : info)
		{
			String[] applicant = f.split(" ");
			insert(applicant);
		}

		for (String q : query)
		{
			String[] qry = q.replaceAll(" and ", " ").split(" ");
			int[] map = mapping(qry);

			List li = list[map[0]][map[1]][map[2]][map[3]];
			if (li == null) answer[cnt++] = 0;
			else
			{
				int idx = bs(li, map[4]);
				answer[cnt++] = li.size() - idx;
			}
		}

		return answer;
	}

	public static void insert(String[] applicant)
	{
		int[] map = mapping(applicant);
		int[] loc = {0, 0, 0, 0};
		for (int i = 0; i < 16; i++)	// Combination via AND operator
		{
			for (int j = 0; j < 4; j++)
			{
				int m = 1 << j;
				if ((m & i) > 0) loc[3 - j] = map[3 - j];
				else loc[3 - j] = 0;
			}

			if (list[loc[0]][loc[1]][loc[2]][loc[3]] == null)
			{
				list[loc[0]][loc[1]][loc[2]][loc[3]] = new ArrayList<Integer>();
			}

			// Inserting Score Sorted
			List li = list[loc[0]][loc[1]][loc[2]][loc[3]];
			int idx = bs(li, map[4]);
			if (idx == li.size()) li.add(map[4]);
			else li.add(idx, map[4]);
		}
	}

	public static int bs(List<Integer> li, int target)
	{
		int s = 0;
		int e = li.size();
		int mid;
		while (s < e)
		{
			mid = (s + e) / 2;
			if (li.get(mid) >= target) e = mid;
			else s = mid + 1;
		}
		return e;
	}

	public static int[] mapping(String[] applicant)
	{
		int[] arr = {0, 0, 0, 0, 0};

		char c = applicant[0].charAt(0);
		if (c == 'c') arr[0] = 1;
		else if (c == 'j') arr[0] = 2;
		else if (c == 'p')	arr[0] = 3;

		c = applicant[1].charAt(0);
		if (c == 'b')	arr[1] = 1;
		else if (c == 'f')	arr[1] = 2;

		c = applicant[2].charAt(0);
		if (c == 'j') arr[2] = 1;
		else if (c == 's') arr[2] = 2;

		c = applicant[3].charAt(0);
		if (c == 'c') arr[3] = 1;
		else if (c == 'p') arr[3] = 2;

		arr[4] = Integer.parseInt(applicant[4]);
		return arr;
	}
}