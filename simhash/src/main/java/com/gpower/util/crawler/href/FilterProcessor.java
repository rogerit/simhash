package com.gpower.util.crawler.href;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FilterProcessor implements IHrefDocProcessor<String, String> {

	private Map<String, String> hrefFilteredNodes = null;

	public FilterProcessor() {
		this.hrefFilteredNodes = new HashMap<String, String>();
	}

	@Override
	public void process(Document doc, HrefMapModel hrefMapModel) {
		Elements as = doc.getElementsByTag("a");
		for (Element a : as) {
			String absHref = a.attr("abs:href");
			if (absHref.contains(".doc") || absHref.contains(".jpg")
					|| absHref.contains(".rar") || absHref.contains(".pdf")
					|| absHref.contains("zip")||absHref.contains(".xls")) {
				a.remove();
				this.hrefFilteredNodes.put(absHref, null);
			} 
		}
	}

	@Override
	public Map<String, String> gatheredMap() {
		return this.hrefFilteredNodes;
	}

}
