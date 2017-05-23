package com.gpower.util.crawler.href;
import java.util.Map;

import org.jsoup.nodes.Document;
public interface IHrefDocProcessor<K,V> {
	
	public void process(Document doc,HrefMapModel hrefMapModel);
	
	public Map<K, V> gatheredMap();
}
