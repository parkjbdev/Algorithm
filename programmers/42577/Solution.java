import java.util.HashMap;

class Solution {
	public static void main(String[] args)
	{
		String[] phone_book = {"97674223", "1195524421", "1195" };
		System.out.println(solution(phone_book));
	}

	public static boolean solution(String[] phone_book) 
	{
		HashMap<String, Boolean> map = new HashMap<>();
		for(String phone : phone_book)
		{
			for (int i = 1; i <= phone.length(); i++)
			{
				if (map.get(phone.substring(0, i)) != null)
				{
					if (map.get(phone.substring(0, i)))	return false;
					else
					{
						if (phone.substring(0, i).equals(phone))	return false;
					}
				}
				else
				{
					map.put(phone.substring(0, i), false);
					if (i == phone.length())	map.put(phone, true);
				}
			}
		}

		return true;
	}
}