package com.gpower.util.crawler.href;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;

import com.gpower.algorithm.simhash.SimHash;

public class SimHashProcessor implements IHrefDocProcessor<String, Number> {

	private SimHash simHash = null;

	private Map<String, Number> hrefSimHash = null;

	public SimHashProcessor() {
		this.simHash = new SimHash();
		this.hrefSimHash = new HashMap<String, Number>();
	}

	public SimHashProcessor(String htmlTextSelection) {
		this.simHash = new SimHash(htmlTextSelection);
		this.hrefSimHash = new HashMap<String, Number>();
	}

	@Override
	public void process(Document doc, HrefMapModel hrefMapModel) {
		Long hash64 = this.simHash.Hash64(doc.toString());
		//if (hash64==0) System.out.println(doc.toString());
		if (hash64==0) System.err.println(hrefMapModel.getHref()+"的simhash值异常");
		this.hrefSimHash.put(hrefMapModel.getHref(), hash64);
	}

	@Override
	public Map<String, Number> gatheredMap() {
		return this.hrefSimHash;
	}

}
