package com.gpower.algorithm.simhash.simhash;

public abstract class SimHashUncle<T extends Number> implements ISimHash<T> {
	public void BitsPeakValue(int[] bits) {
		int max = 0, min = 0;
		for (int i : bits) {
			max = max < i ? i : max;
			min = min < i ? min : i;
		}
		System.out.println("SimHash peak info:\n" + "simhash min:" + min + "\n"
				+ "simhash max:" + max);

	}
}
