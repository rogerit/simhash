package com.gpower.algorithm.simhash.simhash;


import java.util.List;



public class SimHash32 extends SimHashSimple<Integer> {

	private static final int BIT_LEN = 32;

	private int[] bits = new int[BIT_LEN];

	

	@Override
	public void gather(Integer weight, List<String> tokens) {
		for (String t : tokens) {
			int v = MurmurHash.hash32(t);
			for (int i = 0; i < BIT_LEN; ++i) {
				if (((v >> i) & 1) == 1)
					this.bits[i] += weight;
				else
					this.bits[i] -= weight;
			}
		}
		return;
	}

	@Override
	public Integer hash() {
		int hash = 0x00000000;
		int one = 0x00000001;
		for (int i = 0; i < BIT_LEN; ++i) {
			if (bits[i] > 1) {
				hash |= one;
			}
			one = one << 1;
		}
		return hash;
	}

}
