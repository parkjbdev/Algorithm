public class Prob4
{
	private final int nodeSize, startNode, destNode1, destNode2;
	private int[][] map;

	public Prob4(int nodeSize, int startNode, int destNode1, int destNode2, int[][] fares)
	{
		this.nodeSize = nodeSize;
		this.startNode = startNode;
		this.destNode1 = destNode1;
		this.destNode2 = destNode2;
		this.map = initMap(fares);
	}

	private int[][] initMap(int[][] fares)
	{
		int[][] map = new int[nodeSize][nodeSize];
		for (int i = 0; i < nodeSize; i++)
			for (int j = 0; j < nodeSize; j++)
			{
				map[i][j] = Integer.MAX_VALUE;
				if (i == j) map[i][j] = 0;
			}

		for (int[] fare : fares)
		{
			map[fare[0] - 1][fare[1] - 1] = fare[2];
			map[fare[1] - 1][fare[0] - 1] = fare[2];
		}

		if (Solution.debug)
			for (int i = 0; i < nodeSize; i++)
			{
				for (int j = 0; j < nodeSize; j++)
					System.out.printf("%12d", map[i][j]);
				System.out.println();
			}

		return map;
	}

	public int solve()
	{

		return 0;
	}
}
