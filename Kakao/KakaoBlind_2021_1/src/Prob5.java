import java.util.*;

public class Prob5
{
	boolean debug = true;
	int playTime, adTime;
	TimeStartEnd[] logs;
	int[] timePoints;
	Map<Integer, Integer> timePointMap;

	public Prob5(String play_time, String adv_time, String[] logs)
	{
		playTime = getIntTime(play_time);
		adTime = getIntTime(adv_time);
		if (debug) System.out.println("playTime: " + playTime);
		if (debug) System.out.println("adTime: " + adTime);

		this.logs = new TimeStartEnd[logs.length];
		for (int i = 0; i < logs.length; i++)
		{
			int start = getIntTime(logs[i].substring(0, 8));
			int end = getIntTime(logs[i].substring(9));
			this.logs[i] = new TimeStartEnd(start, end);
			if (debug) System.out.println("log[" + i + "]: " + this.logs[i].start + " ~ " + this.logs[i].end);
		}
		timePoints = createTimePoints();
		timePointMap = createTimePointMap(timePoints);
		if (debug) System.out.println(Arrays.toString(timePoints));
	}

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

	private int[] createTimePoints()
	{
		Set<Integer> times = new HashSet<>();
		for (TimeStartEnd log : logs)
		{
			times.add(log.start);
			times.add(log.end);
		}
		return times.stream().sorted().mapToInt(Integer::intValue).toArray();
	}

	private Map<Integer, Integer> createTimePointMap(int[] timePoints)
	{
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < timePoints.length; i++)
			map.put(timePoints[i], i);
		return map;
	}

	private int getIndexOfTime(int time)
	{
		return timePointMap.get(time);
	}

	private int calcAccTime(int[] sumTimes, int startIdx)
	{
		int accTime = 0;
		int accAllTime = 0;

		for (int endIdx = startIdx + 1; endIdx < sumTimes.length; endIdx++)
		{
			int timeDiff = timePoints[endIdx] - timePoints[endIdx - 1];
			accTime += timeDiff;
			if (accTime < adTime) accAllTime += timeDiff * sumTimes[endIdx];
			else {
				accAllTime += (adTime - accTime) * sumTimes[endIdx];
				break;
			}
		}

		return accAllTime;
	}

	public String solve()
	{
		if (playTime <= adTime) return getStringTime(0);

		int[] sumTimes = new int[timePoints.length - 1];
		for (TimeStartEnd log : logs)
		{
			int startIdx = getIndexOfTime(log.start);
			int endIdx = getIndexOfTime(log.end);
			for (int i = startIdx; i < endIdx; i++) sumTimes[i]++;
		}

		int result = 0;
		int max = 0;

		for (int startIdx = 0; startIdx < sumTimes.length; startIdx++)
		{
			int accTime = calcAccTime(sumTimes, startIdx);

			// Find Maximizing result
			if (accTime > max)
			{
				max = accTime;
				result = timePoints[startIdx];
			}
		}

		return getStringTime(result);
	}
}

class TimeStartEnd
{
	public final int start;
	public final int end;

	public TimeStartEnd(int start, int endTime)
	{
		this.start = start;
		this.end = endTime;
	}
}