import java.util.Stack;

public class Prob64061 {
  public final int[] moves;
  private final Stack<Integer>[] board;
  private final Stack<Integer> stack = new Stack<>();

  public Prob64061(int[][] board, int[] moves) {
    int N = board.length;

    this.board = new Stack[N];
    for (int i = 0; i < this.board.length; i++)
      this.board[i] = new Stack<>();

    for (int i = N - 1; i >= 0; i--)
      for (int j = 0; j < N; j++)
        if (board[i][j] != 0) this.board[j].push(board[i][j]);

    this.moves = moves;
  }

  public int solve() {
    int answer = 0;

    for (int move : moves) {
      if (board[move - 1].empty()) continue;
      int peek = board[move - 1].pop();

      if (!stack.empty() && stack.peek() == peek) {
        stack.pop();
        answer += 2;
      } else stack.push(peek);
    }

    return answer;
  }
}
