package BOARDCOVER;
import java.util.Scanner;

class Pair
{
	final int x, y;

	Pair(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString()
	{
		return "(" + x + ", " + y + ')';
	}
}

class Block
{
	final Pair[] delta;
	final int size;

	Block(Pair[] block)
	{
		delta = block;
		size = delta.length;
	}
}

class BoardCover
{
	int Answer = 0;
	final int mapSizeX, mapSizeY;
	boolean[][] map;
	int fillCount = 0;

	final Block[] blocks = {
			new Block(new Pair[]{new Pair(0, 0), new Pair(1, 0), new Pair(0, 1)}),    // ┏
			new Block(new Pair[]{new Pair(0, 0), new Pair(0, 1), new Pair(1, 1)}),    // ┓
			new Block(new Pair[]{new Pair(0, 0), new Pair(1, 0), new Pair(1, 1)}),    // ┗
			new Block(new Pair[]{new Pair(0, 0), new Pair(1, 0), new Pair(1, -1)})    // ┛
	};

	public BoardCover(Scanner sc)
	{
		// input
		mapSizeX = sc.nextInt();
		mapSizeY = sc.nextInt();
		sc.nextLine();
		map = new boolean[mapSizeX][mapSizeY];

		for (int i = 0; i < mapSizeX; i++)
		{
			String tmp = sc.nextLine();
			for (int j = 0; j < mapSizeY; j++)
			{
				map[i][j] = tmp.charAt(j) == '#';
				if (tmp.charAt(j) == '#') fillCount++;
			}
		}
		solve();
		output();
	}

	public void solve()
	{
		if ((mapSizeX * mapSizeY - fillCount) % blocks[0].size != 0)
			return;
		recursive(new Pair(0, 0));
	}

	public void output()
	{
		System.out.println(Answer);
	}

	private void recursive(Pair coord)
	{
		if (isAllFilled())
		{
			Answer++;
			return;
		}

		coord = nextPair(coord);

		for (int blockType = 0; blockType < blocks.length; blockType++)
		{
			if (isFillPossible(coord, blockType))
			{
				fillBlock(coord, blockType);
				recursive(coord);
				deleteBlock(coord, blockType);
			}
		}
	}

	private Pair nextPair(Pair pair)
	{
		int x = pair.x;
		int y = pair.y;

		while (map[x][y])
		{
			if (x + 1 >= mapSizeX && y + 1 >= mapSizeY) return null;
			else if (x + 1 < mapSizeX && y + 1 >= mapSizeY)
			{
				x++;
				y = 0;
			}
			else y++;
		}
		return new Pair(x, y);
	}

	private void fillBlock(Pair coord, int blockType)
	{
		for (int i = 0; i < blocks[blockType].size; i++)
		{
			int dx = blocks[blockType].delta[i].x;
			int dy = blocks[blockType].delta[i].y;
			map[coord.x + dx][coord.y + dy] = true;
			fillCount++;
		}
	}

	private void deleteBlock(Pair coord, int blockType)
	{
		for (int i = 0; i < blocks[blockType].size; i++)
		{
			int dx = blocks[blockType].delta[i].x;
			int dy = blocks[blockType].delta[i].y;
			map[coord.x + dx][coord.y + dy] = false;
			fillCount--;
		}
	}

	private boolean isFillPossible(Pair coord, int blockType)
	{
		for (int i = 0; i < blocks[blockType].size; i++)
		{
			int dx = blocks[blockType].delta[i].x;
			int dy = blocks[blockType].delta[i].y;
			Pair tmp = new Pair(coord.x + dx, coord.y + dy);
			if (!isRange(tmp) || map[tmp.x][tmp.y])
				return false;
		}
		return true;
	}

	private boolean isRange(Pair pair)
	{
		return pair.x < mapSizeX && pair.y < mapSizeY && pair.x >= 0 && pair.y >= 0;
	}

	private boolean isAllFilled()
	{
		return fillCount == mapSizeX * mapSizeY;
	}
}

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int testCaseCnt = sc.nextInt();
		for (int testCase = 0; testCase < testCaseCnt; testCase++) new BoardCover(sc);
		sc.close();
	}
}