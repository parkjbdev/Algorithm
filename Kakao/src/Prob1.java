public class Prob1
{
	String id;
	public Prob1(String id)
	{
		this.id = id;
	}

	public String recommendation(String str) {
		str = str.toLowerCase();
		str = str.replaceAll("\\~|\\!|\\@|\\#|\\$|\\%|\\^|\\&|\\*|\\(|\\)|\\=|\\+|\\[|\\]|\\{|\\}|\\:|\\?|\\,|\\<|\\>|\\/", "");
		str = str.replaceAll("\\.+", ".");
		str = str.replaceAll("^\\.", "");
		str = str.replaceAll("\\.$", "");
		int len = str.length();
		if(len == 0)	str = "a";
		else if(len >= 16)	str = str.substring(0, 15);
		str = str.replaceAll("\\.$", "");
		while(str.length() < 3)	str = str + str.charAt(str.length() - 1);

		return str;
	}

	@Override
	public String toString()
	{
		return recommendation(id);
	}
}
