import java.util.ArrayList;

class Prob3
{
	class Node
	{
		private int weight;
		private final ArrayList<Integer> adjacentList = new ArrayList<>();

		public Node(int weight)
		{
			this.weight = weight;
		}

		public int getWeight()
		{
			return weight;
		}

		public int increaseWeight(int increment)
		{
			weight += increment;
			return weight;
		}

		public int setZeroWeight()
		{
			int tmp = weight;
			weight = 0;
			return tmp;
		}

		public ArrayList<Integer> getAdjacentList()
		{
			return adjacentList;
		}

		public int getAdjacentCount()
		{
			return adjacentList.size();
		}

		public void addEdge(int nodeIdx)
		{
			adjacentList.add(nodeIdx);
		}

		public void deleteEdge(int nodeIdx)
		{
			adjacentList.remove(Integer.valueOf(nodeIdx));
		}

		public boolean isZero()
		{
			return weight == 0;
		}

		public boolean isDeletable()
		{
			return weight == 0 && getAdjacentCount() == 1;
		}
	}

	private int zeroCount = 0;
	private final Node[] nodes;
	private int answer = 0;

	public Prob3(int[] a, int[][] edges)
	{
		// Initialize Nodes with weight
		// Initialize zeroCount & Check availability
		nodes = new Node[a.length];
		int sum = 0;
		for (int i = 0; i < nodes.length; i++)
		{
			nodes[i] = new Node(a[i]);
			if (a[i] == 0) zeroCount++;
			sum += a[i];
		}
		if (sum != 0) answer = -1;

		// Initialize adjList
		for (int[] edge : edges)
		{
			nodes[edge[0]].addEdge(edge[1]);
			nodes[edge[1]].addEdge(edge[0]);
		}

		for (int i = 0; i < nodes.length; i++)
			isolateNode(i);
	}

	public int solve()
	{
		if (answer != 0) return -1;

		while (!isAllZero())
		{
			for (int i = 0; i < nodes.length; i++)
			{
				if (isAllZero()) break;
				if (nodes[i].getAdjacentCount() != 1) continue;
				answer += Math.abs(nodes[i].getWeight());
				swapWeightAsZero(i);
				isolateNode(i);
			}
		}

		return answer;
	}

	private boolean swapWeightAsZero(int nodeIdx)
	{
		if (nodes[nodeIdx].getAdjacentCount() != 1) return false;
		if (nodes[nodeIdx].getWeight() != 0) zeroCount++;

		int adjNode = nodes[nodeIdx].getAdjacentList().get(0);
		boolean wasAdjNodeZero = nodes[adjNode].getWeight() == 0;

		nodes[adjNode].increaseWeight(nodes[nodeIdx].setZeroWeight());
		if(!wasAdjNodeZero && nodes[adjNode].isZero())	zeroCount++;

		return true;
	}

	private boolean isolateNode(int nodeIdx)
	{
		if (!nodes[nodeIdx].isDeletable()) return false;

		int adjacentIdx = nodes[nodeIdx].adjacentList.get(0);

		nodes[nodeIdx].deleteEdge(adjacentIdx);
		nodes[adjacentIdx].deleteEdge(nodeIdx);

		return true;
	}

	private boolean isAllZero()
	{
		return zeroCount == nodes.length;
	}
}