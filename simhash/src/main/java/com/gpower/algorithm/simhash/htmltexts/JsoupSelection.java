package com.gpower.algorithm.simhash.htmltexts;

import java.io.File;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.gpower.algorithm.simhash.modifystring.ChnsWordSeg;
import com.gpower.algorithm.simhash.modifystring.IWordSeg;
import com.gpower.algorithm.simhash.modifystring.MetaString;

public class JsoupSelection {

	/*
	 * condition chains for select return
	 * es.select(cond1).select(cond2).....select(condn);
	 */
	public static Elements selectElements(Elements es, String cond) {
		if (cond != null && cond.length() > 0) {
			String[] subconds = cond.split(";");
			for (String s : subconds) {
				es = es.select(s);
			}
		}
		return es;
	}

	public static HtmlText deployCriterias(HtmlText hts) {

		CriteriaModel cmerror = null;
		if (hts.getOriginHtml() == null) {
			System.err.println("com.gpower.algorithm.simhash.htmltexts."
					+ "JsoupSelection.deployCriterias(HtmlTextSelection hts)"
					+ " hts has no originhtml.");
			return hts;
		}
		try {
			Document doc = Jsoup.parse(hts.getOriginHtml());

			for (CriteriaModel cm : hts.getCriterias()) {
				cmerror = cm;
				Elements htmlnodes = doc.getElementsByTag("html");
				if (htmlnodes.size() > 0) {
					Element element = htmlnodes.get(0);
					Elements allElements = element.getAllElements();
					Elements ses = selectElements(allElements, cm.getCriteria());
					ses.remove();
					cm.setHtml(ses.toString());
					cm.setText(ses.text());

				} else {
					System.out.println("请检查html节点是否已被删除");
				}
			}
		} catch (Exception e) {
			// todo: define an exption class and throw
			if (cmerror != null) {
				cmerror.setError(e.getMessage());
			}
			// e.printStackTrace();
		}
		return hts;
	}

	public static String selectedText(HtmlText hts) {
		String text = new String("");
		for (CriteriaModel cm : hts.getCriterias()) {
			if (cm.getError() == null && cm.getText() != null) {
				for (int i = 0; i < cm.getWeight(); i++) {
					text = text + "\n" + cm.getText();
				}
			}
		}
		System.out.println(1 + text);
		return text;
	}

	public static void main(String[] args) {

		String html = new MetaString(new File("d:/temp/1.html")).getText();
		String html2 = new MetaString(new File("d:/temp/2.html")).getText();

		// json 提供测试用例的html配置文件
		String json = new MetaString(new File("conf/htmlTextSelection.json"))
				.getText();
		Gson gson = new Gson();
		HtmlText hts = gson.fromJson(json, HtmlText.class);

		// 设置筛选条件，
		// Document doc = Jsoup.parse(html);

		hts.setOriginHtml(html);
		deployCriterias(hts);
		System.out.println(gson.toJson(hts.getCriterias()));

		hts.setOriginHtml(html2);
		deployCriterias(hts);
		System.out.println(gson.toJson(hts.getCriterias()));
		String text = selectedText(hts);
		IWordSeg bw = new ChnsWordSeg();
		List<String> tokens = bw.tokens(text);
		System.out.println(gson.toJson(tokens));
	}
}
