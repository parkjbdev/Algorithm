public class Prob5_Sliding
{
	int playTime, adTime;
	int[] timeLine;

	private int getIntTime(String strTime)
	{
		int hour = Integer.parseInt(strTime.substring(0, 2));
		int minute = Integer.parseInt(strTime.substring(3, 5));
		int second = Integer.parseInt(strTime.substring(6, 8));

		return hour * 3600 + minute * 60 + second;
	}

	private String getStringTime(int intTime)
	{
		int second = intTime % 60;
		int minute = (intTime / 60) % 60;
		int hour = (intTime / 3600);

		return String.format("%02d", hour) + ":" +
				String.format("%02d", minute) + ":" +
				String.format("%02d", second);
	}

	public Prob5_Sliding(String playTime, String adTime, String[] logs)
	{
		this.playTime = getIntTime(playTime);
		this.adTime = getIntTime(adTime);
		this.timeLine = new int[this.playTime + 1];

		for (String log : logs)
		{
			int start = getIntTime(log.substring(0, 8));
			int end = getIntTime(log.substring(9));
			for (int i = start; i < end; i++) timeLine[i]++;
		}
	}

	public String solve()
	{
		int result = 0;
		long max, sum = 0;

		for (int i = 0; i < adTime; i++)	sum += timeLine[i];
		max = sum;

		for (int start = 0; start + adTime < timeLine.length; start++)
		{
			sum = sum - timeLine[start] + timeLine[start + adTime];

			if (max < sum)
			{
				max = sum;
				result = start + 1;
			}
		}

		return getStringTime(result);
	}
}
