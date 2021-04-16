import java.util.Stack;

class Prob2
{

	String str;

	public Prob2(String s)
	{
		this.str = s;
	}

	public int solve()
	{
		int answer = 0;
		for (int x = 0; x < str.length(); x++)
		{
			if (isProper(str)) answer++;
			rotateStr();
		}

		return answer;
	}

	private String rotateStr()
	{
		str += str.charAt(0);
		str = str.substring(1);

		return str;
	}

	private boolean isProper(String str)
	{
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < str.length(); i++)
		{
			char c = str.charAt(i);

			if (!stack.empty() &&
					((c == ')' && stack.peek() == '(') ||
							(c == '}' && stack.peek() == '{') ||
							(c == ']' && stack.peek() == '[')))
			{
				stack.pop();
				continue;
			}
			stack.push(c);
		}
		return stack.empty();
	}

}
