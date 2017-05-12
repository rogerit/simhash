package com.gpower.algorithm.simhash.simhash;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.gpower.algorithm.simhash.MetaString.ChnsWordSeg;
import com.gpower.algorithm.simhash.MetaString.IWordSeg;
import com.gpower.algorithm.simhash.MetaString.MetaString;
import com.gpower.algorithm.simhash.hash.Simhash;
import com.gpower.algorithm.simhash.htmltexts.CriteriaModel;
import com.gpower.algorithm.simhash.htmltexts.HtmlTextSelection;
import com.gpower.algorithm.simhash.htmltexts.HtmlTextSelectionFactory;
import com.gpower.algorithm.simhash.htmltexts.JsoupSelection;

public class Test {
	public static void main(String[] args) throws IOException {
		HtmlTextSelectionFactory htsf = HtmlTextSelectionFactory.getInstance();
		InputStream is = ISimHash.class.getResourceAsStream("1.html");
		
		String html = new MetaString(is).getText();
		is.close();
		
		HtmlTextSelection hts = htsf.getHtmlTextSelection();
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
		System.out.println(shc.hash());

		Simhash sm = new Simhash();
		//System.out.println(sm.simhash64(tokenstest));
		System.out.println(sm.simhash32(tokenstest));

	}
}
