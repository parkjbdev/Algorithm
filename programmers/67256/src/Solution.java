class Pair
{
	public int x;
	public int y;
	Pair(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}

class Solution
{
	private final int[] leftNum = {1, 4, 7};
	private final int[] rightNum = {3, 6, 9};
	private final int[] centerNum = {2, 5, 8, 0};

	Pair left = new Pair(0, 3);
	Pair right = new Pair(2, 3);
	StringBuilder answer = new StringBuilder();

	public String solution(int[] numbers, String hand)
	{
		for (int number : numbers)
		{
			if (containsLeft(number))	left(number);
			else if (containsRight(number))	right(number);
			else
			{
				if (distLeft(number) > distRight(number))	right(number);
				else if (distLeft(number) < distRight(number))	left(number);
				else
				{
					if(hand.equals("left"))	left(number);
					else if(hand.equals("right"))	right(number);
				}
			}
		}

		return answer.toString();
	}

	private void right(int num)
	{
		right = numCoord(num);
		answer.append("R");
	}

	private void left(int num)
	{
		left = numCoord(num);
		answer.append("L");
	}

	private Pair numCoord(int num)
	{
		int x, y;

		if(num == 0)
		{
			x = 1;
			y = 3;
		}
		else
		{
			x = (num - 1) % 3;
			y = (num - 1) / 3;
		}

		return new Pair(x, y);
	}

	private int distLeft(int num)
	{
		return Math.abs(numCoord(num).x - left.x) + Math.abs(numCoord(num).y - left.y);
	}
	private int distRight(int num)
	{
		return Math.abs(numCoord(num).x - right.x) + Math.abs(numCoord(num).y - right.y);
	}

	private boolean containsLeft(int num)
	{
		for (int target : leftNum)
			if (target == num) return true;
		return false;
	}

	private boolean containsRight(int num)
	{
		for (int target : rightNum)
			if (target == num) return true;
		return false;
	}

	private boolean containsCenter(int num)
	{
		for (int target : centerNum)
			if (target == num) return true;
		return false;
	}
}