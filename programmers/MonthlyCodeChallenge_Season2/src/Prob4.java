class Prob4 {
	private final int[][] adjacentMatrix;
	private final long[] queries;
	public Prob4(int n, int z, int[][] roads, long[] queries)
	{
		adjacentMatrix = new int[n][n];
		for (int i = 0; i < n; i++) adjacentMatrix[i][i] = z;
		for (int[] road : roads) adjacentMatrix[road[0]][road[1]] = road[2];
		this.queries = queries;
	}

	public long[] solve()
	{
		long[] answer = new long[queries.length];

		for (int i = 0;i < queries.length;i++)
		{
			long targetCost = queries[i];
			answer[i] = query(targetCost);
		}

		return answer;
	}

	private long query(final long targetCost)
	{
		long cost = 0;
		long cnt = 0;

		return cnt;
	}

}