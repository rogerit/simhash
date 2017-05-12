package com.gpower.algorithm.simhash.htmltexts;

import java.io.IOException;
import java.io.InputStream;

import com.google.gson.Gson;
import com.gpower.algorithm.simhash.MetaString.MetaString;

public class HtmlTextSelectionFactory {
	private static class SingletonHolder {
		private static final HtmlTextSelectionFactory INSTANCE = new HtmlTextSelectionFactory();
	}

	private HtmlTextSelectionFactory() {
	}

	public static final HtmlTextSelectionFactory getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public HtmlTextSelection getHtmlTextSelection() {
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
		return gson.fromJson(json, HtmlTextSelection.class);
	}
}
