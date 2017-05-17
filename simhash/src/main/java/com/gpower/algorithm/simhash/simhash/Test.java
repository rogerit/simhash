package com.gpower.algorithm.simhash.simhash;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.gpower.algorithm.simhash.htmltexts.CriteriaModel;
import com.gpower.algorithm.simhash.htmltexts.HtmlText;
import com.gpower.algorithm.simhash.htmltexts.HtmlTextFactory;
import com.gpower.algorithm.simhash.htmltexts.JsoupSelection;
import com.gpower.algorithm.simhash.modifystring.ChnsWordSeg;
import com.gpower.algorithm.simhash.modifystring.IWordSeg;
import com.gpower.algorithm.simhash.modifystring.MetaString;

public class Test {
	public static void main(String[] args) throws IOException {
		HtmlTextFactory htsf = HtmlTextFactory.getInstance();
		InputStream is = ISimHash.class.getResourceAsStream("1.html");
		//InputStream is = new FileInputStream("D:/1.html");
		
		String html = new MetaString(is).getText();
		is.close();
		
		HtmlText hts = htsf.getHtmlText();
		hts.setOriginHtml(html);
		JsoupSelection.deployCriterias(hts);

		IWordSeg ws = new ChnsWordSeg();
		List<String> tokens = null;
		List<String> tokenstest = new ArrayList<String>();

		//ISimHash shc = new SimHash64();
		ISimHash<Integer> shc = new SimHash32();

		for (CriteriaModel cm : hts.getCriterias()) {
			String t = cm.getText();
			int w = cm.getWeight();
			if (t != null) {
				tokens = ws.tokens(t);
				System.out.println(tokens);
				for (int i = 0; i < w; i++) {
					tokenstest.addAll(tokens);
				}
				shc.gather(w, tokens);
			}
		}
		System.out.println(new Gson().toJson(hts.getCriterias()));
		System.out.println(shc.hash());


	}
}
