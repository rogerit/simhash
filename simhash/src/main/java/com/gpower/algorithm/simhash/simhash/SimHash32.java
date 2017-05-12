package com.gpower.algorithm.simhash.simhash;


import java.util.List;

import com.gpower.algorithm.simhash.hash.MurmurHash;


public class SimHash32 extends SimHashUncle<Integer> {

	private static final int BIT_LEN = 32;

	private int[] bits = new int[BIT_LEN];

	@Override
	public Integer hammingDistance(Integer hash1, Integer hash2) {
		int i = hash1 ^ hash2;
		i = i - ((i >>> 1) & 0x55555555);
		i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
		i = (i + (i >>> 4)) & 0x0f0f0f0f;
		i = i + (i >>> 8);
		i = i + (i >>> 16);
		return i & 0x3f;
	}

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
