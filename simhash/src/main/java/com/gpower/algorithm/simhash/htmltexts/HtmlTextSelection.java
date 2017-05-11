package com.gpower.algorithm.simhash.htmltexts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.gpower.algorithm.simhash.MetaString.MetaString;
import com.gpower.algorithm.simhash.htmlText.EmphasiModel;

public class HtmlTextSelection {
	// private List<CriteriaModel> criterias = null;
	private String originHtml = null;
	private List<CriteriaModel> criterias = null;


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
