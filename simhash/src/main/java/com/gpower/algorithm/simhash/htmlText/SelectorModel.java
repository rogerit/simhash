package com.gpower.algorithm.simhash.htmlText;

import java.util.List;

/*
 * html tags selector:
 * 		abandon<[str]>: text to be removed;
 * 		concern<[emphasize]>:
 * 			level<num>: importance of text
 * 			tags<[str]>: text concerned
 */
public class SelectorModel {

	// text in tags will be remove
	private List<String> abandon = null;

	private List<EmphasiModel> concern = null;
	

	public List<String> getAbandon() {
		return abandon;
	}

	public void setAbandon(List<String> abandon) {
		this.abandon = abandon;
	}

	public List<EmphasiModel> getConcern() {
		return concern;
	}

	public void setConcern(List<EmphasiModel> concern) {
		this.concern = concern;
	}

}


