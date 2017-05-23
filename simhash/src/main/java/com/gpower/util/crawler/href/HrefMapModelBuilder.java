package com.gpower.util.crawler.href;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class HrefMapModelBuilder {

	private List<IHrefDocProcessor> hrefDocProcessor = null;


	
	public List<IHrefDocProcessor> getHrefDocProcessor() {
		return hrefDocProcessor;
	}
	


	public HrefMapModelBuilder(String basePath) {

		
		/*
		 * process children href:
		 * 
		 * @para: builder Use a HMM builder to builds all of the children HMM;
		 * 
		 * @basePath: Filter the HMM that not start with basePath;
		 */
		this.hrefDocProcessor = new ArrayList<IHrefDocProcessor>();
		
		ChildrenHrefProcessor childrenHrefProcessor = new ChildrenHrefProcessor(
				basePath, this);
		
		this.hrefDocProcessor.add(childrenHrefProcessor);
		
		

		// to add more hrefDocProcessor
	}

	public HrefMapModel build(String href) {

		HrefMapModel hmm = new HrefMapModel(href);
		hmm.setHrefDocProcessor(this.hrefDocProcessor);
		return hmm;

	}

}
