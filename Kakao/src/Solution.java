import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution
{
	public static String input() {
		String str = "";
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		try { str = bf.readLine(); }
		catch (IOException e) { e.printStackTrace(); }

		return str;
	}

	public static void main(String[] args)
	{
//		new Prob1(input());
		String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
		int[] course = {2, 3, 5};
		String[] ans = new Prob2(orders, course).solve();
		System.out.println(Arrays.toString(ans));
	}
}
