import java.util.Map;
import java.util.HashMap;

class Solution {
	public static void main(String[] args) 
	{
		String[] participants = { "leo", "kiki", "eden" };
		String[] completions = { "eden", "kiki" };

		System.out.println(solution(participants, completions));
	}

	public static String solution(String[] participant, String[] completion)
	{
		HashMap<String, Integer> map = new HashMap<>();

		for(String key : participant)	map.put(key, map.getOrDefault(key, 0) + 1);
		for(String key : completion)	map.replace(key, map.get(key) - 1);
		
		for (Map.Entry<String, Integer> entry : map.entrySet())
		{
			if (entry.getValue() != 0)
				return entry.getKey();
		}

		return "";
	}
}