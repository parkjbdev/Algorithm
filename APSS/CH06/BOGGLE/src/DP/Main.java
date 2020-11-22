package DP;

import org.jetbrains.annotations.NotNull;
import java.util.Scanner;

class Word
{
	public final String word;
	public boolean isContain = false;

	Word(String word)
	{
		this.word = word;
	}
}

class Coordinate
{
	private final int x;
	private final int y;

	Coordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}

	public Coordinate add(@NotNull Coordinate c)
	{
		return new Coordinate(c.getX() + this.getX(), c.getY() + this.getY());
	}
}

public class Main
{
	public static Scanner sc = new Scanner(System.in);
	public static final int mapX = 5, mapY = 5;
	public static int testCaseCnt;
	public static char[][] gameBoard = new char[mapX][mapY];
	public static Word[] words;
	public static Boolean[][][] cache;
	public static final Coordinate[] deltas = {
			new Coordinate(-1, -1),
			new Coordinate(-1, 0),
			new Coordinate(-1, 1),
			new Coordinate(1, -1),
			new Coordinate(1, 0),
			new Coordinate(1, 1),
			new Coordinate(0, -1),
			new Coordinate(0, 1)
	};

	public static void main (String[] args)
	{
		testCaseCnt = sc.nextInt();
		for(int testCase = 0;testCase < testCaseCnt;testCase++)
		{
			input();
			solve();
			output();
		}
		sc.close();
	}

	public static void input()
	{
		for(int i = 0;i < mapY;i++)	gameBoard[i] = sc.next().toCharArray();

		words = new Word[sc.nextInt()];
		for (int i = 0;i < words.length;i++)	words[i] = new Word(sc.next());
	}

	public static void output()
	{
		for(Word word : words)
			System.out.println(word.word + " " + (word.isContain ? "YES" : "NO"));
	}

	public static void solve()
	{
		for (Word word : words)
		{
			// Initialization
			cache = new Boolean[mapX][mapY][word.word.length()];
			for(int i = 0;i < mapX;i++)	for(int j = 0;j < mapY;j++)
				for(int k = 0;k < word.word.length();k++)
					cache[i][j][k] = null;

			// Search word
			for (int i = 0; i < mapX; i++) for (int j = 0; j < mapY; j++)
				if (!word.isContain)
					word.isContain = hasWord(new Coordinate(i, j), word.word, 0);
		}
	}

	public static boolean hasWord(Coordinate coordinate, String word, int index)
	{
		if(!inRange(coordinate))	return false;
		if(gameBoard[coordinate.getX()][coordinate.getY()] != word.charAt(index))	return false;
		if(index + 1 == word.length())	return true;
		// return cached result
		if(cache[coordinate.getX()][coordinate.getY()][index] != null)
			return cache[coordinate.getX()][coordinate.getY()][index];

		for (Coordinate delta : deltas)
		{
			Coordinate nextCoordinate = coordinate.add(delta);
			if(hasWord(nextCoordinate, word, index + 1))
			{
				// cache result
				cache[coordinate.getX()][coordinate.getY()][index] = true;
				return true;
			}
		}
		// cache result
		cache[coordinate.getX()][coordinate.getY()][index] = false;
		return false;
	}

	public static boolean inRange(Coordinate coordinate)
	{
		return coordinate.getX() >= 0 && coordinate.getY() >= 0 &&
				coordinate.getX() < mapX && coordinate.getY() < mapY;
	}
}
