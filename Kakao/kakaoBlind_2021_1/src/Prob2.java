import java.util.*;

public class Prob2
{
	private final String[] customers;
	private final Map<String, Integer>[] courses;
	private final int[] courseMenuNums;

	public Prob2(String[] orders, int[] courseMenuNums)
	{
		customers = new String[orders.length];
		courses = new Map[courseMenuNums.length];

		for (int i = 0; i < orders.length; i++)
		{
			char[] tmp = orders[i].toCharArray();
			Arrays.sort(tmp);
			customers[i] = new String(tmp);
		}

		this.courseMenuNums = courseMenuNums;
	}

	public String[] solve()
	{
		for (String customer: customers) {
			for (int i = 0; i < courses.length; i++)
			{
				if(courses[i] == null)	courses[i] = new TreeMap<>();
				combine(courses[i], new StringBuilder(), customer, courseMenuNums[i]);
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
}