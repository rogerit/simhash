package com.gpower.algorithm.simhash.htmltexts;

import java.io.IOException;
import java.io.InputStream;

import com.google.gson.Gson;
import com.gpower.algorithm.simhash.MetaString.MetaString;

public class HtmlTextFactory {
	private static class SingletonHolder {
		private static final HtmlTextFactory INSTANCE = new HtmlTextFactory();
	}

	private HtmlTextFactory() {
	}

	public static final HtmlTextFactory getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public HtmlText getHtmlText() {
		InputStream is = null;
		String json =null;
		Gson gson = new Gson();
		try {
			is = this.getClass().getResourceAsStream("htmlTextSelection.json");
			json = new MetaString(is).getText();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return gson.fromJson(json, HtmlText.class);
	}
}
