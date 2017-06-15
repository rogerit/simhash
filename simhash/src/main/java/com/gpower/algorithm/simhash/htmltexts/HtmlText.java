package com.gpower.algorithm.simhash.htmltexts;

import java.util.List;


public class HtmlText {
	// private List<CriteriaModel> criterias = null;
	private String originHtml = null;
	private List<CriteriaModel> criterias = null;

	@Override
	public String toString() {
		return "HtmlTextSelection []";
	}

	public String getOriginHtml() {
		return originHtml;
	}

	public void setOriginHtml(String originHtml) {
		this.originHtml = originHtml;
	}

	public List<CriteriaModel> getCriterias() {
		return criterias;
	}

	public void setCriterias(List<CriteriaModel> criterias) {
		this.criterias = criterias;
	}

	

}
