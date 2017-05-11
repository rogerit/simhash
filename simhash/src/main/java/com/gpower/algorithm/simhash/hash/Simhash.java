/**
 * 
 */
package com.gpower.algorithm.simhash.hash;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zhangcheng
 * 
 */
public class Simhash {
	


	public int hammingDistance(int hash1, int hash2) {
		int i = hash1 ^ hash2;
		//1011001001011100110101011101111
		//1011000001011100110101011101111
		//0000001000000000000000000000000
		i = i - ((i >>> 1) & 0x55555555);
		i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
		i = (i + (i >>> 4)) & 0x0f0f0f0f;
		i = i + (i >>> 8);
		i = i + (i >>> 16);
		return i & 0x3f;
	}
	
	public int hammingDistance(long hash1, long hash2) {
		long i = hash1 ^ hash2;
		i = i - ((i >>> 1) & 0x5555555555555555L);
		i = (i & 0x3333333333333333L) + ((i >>> 2) & 0x3333333333333333L);
		i = (i + (i >>> 4)) & 0x0f0f0f0f0f0f0f0fL;
		i = i + (i >>> 8);
		i = i + (i >>> 16);
		i = i + (i >>> 32);
		return (int) i & 0x7f;
	}
	
	public long simhash64(List<String> tokens) {
		int bitLen = 64;
		int[] bits = new int[bitLen];
		for (String t : tokens) {
			long v = MurmurHash.hash64(t);
			for (int i = 0; i < bitLen; ++i) {
				if (((v >> i) & 1) == 1)
					++bits[i];
				else
					--bits[i];
			}
		}
		
		//BitPeakValue(bits);
		
		long hash = 0x0000000000000000;
		long one = 0x0000000000000001;
		
		for (int i = 0; i < bitLen; ++i) {
			if (bits[i] > 1) {
				hash |= one;
			}
			one = one << 1;
		}
		return hash;
		
	}
	public int simhash32(List<String> tokens) {
		int bitLen = 32;
		int[] bits = new int[bitLen];
		for (String t : tokens) {
			int v = MurmurHash.hash32(t);
			for (int i = 0; i < bitLen; ++i) {
				if (((v >> i) & 1) == 1)
					++bits[i];
				else
					--bits[i];
			}
		}
		//BitPeakValue(bits);
		int hash = 0x00000000;
		int one = 0x00000001;
		for (int i = 0; i < bitLen; ++i) {
			if (bits[i] > 1) {
				hash |= one;
			}
			one = one << 1;
		}
		return hash;
	}
	
	public void BitsPeakValue (int[] bits){
		int max=0,min=0;
		for(int i : bits){
			//System.out.print(i+",");
			max = max<i? i:max;
			min = min<i? min:i;
		}
		System.out.println();
		System.out.println("simhash min:");
		System.out.println(min);
		System.out.println("simhash max:");
		System.out.println(max);
	}
}
