import java.util.*;

class Prob3
{
	class Node
	{
		private class Weight
		{
			private int weight;
			Weight(int weight)
			{
				this.weight = weight;
			}
			public boolean isZero()
			{
				return weight == 0;
			}
			public int get()
			{
				return weight;
			}
			public int increase(int increment)
			{
				weight += increment;
				return weight;
			}
			public int setZero()
			{
				int tmp = weight;
				weight = 0;
				return tmp;
			}
		}
		public final Weight weight;
		private final Set<Integer> adjacents = new HashSet<>();

		public Node(int weight)
		{
			this.weight = new Weight(weight);
		}

		public int getAdjacentCount()
		{
			return adjacents.size();
		}

		public void addEdge(int nodeIdx)
		{
			adjacents.add(nodeIdx);
		}

		public void deleteEdge(int nodeIdx)
		{
			adjacents.remove(nodeIdx);
		}

		public boolean isLeaf() {
			return getAdjacentCount() == 1;
		}

		public boolean isolate()
		{
			if(!isLeaf() || !weight.isZero()) return false;
			adjacents.clear();

			return true;
		}

		public int peekAdjacentIndex()
		{
			return (int) adjacents.toArray()[0];
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

		int[] priority = new int[nodes.length];
		for (int i = 0; i < priority.length; i++) priority[i] = i;
		priority = Arrays.stream(priority)
				.boxed()
				.sorted(Comparator.comparingInt(a -> nodes[a].getAdjacentCount()))
				.mapToInt(i -> i)
				.toArray();

		for (int i: priority)
		{
			if(isAllZero())	break;
			if (nodes[i].getAdjacentCount() != 1) continue;
			answer += Math.abs(nodes[i].weight.get());
			swapWeightAsZero(i);
			isolateNode(i);
		}

		return answer;
	}

	private boolean swapWeightAsZero(int nodeIdx)
	{
		if (nodes[nodeIdx].getAdjacentCount() != 1) return false;
		if (nodes[nodeIdx].weight.get() != 0) zeroCount++;

		int adjNode = nodes[nodeIdx].peekAdjacentIndex();
		boolean wasAdjNodeZero = nodes[adjNode].weight.get() == 0;

		nodes[adjNode].weight.increase(nodes[nodeIdx].weight.setZero());
		if (!wasAdjNodeZero && nodes[adjNode].weight.isZero()) zeroCount++;

		return true;
	}

	private boolean isolateNode(int nodeIdx)
	{
		if (!nodes[nodeIdx].weight.isZero() || !nodes[nodeIdx].isLeaf())
			return false;

		int adjacentIdx = nodes[nodeIdx].peekAdjacentIndex();

		nodes[nodeIdx].isolate();
		nodes[adjacentIdx].deleteEdge(nodeIdx);

		return true;
	}

	private boolean isAllZero()
	{
		return zeroCount == nodes.length;
	}
}