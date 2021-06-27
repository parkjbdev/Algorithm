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
			// (먼저 나온 번호의 자릿수) > (나중에 나온 번호의 자릿수)
			if (map.get(phone) != null)
				return false;
			
			for (int i = 1; i < phone.length(); i++)
			{
				//번호 substring 저장 되어 있을 경우
				if (map.get(phone.substring(0, i)) != null) {
					// (먼저 나온 번호의 자릿수) < (나중에 나온 번호의 자릿수)
					if (map.get(phone.substring(0, i)))// phone의 앞자리 substring이 번호로 존재할 경우
						return false;
				}
				// 번호 substring 저장 안되어 있을때
				else
					map.put(phone.substring(0, i), false);
			}
			
			map.put(phone, true);
		}

		return true;
	}
}