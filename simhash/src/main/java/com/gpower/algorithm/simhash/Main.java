package com.gpower.algorithm.simhash;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;




import org.ansj.splitWord.analysis.ToAnalysis;

import com.gpower.algorithm.simhash.MetaString.MetaString;
import com.gpower.algorithm.simhash.hash.Simhash;

public class Main {
	
	public static void main(String[] args) throws IOException {

		
		test1();
		test2();
		
		
	}
	
	public static void test7(){
		 String str = "欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!" ;
		 System.out.println(ToAnalysis.parse(str));
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
