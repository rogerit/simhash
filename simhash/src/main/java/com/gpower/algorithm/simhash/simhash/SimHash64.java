package com.gpower.algorithm.simhash.simhash;



import java.util.List;


import com.gpower.algorithm.simhash.hash.MurmurHash;


public class SimHash64 extends SimHashUncle<Long>{
	private static final int BIT_LEN = 64;

	private int[] bits = new int[BIT_LEN];

	@Override
	public Integer hammingDistance(Long hash1, Long hash2) {
		long i = hash1 ^ hash2;
		i = i - ((i >>> 1) & 0x5555555555555555L);
		i = (i & 0x3333333333333333L) + ((i >>> 2) & 0x3333333333333333L);
		i = (i + (i >>> 4)) & 0x0f0f0f0f0f0f0f0fL;
		i = i + (i >>> 8);
		i = i + (i >>> 16);
		i = i + (i >>> 32);
		return (int) i & 0x7f;
	}

	@Override
	public void gather(Integer weight, List<String> tokens) {
		for (String t : tokens) {
			long v = MurmurHash.hash64(t);
			for (int i = 0; i < BIT_LEN; ++i) {
				if (((v >> i) & 1) == 1)
					this.bits[i] += weight;
				else
					this.bits[i] -= weight;
			}
		}
	}

	@Override
	public Long hash() {
		long hash = 0x0000000000000000;
		long one = 0x0000000000000001;
		
		for (int i = 0; i < BIT_LEN; ++i) {
			if (this.bits[i] > 1) {
				hash |= one;
			}
			one = one << 1;
		}
		return hash;
	}
	
	
}
