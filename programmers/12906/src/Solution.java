import java.util.*;

public class Solution {
	public int[] solution(int []arr) {
		ArrayList<Integer> answer = new ArrayList<>();
		answer.add(arr[0]);
		for(int i = 1;i < arr.length;i++)
		{
			if(arr[i] != answer.get(answer.size() - 1)) answer.add(arr[i]);
		}

		return answer.stream().mapToInt(i->i).toArray();
	}
}