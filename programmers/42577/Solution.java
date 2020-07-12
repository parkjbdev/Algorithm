import java.util.Map;
import java.util.HashMap;
class Solution {
	public static void main(String[] args)
	{
		String[] phone_book = { "119", "97674223", "1195524421" };
		System.out.println(solution(phone_book));
	}

	public static boolean solution(String[] phone_book) 
	{
		HashMap<String, Integer> map = new HashMap<>();

		for (String phone : phone_book)
		{
			for (Map.Entry<String, Integer> entry : map.entrySet())
			{
				int length = phone.length() > entry.getKey().length() ? entry.getKey().length() : phone.length();

				if (length == entry.getKey().length())
				{
					if(phone.substring(0, length).equals(entry.getKey()))	return false;
				}
				else
				{
					if (entry.getKey().substring(0, length).equals(phone))	return false;
				}
			}
			map.put(phone, map.getOrDefault(phone, 0) + 1);
		}

		return true;
    }
}