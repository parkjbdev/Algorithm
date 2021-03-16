public class Prob5_TwoPointer
{
	int playTime, adTime;
	long[] timeLine;

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

	public Prob5_TwoPointer(String playTime, String adTime, String[] logs)
	{
		this.playTime = getIntTime(playTime);
		this.adTime = getIntTime(adTime);
		this.timeLine = new long[this.playTime + 1];

		for (String log : logs)
		{
			int start = getIntTime(log.substring(0, 8));
			int end = getIntTime(log.substring(9));
			for (int i = start; i <= end; i++) timeLine[i]++;
		}
	}

	public String solve()
	{
		if(playTime <= adTime)	return getStringTime(0);
		int result = 0;
		int max = 0;

		int startIdx = 0;
		int endIdx = 1;
		int sum = 0;

		while(startIdx < timeLine.length && endIdx < timeLine.length)
		{
			if(endIdx - startIdx < adTime)
			{
				if(endIdx + 1 >= timeLine.length)	break;
				endIdx++;
				sum += timeLine[endIdx];
			}
			else
			{
				if(max < sum)
				{
					max = sum;
					result = startIdx;
				}
				sum -= timeLine[startIdx];
				startIdx++;
			}
		}

		return getStringTime(result);
	}
}
