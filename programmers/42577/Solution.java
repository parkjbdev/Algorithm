import java.util.ArrayList;
class Solution {
	public static void main(String[] args)
	{
		String[] phone_book = { "119", "97674223", "1195524421" };
		System.out.println(solution(phone_book));
	}

	public static boolean solution(String[] phone_book) 
	{
		ArrayList<String> list = new ArrayList<>();

		for (String phonei : phone_book) {
			for (String phonej : list) {
				if ((phonei.length() > phonej.length() && phonei.substring(0, phonej.length()).equals(phonej))
						|| (phonei.length() <= phonej.length() && phonej.substring(0, phonei.length()).equals(phonei)))
					return false;
			}
			list.add(phonei);
		}

		return true;
	}
}