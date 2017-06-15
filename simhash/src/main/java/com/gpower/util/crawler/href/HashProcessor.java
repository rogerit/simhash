package com.gpower.util.crawler.href;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;

public class HashProcessor implements IHrefDocProcessor<String, Integer> {

	private Map<String, Integer> hrefHash = null;

	public HashProcessor() {
		this.hrefHash = new HashMap<String, Integer>();
	}

	@Override
	public void process(Document doc, HrefMapModel hrefMapModel) {
		Integer hash = doc.toString().hashCode();
		this.hrefHash.put(hrefMapModel.getHref(), hash);
		System.out.println(hrefMapModel.getHref()+"      hassssssssssh");
		System.out.println(hash);
	}

	@Override
	public Map<String, Integer> gatheredMap() {
		return this.hrefHash;
	}

}
