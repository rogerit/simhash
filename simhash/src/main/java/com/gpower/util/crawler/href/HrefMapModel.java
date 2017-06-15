package com.gpower.util.crawler.href;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@SuppressWarnings("rawtypes")
public class HrefMapModel {

	public static final int PROCESS_ERR = 2; // error;
	public static final int PROCESS_ED = 1; // has been processED;
	public static final int PROCESS_EL = 1001; // extern link
												// IGnored;
	public static final int PROCESS_TP = -1; // To Process;

	private Set<HrefMapModel> childrenHrefSet = null;

	public Set<HrefMapModel> getChildrenHrefSet() {
		return childrenHrefSet;
	}

	public void setChildrenHrefSet(Set<HrefMapModel> childrenHrefSet) {
		this.childrenHrefSet = childrenHrefSet;
	}

	private List<IHrefDocProcessor> hrefDocProcessor = null;
	private String href = null;
	private int status = PROCESS_TP;

	public String getHref() {
		return href;
	}

	public void mountChilrenHref(String href, HrefMapModel hrefMapModel) {

	}

	public List<IHrefDocProcessor> getHrefDocProcessor() {
		return hrefDocProcessor;
	}

	public void setHrefDocProcessor(List<IHrefDocProcessor> hrefDocProcessor) {
		this.hrefDocProcessor = hrefDocProcessor;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public HrefMapModel(String href) {
		super();
		this.href = href;
	}

	public HrefMapModel() {
	}

	public void hrefDocProcess() {
		Document doc = null;

		if (this.getStatus() != HrefMapModel.PROCESS_TP) {
			return;
		}

		try {
			System.out.println(this.href);
			doc = Jsoup.connect(this.href).get();

			for (IHrefDocProcessor hdp : this.hrefDocProcessor) {
				try {
					hdp.process(doc, this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			this.setStatus(PROCESS_ED);
		} catch (Exception e1) {
			// it's Exception, not IOException!!!. to catch any none IOException
			// throw by Jsoup.
			this.setStatus(HrefMapModel.PROCESS_ERR);
			System.err.println(this.href);
		}
	}

	public void deepFirst(HrefMapModel hrefMapModel) {
		// stop to avoid circle grabing href!
		if (hrefMapModel.getStatus() != HrefMapModel.PROCESS_TP) {
			return;
		}

		hrefMapModel.hrefDocProcess();
		if (hrefMapModel.getChildrenHrefSet() != null) {
			for (HrefMapModel h : hrefMapModel.getChildrenHrefSet()) {
				deepFirst(h);
			}
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public static void main(String[] args) {

		HrefMapModelBuilder hmmb = new HrefMapModelBuilder(
				"http://www.gpowersoft.com/");
/*		HrefMapModelBuilder hmmb = new HrefMapModelBuilder(
				"http://www.cueb.edu.cn/");
*/
		/*
		 * HrefMapModelBuilder hmmb = new HrefMapModelBuilder(
		 * "http://www.bjfsh.gov.cn/");
		 */
		/*
		 * HrefMapModelBuilder hmmb = new HrefMapModelBuilder(
		 * "http://dzb.bucea.edu.cn/");
		 */
		// add sensitive words processor
		List<String> sensitiveWords = new ArrayList<String>();
		sensitiveWords.add("基本标准");
		sensitiveWords.add("高等教育研究室");
		sensitiveWords.add("安全管理");
		WordsProcessor sensitiveWordsProcessor = new WordsProcessor(
				sensitiveWords);
		hmmb.getHrefDocProcessor().add(sensitiveWordsProcessor);

		// simhash processor
		SimHashProcessor simpcr = new SimHashProcessor();
		hmmb.getHrefDocProcessor().add(simpcr);
		
		// hash processor 
		HashProcessor hashpcr = new HashProcessor();
		hmmb.getHrefDocProcessor().add(hashpcr);

		// .doc .xlsx filter processor
		FilterProcessor ftrpcr = new FilterProcessor();
		hmmb.getHrefDocProcessor().add(0, ftrpcr);

		// build instance of class HrefMapModel
		HrefMapModel hmm = hmmb.build("http://www.gpowersoft.com/");
		//HrefMapModel hmm = hmmb.build("http://www.cueb.edu.cn/");
		// HrefMapModel hmm = hmmb.build("http://dzb.bucea.edu.cn/");
		// HrefMapModel hmm = hmmb.build("http://www.bjfsh.gov.cn/");

		// start crawler
		hmm.deepFirst(hmm);

		IHrefDocProcessor<String, HrefMapModel> iHrefDocProcessor = hmmb
				.getHrefDocProcessor().get(1);
		Map<String, HrefMapModel> encounteredHref = iHrefDocProcessor
				.gatheredMap();
		System.out.println(encounteredHref.size());
		System.out.println(ftrpcr.gatheredMap().size());
		System.out.println(simpcr.gatheredMap().size());

		Map<String, String> dangerURL = new HashMap<String, String>();

		// 遍历encounteredHref
		for (HrefMapModel hparent : encounteredHref.values()) {
			// 查看子href
			if (hparent.getChildrenHrefSet() == null)
				continue;
			for (HrefMapModel childhmm : hparent.getChildrenHrefSet()) {
				// 是否是外链
				if (childhmm.status == HrefMapModel.PROCESS_ERR) {
					String str = "";
					if (dangerURL.containsKey(childhmm.getHref())) {
						// 获取dangerURL的值
						str = dangerURL.get(childhmm.getHref()) + ",";
					}
					// 将父hparent追加至dangerURL的值中
					str = str + hparent.getHref();
					dangerURL.put(childhmm.getHref(), str);
				}
			}
		}

		for (Map.Entry<String, String> m : dangerURL.entrySet()) {
			System.out.println("!!!!!!!!!!!!!!!!");
			System.out.println(m.getKey() + "~~" + m.getValue());
			System.out.println("!!!!!!!!!!!!!!!!");
		}

	}
}
