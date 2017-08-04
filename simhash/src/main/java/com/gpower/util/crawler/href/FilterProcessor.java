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
			/*
			 * 不需要爬取的连接类型 .ppt .pptx .JPG .bmp .wmv .doc .jpeg .wps .dot
			 * .download .png .psd .dwg
			 */
			if (absHref == "" || absHref.contains("@")
					|| absHref.contains("mailto") || absHref.contains("href=")
					|| absHref.contains("void(0)")
					|| absHref.contains("window.print()")
					|| absHref.contains("#") || absHref.contains(";")) {
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
