package com.gpower.util.crawler.href;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerProcessor implements
		IHrefDocProcessor<String, HrefMapModel> {

	private String basePath = null;

	private HrefMapModelBuilder hrefMapModelBuilder = null;

	private Map<String, HrefMapModel> encounteredHref = null;

	public CrawlerProcessor() {
	}

	/*
	 * process children href:
	 * 
	 * @para: builder Use a HMM builder to builds all of the children HMM;
	 * 
	 * @basePath: Filter the HMM that not start with basePath;
	 */
	public CrawlerProcessor(String basePath,
			HrefMapModelBuilder hrefMapModelBuilder) {
		this.basePath = basePath;
		this.hrefMapModelBuilder = hrefMapModelBuilder;
		this.encounteredHref = new HashMap<String, HrefMapModel>();
	}

	@Override
	public void process(Document doc, HrefMapModel hrefMapModel) {
		Elements es = doc.getElementsByTag("a");

		// this only use to put the root href into encounteredHref
		if (!this.encounteredHref.containsKey(hrefMapModel.getHref())) {
			this.encounteredHref.put(hrefMapModel.getHref(), hrefMapModel);
		}

		hrefMapModel.setChildrenHrefSet(new HashSet<HrefMapModel>());

		for (Element e : es) {
			String absHref = e.attr("abs:href");
			//absHref = absHref.replace("#", "");
			if (!this.encounteredHref.containsKey(absHref)) {

				HrefMapModel thfm = this.hrefMapModelBuilder.build(absHref);
				// if the absHref is an external links then set the PROCESS flag
				// to PROCESS_IG
				if (!absHref.startsWith(this.basePath)) {
					thfm.setStatus(HrefMapModel.PROCESS_EL);
					System.out.println("external: " + absHref + "~");
				}
				this.encounteredHref.put(absHref, thfm);
			}

			hrefMapModel.getChildrenHrefSet().add(
					this.encounteredHref.get(absHref));
		}
		// System.out.println(this.visitedHref.size());
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public HrefMapModelBuilder getHrefMapModelBuilder() {
		return hrefMapModelBuilder;
	}

	public void setHrefMapModelBuilder(HrefMapModelBuilder hrefMapModelBuilder) {
		this.hrefMapModelBuilder = hrefMapModelBuilder;
	}

	@Override
	public Map<String, HrefMapModel> gatheredMap() {

		return encounteredHref;
	}

}
