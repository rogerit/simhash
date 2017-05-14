package com.gpower.algorithm.simhash;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ansj.splitWord.analysis.ToAnalysis;

import com.google.gson.Gson;
import com.gpower.algorithm.simhash.MetaString.ChnsWordSeg;
import com.gpower.algorithm.simhash.MetaString.IWordSeg;
import com.gpower.algorithm.simhash.MetaString.MetaString;
import com.gpower.algorithm.simhash.hash.Simhash;
import com.gpower.algorithm.simhash.htmltexts.CriteriaModel;
import com.gpower.algorithm.simhash.htmltexts.HtmlText;
import com.gpower.algorithm.simhash.htmltexts.HtmlTextFactory;
import com.gpower.algorithm.simhash.htmltexts.JsoupSelection;
import com.gpower.algorithm.simhash.simhash.ISimHash;
import com.gpower.algorithm.simhash.simhash.SimHash32;
import com.gpower.algorithm.simhash.simhash.SimHash64;
import com.gpower.algorithm.simhash.simhash.SimHashSimple;

public class Main {

	public static final HtmlTextFactory htsf = HtmlTextFactory.getInstance();
	public static final Gson gson = new Gson();

	public static void main(String[] args) throws IOException {
		int len = 10;
		List<Integer> hash32s = new ArrayList<Integer>();
		List<Long> hash64s = new ArrayList<Long>();
		for (int i = 0; i < len; i++) {

			List<CriteriaModel> htmlTextInfo = htmlTextInfo(i + ".html");
			Map<Integer, Number> map = simhashValue(htmlTextInfo);
			hash32s.add((Integer) map.get(32));
			hash64s.add((Long) map.get(64));
		}
		System.out.println(hash32s);
		System.out.println(hash64s);

		System.out.println("32位simhash海明距离：");
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				int d32 = SimHashSimple.hammingDistance(hash32s.get(i),
						hash32s.get(j));
				System.out.print(d32 + "  ");
			}
			System.out.println();
		}
		
		System.out.println("64位simhash海明距离：");
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				int d64 = SimHashSimple.hammingDistance(hash64s.get(i),
						hash64s.get(j));
				System.out.print(d64 + "  ");
			}
			System.out.println();
		}
	}

	private static Map<Integer, Number> simhashValue(
			List<CriteriaModel> htmlTextInfo) {
		ISimHash<Integer> shc32 = new SimHash32();
		ISimHash<Long> shc64 = new SimHash64();
		ChnsWordSeg cws = new ChnsWordSeg();
		System.out.println();
		System.out.println("分词结果:");
		for (CriteriaModel cm : htmlTextInfo) {
			if (cm.getWeight() > 0 && cm.getText() != null) {
				List<String> tokens = cws.tokens(cm.getText());
				System.out.println(cm.getNote());
				System.out.println(tokens);
				System.out.println();
				shc32.gather(cm.getWeight(), tokens);
				shc64.gather(cm.getWeight(), tokens);
			}
		}
		Map<Integer, Number> map = new HashMap<Integer, Number>();
		map.put(32, shc32.hash());
		map.put(64, shc64.hash());
		return map;
	}

	public static List<CriteriaModel> htmlTextInfo(String file) {
		List<CriteriaModel> criterias = null;
		try {
			HtmlText ht = htsf.getHtmlText();
			InputStream is = Main.class.getResourceAsStream(file);
			String html = new MetaString(is).getText();
			ht.setOriginHtml(html);
			JsoupSelection.deployCriterias(ht);
			criterias = ht.getCriterias();
			for (CriteriaModel cm : criterias) {
				System.out.println(cm);
				System.out.println();
			}
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return criterias;

	}

	public static void test7() {
		String str = "欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!";
		System.out.println(ToAnalysis.parse(str));
	}

	public static void test1() {
		MetaString ms1 = new MetaString(new File("D:\\temp/1.html"));
		MetaString ms2 = new MetaString(new File("D:\\temp/3.html"));

		Simhash sh = new Simhash();
		int h321 = sh.simhash32(ms1.chnsTokens());
		int h322 = sh.simhash32(ms2.chnsTokens());
		System.out.println("32位simhash中文分词");
		System.out.println(Integer.toBinaryString(h321));
		System.out.println(Integer.toBinaryString(h322));
		System.out.println("两者的海明距离：");
		System.out.println(Integer.toBinaryString(h321 ^ h322));
		System.out.println(sh.hammingDistance(h321, h322));
		System.out.println();

	}

	public static void test2() {
		MetaString ms1 = new MetaString(new File("D:\\temp/1.html"));
		MetaString ms2 = new MetaString(new File("D:\\temp/3.html"));

		Simhash sh = new Simhash();
		long h1 = sh.simhash64(ms1.chnsTokens());
		long h2 = sh.simhash64(ms2.chnsTokens());
		System.out.println("64位simhash中文分词");
		System.out.println(Long.toBinaryString(h1));
		System.out.println(Long.toBinaryString(h2));
		System.out.println("两者的海明距离：");
		System.out.println(Long.toBinaryString(h1 ^ h2));
		System.out.println(sh.hammingDistance(h1, h2));
		System.out.println();

	}

	public static void test3() {
		MetaString ms1 = new MetaString(new File("D:\\temp/1.html"));
		MetaString ms2 = new MetaString(new File("D:\\temp/3.html"));

		Simhash sh = new Simhash();
		int h321 = sh.simhash32(ms1.neighborTokens());
		int h322 = sh.simhash32(ms2.neighborTokens());
		System.out.println("32位simhash相邻分词");
		System.out.println(Integer.toBinaryString(h321));
		System.out.println(Integer.toBinaryString(h322));
		System.out.println("两者的海明距离：");
		System.out.println(Integer.toBinaryString(h321 ^ h322));
		System.out.println(sh.hammingDistance(h321, h322));
		System.out.println();

	}

	public static void test4() {
		MetaString ms1 = new MetaString(new File("D:\\temp/1.html"));
		MetaString ms2 = new MetaString(new File("D:\\temp/3.html"));

		Simhash sh = new Simhash();
		long h1 = sh.simhash64(ms1.neighborTokens());
		long h2 = sh.simhash64(ms2.neighborTokens());
		System.out.println("64位simhash相邻分词");
		System.out.println(Long.toBinaryString(h1));
		System.out.println(Long.toBinaryString(h2));
		System.out.println("两者的海明距离：");
		System.out.println(Long.toBinaryString(h1 ^ h2));
		System.out.println(sh.hammingDistance(h1, h2));
		System.out.println();

	}

	public static void test5() {
		MetaString ms1 = new MetaString(new File("D:\\temp/1.html"));
		MetaString ms2 = new MetaString(new File("D:\\temp/2.html"));

		Simhash sh = new Simhash();
		int h321 = sh.simhash32(ms1.chnsTokens());
		int h322 = sh.simhash32(ms2.chnsTokens());
		System.out.println("32位simhash中文分词大段替换");
		System.out.println(Integer.toBinaryString(h321));
		System.out.println(Integer.toBinaryString(h322));
		System.out.println("两者的海明距离：");
		System.out.println(Integer.toBinaryString(h321 ^ h322));
		System.out.println(sh.hammingDistance(h321, h322));
		System.out.println();

	}

	public static void test6() {
		MetaString ms1 = new MetaString(new File("D:\\temp/1.html"));
		MetaString ms2 = new MetaString(new File("D:\\temp/2.html"));

		Simhash sh = new Simhash();
		long h1 = sh.simhash64(ms1.chnsTokens());
		long h2 = sh.simhash64(ms2.chnsTokens());
		System.out.println("64位simhash中文分词大段替换");
		System.out.println(Long.toBinaryString(h1));
		System.out.println(Long.toBinaryString(h2));
		System.out.println("两者的海明距离：");
		System.out.println(Long.toBinaryString(h1 ^ h2));
		System.out.println(sh.hammingDistance(h1, h2));
		System.out.println();

	}

}
