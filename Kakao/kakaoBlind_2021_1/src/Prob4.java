public class Prob4
{
	private final boolean debug = false;
	private final int MAX_VALUE = 10000000;
	private final int nodeSize, startNode, destNode1, destNode2;
	private int[][] map;

	public Prob4(int nodeSize, int startNode, int destNode1, int destNode2, int[][] fares)
	{
		this.nodeSize = nodeSize;
		this.startNode = startNode - 1;
		this.destNode1 = destNode1 - 1;
		this.destNode2 = destNode2 - 1;
		this.map = initMap(fares);
	}

	private int[][] initMap(int[][] fares)
	{
		int[][] map = new int[nodeSize][nodeSize];
		for (int i = 0; i < nodeSize; i++)
			for (int j = 0; j < nodeSize; j++)
			{
				map[i][j] = MAX_VALUE;
				if (i == j) map[i][j] = 0;
			}

		for (int[] fare : fares)
		{
			map[fare[0] - 1][fare[1] - 1] = fare[2];
			map[fare[1] - 1][fare[0] - 1] = fare[2];
		}

		return map;
	}

	private void debug_showMap()
	{
		System.out.println("====================  MAP  ====================");

		for (int i = 0; i <= nodeSize; i++)
			System.out.printf("[%10d]", i);
		System.out.println();
		for (int i = 0; i < nodeSize; i++)
		{
			System.out.printf("[%10d]", i + 1);
			for (int j = 0; j < nodeSize; j++)
			{
				if (map[i][j] != MAX_VALUE) System.out.printf("%12d", map[i][j]);
				else System.out.printf("%12s", "∞");
			}

			System.out.println();
		}
	}

	private int getSmallestIndex(int[] distance, boolean[] isVisit)
	{
		int index = -1;
		int min = MAX_VALUE;
		for (int i = 0; i < distance.length; i++)
		{
			if (!isVisit[i] && distance[i] < min)
			{
				min = distance[i];
				index = i;
			}
		}

		return index;
	}

	private int[] dijkstra(int startNode)
	{
		int[] distance = new int[nodeSize];
		System.arraycopy(map[startNode], 0, distance, 0, nodeSize);

		boolean[] isVisit = new boolean[nodeSize];
		isVisit[startNode] = true;

		int currentNode;
		while((currentNode = getSmallestIndex(distance, isVisit)) >= 0)
		{
			isVisit[currentNode] = true;

			for (int j = 0; j < nodeSize; j++)
				if (!isVisit[j]) distance[j] = Math.min(distance[j], distance[currentNode] + map[currentNode][j]);
		}

		return distance;
	}

	public int solve()
	{
		for (int i = 0; i < nodeSize; i++)
		{
			int[] dijkstra = dijkstra(i);
			System.arraycopy(dijkstra, 0, map[i], 0, nodeSize);
		}

		int costMin = MAX_VALUE;
		for(int i = 0;i < nodeSize;i++)
		{
			int cost = map[startNode][i] + map[i][destNode1] + map[i][destNode2];
			if(costMin > cost)	costMin = cost;
		}

		if (debug) debug_showMap();

		return costMin;
	}
}
