import java.util.Scanner;

public class Solution
{
	public static void main(String[] args)
	{
		int[] a = {-5, 0, 2, 1, 2};
		int[][] edges = {{0, 1}, {3, 4}, {2, 3}, {0, 3}};
		System.out.println(new Prob3_Compact(a, edges).solve());
	}
}