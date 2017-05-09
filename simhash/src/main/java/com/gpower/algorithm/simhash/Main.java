package com.gpower.algorithm.simhash;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;


import com.gpower.algorithm.simhash.MetaString.MetaString;

public class Main {
	
	public static void main(String[] args) throws IOException {

		
		test2();
		test3();
		test4();
		
		test5();
		
		test6();
		
		
		
	}
	
	public static void test1(){
		MetaString ms1 = new MetaString(new File("D:\\temp/1.html")); 
		MetaString ms2 = new MetaString(new File("D:\\temp/3.html")); 
		
		
		Simhash sh = new Simhash();
		int h321 = sh.simhash32(ms1.chnsTokens());
		int h322 = sh.simhash32(ms2.chnsTokens());
		System.out.println("32位simhash中文分词");
		System.out.println(Integer.toBinaryString(h321));
		System.out.println(Integer.toBinaryString(h322));
		System.out.println("两者的海明距离：");
		System.out.println(Integer.toBinaryString(h321^h322));
		System.out.println(sh.hammingDistance(h321, h322));
		System.out.println();
		
	}
	
	
	public static void test2(){
		MetaString ms1 = new MetaString(new File("D:\\temp/1.html")); 
		MetaString ms2 = new MetaString(new File("D:\\temp/3.html")); 
		
		
		Simhash sh = new Simhash();
		long h1 = sh.simhash64(ms1.chnsTokens());
		long h2 = sh.simhash64(ms2.chnsTokens());
		System.out.println("64位simhash中文分词");
		System.out.println(Long.toBinaryString(h1));
		System.out.println(Long.toBinaryString(h2));
		System.out.println("两者的海明距离：");
		System.out.println(Long.toBinaryString(h1^h2));
		System.out.println(sh.hammingDistance(h1, h2));
		System.out.println();
		
	}
	public static void test3(){
		MetaString ms1 = new MetaString(new File("D:\\temp/1.html")); 
		MetaString ms2 = new MetaString(new File("D:\\temp/3.html")); 
		
		
		Simhash sh = new Simhash();
		int h321 = sh.simhash32(ms1.neighborTokens());
		int h322 = sh.simhash32(ms2.neighborTokens());
		System.out.println("32位simhash相邻分词");
		System.out.println(Integer.toBinaryString(h321));
		System.out.println(Integer.toBinaryString(h322));
		System.out.println("两者的海明距离：");
		System.out.println(Integer.toBinaryString(h321^h322));
		System.out.println(sh.hammingDistance(h321, h322));
		System.out.println();
		
	}
	
	
	public static void test4(){
		MetaString ms1 = new MetaString(new File("D:\\temp/1.html")); 
		MetaString ms2 = new MetaString(new File("D:\\temp/3.html")); 
		
		
		Simhash sh = new Simhash();
		long h1 = sh.simhash64(ms1.neighborTokens());
		long h2 = sh.simhash64(ms2.neighborTokens());
		System.out.println("64位simhash相邻分词");
		System.out.println(Long.toBinaryString(h1));
		System.out.println(Long.toBinaryString(h2));
		System.out.println("两者的海明距离：");
		System.out.println(Long.toBinaryString(h1^h2));
		System.out.println(sh.hammingDistance(h1, h2));
		System.out.println();
		
	}
	
	public static void test5(){
		MetaString ms1 = new MetaString(new File("D:\\temp/1.html")); 
		MetaString ms2 = new MetaString(new File("D:\\temp/2.html")); 
		
		
		Simhash sh = new Simhash();
		int h321 = sh.simhash32(ms1.chnsTokens());
		int h322 = sh.simhash32(ms2.chnsTokens());
		System.out.println("32位simhash中文分词大段替换");
		System.out.println(Integer.toBinaryString(h321));
		System.out.println(Integer.toBinaryString(h322));
		System.out.println("两者的海明距离：");
		System.out.println(Integer.toBinaryString(h321^h322));
		System.out.println(sh.hammingDistance(h321, h322));
		System.out.println();
		
	}
	
	public static void test6(){
		MetaString ms1 = new MetaString(new File("D:\\temp/1.html")); 
		MetaString ms2 = new MetaString(new File("D:\\temp/2.html")); 
		
		
		Simhash sh = new Simhash();
		long h1 = sh.simhash64(ms1.chnsTokens());
		long h2 = sh.simhash64(ms2.chnsTokens());
		System.out.println("64位simhash中文分词大段替换");
		System.out.println(Long.toBinaryString(h1));
		System.out.println(Long.toBinaryString(h2));
		System.out.println("两者的海明距离：");
		System.out.println(Long.toBinaryString(h1^h2));
		System.out.println(sh.hammingDistance(h1, h2));
		System.out.println();
		
	}

}
