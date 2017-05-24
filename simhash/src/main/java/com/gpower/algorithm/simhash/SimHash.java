package com.gpower.algorithm.simhash;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import com.gpower.algorithm.simhash.htmltexts.CriteriaModel;
import com.gpower.algorithm.simhash.htmltexts.HtmlText;
import com.gpower.algorithm.simhash.htmltexts.HtmlTextFactory;
import com.gpower.algorithm.simhash.htmltexts.JsoupSelection;
import com.gpower.algorithm.simhash.modifystring.ChnsWordSeg;
import com.gpower.algorithm.simhash.simhash.ISimHash;
import com.gpower.algorithm.simhash.simhash.SimHash64;
import com.gpower.algorithm.simhash.simhash.SimHashSimple;

public class SimHash {
	private HtmlText htmlText = null;

	public void setHtmlText(HtmlText htmlText) {
		this.htmlText = htmlText;
	}
	
	public SimHash() {
		this.htmlText = HtmlTextFactory.getInstance()
				.getHtmlText();
	}
	
	public SimHash(String configfile) {
		this.htmlText = HtmlTextFactory.getInstance()
				.getHtmlText(configfile);
	}

	public Long Hash64(String html) {
		// remove tags\scripts\spacing from html according to criteria.
		this.htmlText.setOriginHtml(html);
		this.htmlText = JsoupSelection.deployCriterias(htmlText);
		// compute hashcode
		ISimHash<Long> sh64 = new SimHash64();
		ChnsWordSeg cws = new ChnsWordSeg();
		for (CriteriaModel cm : htmlText.getCriterias()) {
			if (cm.getWeight() > 0 && cm.getText() != null) {
				List<String> tokens = cws.tokens(cm.getText());
				sh64.gather(cm.getWeight(), tokens);
			}
		}
		return sh64.hash();
	}

	public Integer hamingDistance(Long h1, Long h2) {
		return SimHashSimple.hammingDistance(h1, h2);
	}

	public static void main(String[] args) throws MalformedURLException {
		String conf = "htmlTextSelection.json";
		HtmlText ht = HtmlTextFactory.getInstance().getHtmlText();
		if (ht != null) {
			System.out.println(ht.getCriterias());
		}
	}

}
