package com.gpower.algorithm.simhash.simhash;



import java.util.List;





public class SimHash64 extends SimHashSimple<Long>{
	private static final int BIT_LEN = 64;

	private int[] bits = new int[BIT_LEN];
	
	

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
