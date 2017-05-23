package com.gpower.util.crawler.href;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;

public class WordsProcessor implements IHrefDocProcessor<String, String> {

	// <url, word(s) in words> urls whose doc contains some of words.
	private Map<String, String> wordsUrl = null;

	// words to be checked in doc.
	private List<String> words = null;

	public WordsProcessor(List<String> words) {
		this.words = words;
		this.wordsUrl = new HashMap<String, String>();
	}

	@Override
	public void process(Document doc, HrefMapModel hrefMapModel) {
		for (String str : words) {
			if (doc.text().contains(str)) {
				String s = "";
				if (this.wordsUrl.containsKey(hrefMapModel.getHref())) {
					s = this.wordsUrl.get(hrefMapModel.getHref()) + ",";
				}
				this.wordsUrl.put(hrefMapModel.getHref(), s + str);
			}
		}
		if (wordsUrl.containsKey(hrefMapModel.getHref())) {
			System.out.println("000000000000~~~~~~" + wordsUrl.get(hrefMapModel.getHref()));
		}
	}

	@Override
	public Map<String, String> gatheredMap() {
		return null;
	}

}
