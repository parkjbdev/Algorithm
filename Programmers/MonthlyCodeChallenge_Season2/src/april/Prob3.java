package april;

import java.util.*;

class Prob3
{
	static class Node
	{
		private static class Weight
		{
			private long weight;

			Weight(int weight)
			{
				this.weight = weight;
			}

			public boolean isZero()
			{
				return weight == 0;
			}

			public void increase(long increment)
			{
				weight += increment;
			}

			public void setZero()
			{
				weight = 0;
			}

			public long get()
			{
				return weight;
			}
		}

		public final Weight weight;
		private final Set<Integer> adjacent = new HashSet<>();

		public Node(int weight)
		{
			this.weight = new Weight(weight);
		}

		public int getAdjacentCount()
		{
			return adjacent.size();
		}

		public void addEdge(int nodeIdx)
		{
			adjacent.add(nodeIdx);
		}

		public void deleteEdge(int nodeIdx)
		{
			adjacent.remove(nodeIdx);
		}

		public boolean isLeaf()
		{
			return getAdjacentCount() == 1;
		}

		public void isolate()
		{
			if (!isLeaf()) return;
			adjacent.clear();
		}

		public int peekAdjacentIndex()
		{
			return (int) adjacent.toArray()[0];
		}
	}

	private int zeroCount = 0;
	private final Node[] nodes;
	private long answer = 0;

	public Prob3(int[] a, int[][] edges)
	{
		// Initialize Nodes with weight
		// Initialize zeroCount & Check availability
		nodes = new Node[a.length];
		long sum = 0;
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
	}

	public long solve()
	{
		if (answer != 0) return -1;

		boolean[] isVisit = new boolean[nodes.length];
		dfs(isVisit, 0);

		return answer;
	}

	private void dfs(boolean[] isVisit, int nodeIdx)
	{
		if (isAllZero()) return;

		isVisit[nodeIdx] = true;

		Integer[] adjacent = nodes[nodeIdx].adjacent.toArray(new Integer[0]);
		for (int integer : adjacent)
		{
			if (!isVisit[integer])
				dfs(isVisit, integer);
		}

		if (nodes[nodeIdx].isLeaf())
		{
			answer += swapWeightAsZero(nodeIdx);
			isolateNode(nodeIdx);
		}
	}

	private long swapWeightAsZero(int nodeIdx)
	{
		Node node = nodes[nodeIdx];
		Node adjNode = nodes[node.peekAdjacentIndex()];

		// Check Previous Node zeroWeight stat
		boolean wasZeroNode = node.weight.isZero();
		boolean wasZeroAdjNode = adjNode.weight.isZero();

		// Swap Weight
		long increment = node.weight.get();
		node.weight.setZero();
		adjNode.weight.increase(increment);

		// Calculate zeroCount
		boolean isZeroAdjNode = adjNode.weight.isZero();

		if (!wasZeroNode) zeroCount++;
		if (!wasZeroAdjNode && isZeroAdjNode) zeroCount++;

		return Math.abs(increment);
	}

	private void isolateNode(int nodeIdx)
	{
		Node node = nodes[nodeIdx];
		Node adjNode = nodes[node.peekAdjacentIndex()];

		node.isolate();
		adjNode.deleteEdge(nodeIdx);
	}

	private boolean isAllZero()
	{
		return zeroCount == nodes.length;
	}
}

class Prob3_Compact
{
	static class Node
	{
		ArrayList<Integer> adjacentList = new ArrayList<>();
	}

	static Node[] nodes;
	static long answer = 0;
	static long[] weight;

	public Prob3_Compact(int[] a, int[][] edges)
	{
		weight = Arrays.stream(a).asLongStream().toArray();
		nodes = new Node[a.length];
		for (int i = 0; i < nodes.length; i++) nodes[i] = new Node();

		for (int[] edge : edges)
		{
			nodes[edge[0]].adjacentList.add(edge[1]);
			nodes[edge[1]].adjacentList.add(edge[0]);
		}
	}

	public static long solve()
	{
		if (Arrays.stream(weight).sum() != 0) return -1;
		dfs(0, 0);
		return answer;
	}

	private static void dfs(int current, int parent)
	{
		for (int node : nodes[current].adjacentList)
			if (node != parent) dfs(node, current);

		weight[parent] += weight[current];
		answer += Math.abs(weight[current]);
	}
}