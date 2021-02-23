public class Prob5
{
	boolean debug = true;
	int playTime, adTime;
	Log[] logs;

	public Prob5(String play_time, String adv_time, String[] logs)
	{
		playTime = getIntTime(play_time);
		adTime = getIntTime(adv_time);
		if(debug)	System.out.println("playTime: " + playTime);
		if(debug)	System.out.println("adTime: " + adTime);

		this.logs = new Log[logs.length];
		for (int i = 0; i < logs.length; i++)
		{
			String[] tmp = logs[i].split("-");
			this.logs[i] = new Log(getIntTime(tmp[0]), getIntTime(tmp[1]));
			if(debug) System.out.println("log: " + this.logs[i].startTime + " ~ " + this.logs[i].endTime);
		}
	}

	private int getIntTime(String strTime)
	{
		String[] time = strTime.split(":");

		int hour = Integer.parseInt(time[0]);
		int minute = Integer.parseInt(time[1]);
		int second = Integer.parseInt(time[2]);

		return hour * 3600 + minute * 60 + second;
	}

	private String getStringTime(int intTime)
	{
		int second = intTime % 60;
		int minute = (intTime / 60) % 60;
		int hour = (intTime / 3600);

		return hour + ":" + minute + ":" + second;
	}

	public String solve()
	{
		int result = 0;



		return getStringTime(result);
	}
}

class Log
{
	public final int startTime;
	public final int endTime;
	public Log(int startTime, int endTime)
	{
		this.startTime = startTime;
		this.endTime = endTime;
	}
}