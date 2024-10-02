package may;

import java.util.ArrayList;

public class Prob4
{
	private Node[] nodes;

	public Prob4(int[] values, int[][] edges, int[][] queries)
	{
		nodes = new Node[values.length];

		for (int i = 0; i < values.length; i++)
			nodes[i] = new Node(values[i]);

		for (int[] edge : edges)
		{
			if (edge[0] == 1)
			{
				nodes[0].setParent(0);
				nodes[0].addChild(edge[1]);
				nodes[edge[1] - 1].setParent(edge[0]);
			}
			else if (edge[1] == 1)
			{
				nodes[edge[0] - 1].setParent(0);
				nodes[0].addChild(edge[1]);
				nodes[edge[1] - 1].setParent(edge[0]);
			}

			nodes[0].setParent(0);
			nodes[0].addChild(edge[1]);
			nodes[edge[1] - 1].setParent(edge[0]);
		}
	}

	class Node
	{
		private int parent = 0;
		private ArrayList<Integer> childs;
		private int weight;

		public Node(int weight)
		{
			this.weight = weight;
		}

		public int getRootNode()
		{
			return parent;
		}

		public void setParent(int parent)
		{
			this.parent = parent;
		}
		public void addChild(int num)
		{

		}
		public int getWeight()
		{
			return this.weight;
		}

	}

}