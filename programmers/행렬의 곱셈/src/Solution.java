import java.util.Arrays;
public class Solution
{
	public static void main(String[] args)
	{
		int[][] arr1 = {{1, 4}, {3, 2}, {4, 1}};
		int[][] arr2 = {{3, 3}, {3, 3}};
		int[][] arr11 = {{2, 3, 2}, {4, 2, 4}, {3, 1, 4}};
		int[][] arr22 = {{5, 4, 3}, {2, 4, 1}, {3, 1, 1}};
		System.out.println(Arrays.deepToString(solution(arr1, arr2)));
		System.out.println(Arrays.deepToString(solution(arr11, arr22)));
	}
	public static int[][] solution(int [][] arr1, int [][] arr2)
	{
		int[][] answer = new int[arr1.length][arr2[0].length];

        for (int x = 0;x < answer.length;x++)
        {
            for (int y = 0;y < answer[0].length; y++)
            {
                for (int i = 0;i < arr2.length;i++)
                    answer[x][y] += arr1[x][i] * arr2[i][y];
            }
        }


	//	System.out.println(Arrays.deepToString(answer));
		return answer;
	}
}
