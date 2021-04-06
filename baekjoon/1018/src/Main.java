import java.io.*;
import java.util.Arrays;

public class Main
{
	static class IO
	{
		public static class Input extends BufferedReader
		{
			public Input()
			{
				super(new InputStreamReader(System.in));
			}

			public String[] readLineArguments() throws IOException
			{
				return super.readLine().split(" ");
			}

			public int readLineIntArgument() throws IOException
			{
				return readLineIntArguments()[0];
			}

			public int[] readLineIntArguments() throws IOException
			{
				return Arrays.stream(readLineArguments()).mapToInt(Integer::parseInt).toArray();
			}
		}

		public static class Output extends BufferedWriter
		{
			public Output()
			{
				super(new OutputStreamWriter(System.out));
			}

			public void write(Object obj) throws IOException
			{
				super.write(obj.toString());
				super.write('\n');
			}

			@Override
			public void write(int c) throws IOException
			{
				write(String.valueOf(c));
			}

			@Override
			public void write(String str) throws IOException
			{
				super.write(str);
				super.write('\n');
			}
		}
	}

	public static void main(String[] args) throws IOException
	{
		IO.Input input = new IO.Input();
		IO.Output output = new IO.Output();

		int[] inputs = input.readLineIntArguments();
		int N = inputs[0];
		int M = inputs[1];
		boolean[][] isBlackMap = new boolean[N][M];

		for (int i = 0; i < N; i++)
		{
			String tmp = input.readLine();
			for (int j = 0; j < M; j++)
			{
				switch (tmp.charAt(j))
				{
					case 'B', 'b' -> isBlackMap[i][j] = true;
					case 'W', 'w' -> isBlackMap[i][j] = false;
				}
			}
		}
		input.close();

		output.write(new BOJ1018(isBlackMap).solve());
		output.close();
	}
}

class BOJ1018
{
	private final int CHESSBOARD_SIZE = 8;
	private final int N, M;
	private final boolean[][] isBlackBoard;
	private final boolean[][] chessBoardWhite, chessBoardBlack;

	public BOJ1018(boolean[][] isBlackBoard)
	{
		this.isBlackBoard = isBlackBoard;
		N = isBlackBoard.length;
		M = isBlackBoard[0].length;
		chessBoardWhite = createChessBoard(false);
		chessBoardBlack = createChessBoard(true);
	}

	private boolean[][] createChessBoard(boolean start)
	{
		boolean[][] chessBoard = new boolean[CHESSBOARD_SIZE][CHESSBOARD_SIZE];
		for (int i = 0; i < CHESSBOARD_SIZE; i++)
		{
			for (int j = 0; j < CHESSBOARD_SIZE; j++)
			{
				chessBoard[i][j] = start;
				start = !start;
			}
			start = !start;
		}
		return chessBoard;
	}

	public int solve()
	{
		int min = Integer.MAX_VALUE;
		for (int i = 0; i <= N - CHESSBOARD_SIZE; i++)
		{
			for (int j = 0; j <= M - CHESSBOARD_SIZE; j++)
			{
				boolean[][] tmpBoard = sliceBoard(i, j);
				int cntToColor = checker(tmpBoard);
				min = Math.min(min, cntToColor);
			}
		}

		return min;
	}

	private boolean[][] sliceBoard(int startX, int startY)
	{
		boolean[][] slicedBoard = new boolean[CHESSBOARD_SIZE][CHESSBOARD_SIZE];
		for (int i = 0; i < CHESSBOARD_SIZE; i++)
			System.arraycopy(isBlackBoard[startX + i], startY, slicedBoard[i], 0, CHESSBOARD_SIZE);
		return slicedBoard;
	}

	private int checker(boolean[][] blackBoard)
	{
		int whiteCnt = 0;
		int blackCnt = 0;

		for (int i = 0; i < CHESSBOARD_SIZE; i++)
		{
			for (int j = 0; j < CHESSBOARD_SIZE; j++)
			{
				if (blackBoard[i][j] != chessBoardBlack[i][j]) blackCnt++;
				if (blackBoard[i][j] != chessBoardWhite[i][j]) whiteCnt++;
			}
		}
		return Math.min(blackCnt, whiteCnt);
	}
}
