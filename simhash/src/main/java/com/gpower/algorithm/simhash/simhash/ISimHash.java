package com.gpower.algorithm.simhash.simhash;

import java.util.List;


public interface ISimHash<T extends Number> {
	
	public void gather(Integer weight, List<String> tokens);
	
	public T hash();
}
