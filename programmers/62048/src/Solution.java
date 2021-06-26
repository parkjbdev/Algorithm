import java.math.BigInteger;
public class Solution {
	public long solution(int w, int h) {
		return (long)w * (long)h - (w + h - BigInteger.valueOf(w).gcd(BigInteger.valueOf(h)).longValue());
	}
}