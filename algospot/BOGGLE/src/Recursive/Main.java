package Recursive;

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
	private int x;
	private int y;

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

	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
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
			for(int i = 0;i < mapX;i++) for(int j = 0;j < mapY;j++)
				if(!word.isContain) word.isContain = hasWord(new Coordinate(i, j), word.word);
	}

	public static boolean hasWord(Coordinate coordinate, String word)
	{
		if(!inRange(coordinate))	return false;
		if(gameBoard[coordinate.getX()][coordinate.getY()] != word.charAt(0))	return false;
		if(word.length() == 1)	return true;

		for (Coordinate delta : deltas)
		{
			Coordinate nextCoordinate = coordinate.add(delta);
			if(hasWord(nextCoordinate, word.substring(1))) return true;
		}
		return false;
	}

	public static boolean inRange(Coordinate coordinate)
	{
		return coordinate.getX() >= 0 && coordinate.getY() >= 0 &&
				coordinate.getX() < mapX && coordinate.getY() < mapY;
	}
}
