package com.gpower.util.crawler.href;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


@SuppressWarnings("rawtypes")
public class HrefMapModel {

	public static final int PROCESS_ERR = 2; // error;
	public static final int PROCESS_ED = 1; // has been processED;
	public static final int PROCESS_EL = 1001; // extern link
												// IGnored;
	public static final int PROCESS_TP = -1; // To Process;

	private Map<String, HrefMapModel> childrenHrefMap = null;
	private List<IHrefDocProcessor> hrefDocProcessor = null;
	private String href = null;
	private int status = PROCESS_TP;


	public String getHref() {
		return href;
	}

	public Map<String, HrefMapModel> getChildrenHrefMap() {
		return childrenHrefMap;
	}

	public void setChildrenHrefMap(Map<String, HrefMapModel> childrenHrefMap) {
		this.childrenHrefMap = childrenHrefMap;
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
			doc = Jsoup.connect(this.href).get();
			System.out.println(this.href);

			for (IHrefDocProcessor hdp : this.hrefDocProcessor) {
				try {
					hdp.process(doc, this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			this.setStatus(PROCESS_ED);
		} catch (IOException e1) {
			this.setStatus(HrefMapModel.PROCESS_ERR);
		}
	}

	public void deepFirst(HrefMapModel hrefMapModel) {
		// stop to avoid circle grabing href!
		if (hrefMapModel.getStatus() != HrefMapModel.PROCESS_TP) {
			return;
		}

		hrefMapModel.hrefDocProcess();
		if (hrefMapModel.getChildrenHrefMap() != null) {
			for (HrefMapModel h : hrefMapModel.getChildrenHrefMap().values()) {
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
				"http://dzb.bucea.edu.cn/");
		List<String> sensitiveWords = new ArrayList<String>();
		sensitiveWords.add("基本标准");
		sensitiveWords.add("高等教育研究室");
		sensitiveWords.add("安全管理");
		WordsProcessor sensitiveWordsProcessor = new WordsProcessor(sensitiveWords);
		hmmb.getHrefDocProcessor().add(sensitiveWordsProcessor);
		
		
		
		
		
		
		
		HrefMapModel hmm = hmmb.build("http://dzb.bucea.edu.cn/");
		hmm.deepFirst(hmm);
		IHrefDocProcessor<String, HrefMapModel> iHrefDocProcessor = hmmb.getHrefDocProcessor().get(0);
		Map<String, HrefMapModel> encounteredHref = iHrefDocProcessor.gatheredMap();
		System.out.println(encounteredHref.size());
	}

}
