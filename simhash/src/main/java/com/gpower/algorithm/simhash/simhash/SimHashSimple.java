package com.gpower.algorithm.simhash.simhash;

public abstract class SimHashSimple<T extends Number> implements ISimHash<T> {
	public static void BitsPeakValue(int[] bits) {
		int max = 0, min = 0;
		for (int i : bits) {
			max = max < i ? i : max;
			min = min < i ? min : i;
		}
		System.out.println("SimHash peak info:\n" + "simhash min:" + min + "\n"
				+ "simhash max:" + max);

	}
	public static Integer hammingDistance(Integer hash1, Integer hash2) {
		int i = hash1 ^ hash2;
		i = i - ((i >>> 1) & 0x55555555);
		i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
		i = (i + (i >>> 4)) & 0x0f0f0f0f;
		i = i + (i >>> 8);
		i = i + (i >>> 16);
		return i & 0x3f;
	}
	public static Integer hammingDistance(Long hash1, Long hash2) {
		long i = hash1 ^ hash2;
		i = i - ((i >>> 1) & 0x5555555555555555L);
		i = (i & 0x3333333333333333L) + ((i >>> 2) & 0x3333333333333333L);
		i = (i + (i >>> 4)) & 0x0f0f0f0f0f0f0f0fL;
		i = i + (i >>> 8);
		i = i + (i >>> 16);
		i = i + (i >>> 32);
		return (int) i & 0x7f;
	}
	
	
	
	
}
