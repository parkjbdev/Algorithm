package may;

public class Prob2
{
	private final long[] numbers;
	private long[] answers;

	public Prob2(long[] numbers)
	{
		this.numbers = numbers;
		this.answers = new long[numbers.length];
	}

	public long[] solve()
	{
		for (int i = 0; i < numbers.length; i++)
		{
			long number = numbers[i];
			if ((number & 1) == 1 && ((number >> 1) & 1) == 1) // 끝자리 비트 11일 경우
			{
				int bitMove = 1;
				while (
						!((((number >> bitMove) & 1) == 1) &&
								(((number >> (bitMove + 1)) & 1) == 0))
				) bitMove++;
				answers[i] = number + (long) Math.pow(2, bitMove);
			}
			else answers[i] = number + 1;

			// This is slow
			// for (answers[i] = numbers[i] + 1; Long.bitCount(answers[i] ^ numbers[i]) >= 3; answers[i]++) ;
		}
		return answers;
	}
}
