package com.gpower.algorithm.simhash.simhash;

import java.util.List;

import com.gpower.algorithm.simhash.hash.MurmurHash;

public interface ISimHash<T extends Number> {
	
	public void gather(Integer weight, List<String> tokens);
	
	public T hash();
}
