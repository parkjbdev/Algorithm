import java.util.*;

public class Prob2
{
	private final Customer[] customers;
	private final Map<String, Integer>[] courses;
	private final int[] courseMenuNums;

	public Prob2(String[] orders, int[] courseMenuNums)
	{
		customers = new Customer[orders.length];
		courses = new Map[courseMenuNums.length];

		for (int i = 0; i < orders.length; i++)
		{
			char[] tmp = orders[i].toCharArray();
			Arrays.sort(tmp);
			customers[i] = new Customer(new String(tmp));
		}

		this.courseMenuNums = courseMenuNums;
	}

	public String[] solve()
	{
		for (Customer customer: customers) {
			for (int i = 0; i < courses.length; i++)
			{
				if(courses[i] == null)	courses[i] = new TreeMap<String, Integer>();
				combine(courses[i], new StringBuilder(), customer.orderString, courseMenuNums[i]);
			}
		}

		ArrayList<String> ansArr = new ArrayList<>();

		for (Map<String, Integer> course : courses)
		{
			// Find Max Value
			int max = 0;
			for (Map.Entry<String, Integer> entry : course.entrySet())
				if (entry.getValue() > max) max = entry.getValue();

			// Find Answer
			for (Map.Entry<String, Integer> entry : course.entrySet())
				if (entry.getValue() == max && max >= 2) ansArr.add(entry.getKey());
		}

		// Convert Arraylist to array & Sort
		String[] ans = ansArr.toArray(new String[0]);
		Arrays.sort(ans);
		return ans;
	}

	private void combine(Map<String, Integer> save, StringBuilder sb, String base, int combNum)
	{
		if (combNum == 0)
		{
			int value = save.getOrDefault(sb.toString(),0);
			save.put(sb.toString(), ++value);
			return;
		}

		for (int i = 0; i < base.length(); i++)
		{
			sb.append(base.charAt(i));
			combine(save,sb, base.substring(i + 1), combNum - 1);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	// Customer Class
	private class Customer
	{
		public final String orderString;

		public Customer(String orders)
		{
			this.orderString = orders;
		}

		public boolean isExist(Character menu)
		{
			return orderString.contains(menu.toString());
		}

		public boolean isExist(Character[] menus)
		{
			for (Character menu : menus)
				if (!orderString.contains(menu.toString()))
					return false;

			return true;
		}

		public boolean isExist(String menusString)
		{
			Character[] menus = Utils.split(menusString);
			return isExist(menus);
		}
	}
}

class Utils
{
	public static Character[] split(String str)
	{
		Character[] chars = new Character[str.length()];
		for (int i = 0; i < str.length(); i++)
			chars[i] = str.charAt(i);
		return chars;
	}
}