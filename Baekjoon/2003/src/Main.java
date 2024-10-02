import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) arr[i] = sc.nextInt();
		sc.close();
		System.out.println(solution(N, M, arr));
	}

	public static int solution(int N, int M, int[] arr)
	{
		int startIdx = 0;
		int endIdx = 0;
		int cnt = 0, sum = 0;

		while(startIdx < N && endIdx <= N)
		{
			if (sum == M)	cnt++;
			if(sum < M)
			{
				endIdx++;
				if(endIdx > N)	break;
				if (endIdx > 0)	sum += arr[endIdx - 1];
			}
			else
			{
				sum -= arr[startIdx];
				startIdx++;
				if(startIdx >= N)	break;
			}
		}

		return cnt;
	}
}
