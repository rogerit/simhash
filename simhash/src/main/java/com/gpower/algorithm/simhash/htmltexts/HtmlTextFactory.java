package com.gpower.algorithm.simhash.htmltexts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.google.gson.Gson;
import com.gpower.algorithm.simhash.modifystring.MetaString;

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
		return this.getHtmlText("htmlTextSelection.json");
	}

	public HtmlText getHtmlText(String configFile) {
		InputStream configStream = null;
		//get config file
		// from absolute path
		File file = new File(configFile);
		if (!file.exists()) {
			String path = this.getClass().getClassLoader().getResource("")
					.getPath();
			// if not abs path then local resources path
			file = new File(path + configFile);
		}
		
		//get input stream 
		if (file.exists()) {
			// if file exist, get file inputstream
			try {
				configStream = new FileInputStream(file);
			} catch (FileNotFoundException e) {
			}
		}else{
			// local jar inputstream
			configStream = this.getClass().getResourceAsStream(
					"htmlTextSelection.json");
		}
		String json = null;
		Gson gson = new Gson();
		json = new MetaString(configStream).getText();
		return gson.fromJson(json, HtmlText.class);
	}
}
