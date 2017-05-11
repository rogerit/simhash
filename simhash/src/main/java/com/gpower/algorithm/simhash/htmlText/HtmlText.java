package com.gpower.algorithm.simhash.htmlText;

import java.util.ArrayList;
import java.util.List;

public class HtmlText {
	private String baseText = null;

	private List<EmphasiModel> concernTexts = null;

	public String getBaseText() {
		return baseText;
	}

	public void setBaseText(String baseText) {
		this.baseText = baseText;
	}

	public List<EmphasiModel> getConcernTexts() {
		return concernTexts;
	}

	public void setConcernTexts(List<EmphasiModel> concernTexts) {
		this.concernTexts = concernTexts;
	}

	@Override
	public String toString() {
		return "HtmlText [baseText=" + baseText + "\n"+ ", concernTexts="
				+ concernTexts + "]";
	}
	
	
	
}

