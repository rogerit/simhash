package com.gpower.algorithm.simhash.htmlText;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.gpower.algorithm.simhash.MetaString.MetaString;

public class JsoupSelection {

	public static void docTagsAbandon(Elements alleles, List<String> abandons) {
		if (abandons != null && abandons.size() > 0) {
			for (String cond : abandons) {
				Elements se = selectElements(alleles, cond).remove();
			}
		}
	}

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

	public static List<EmphasiModel> docTagsEmphasi(Elements alleles,
			List<EmphasiModel> emphs) {

		if (emphs != null && emphs.size() > 0) {
			for (EmphasiModel em : emphs) {
				// remove from the dom nodes trees, and get the text
				em.setText(selectElements(alleles, em.getText()).remove()
						.text());
			}
		}
		// EmphasiModel emphs is useless anymore
		// So emphs's roles converted! used as htmltext's concernTexts
		return emphs;
	}

	public static HtmlText docTagsSelection(Document doc, SelectorModel sm) {
		HtmlText htmlText = new HtmlText();
		Elements alleles = doc.getAllElements();
		//Elements alleles = doc.getElementsByTag("html");
	

		// get concern condition
		List<EmphasiModel> condition = sm.getConcern();

		// get concern nodes text and remove nodes logically from doc
		List<EmphasiModel> concernTexts = docTagsEmphasi(alleles, condition);
		htmlText.setConcernTexts(concernTexts);

		List<String> abandon = sm.getAbandon();
		// remove nodes abandoned logically from doc
		docTagsAbandon(alleles, abandon);

		// get the remaining text
		htmlText.setBaseText(doc.text());

		return htmlText;
	}

	public static void main(String[] args) {

		MetaString ms = new MetaString(new File("d:/temp/1.html"));
		String json = new MetaString(new File("d:/temp/selectormodel.json")).getText();
		Document doc = Jsoup.parse(ms.getText());
		/*System.out.println(doc.getAllElements().select(":not(:has(p))").remove().html());
		System.out.println(doc.getAllElements().select(":not(:has(p))").remove().html());*/

		Gson gson = new Gson();
		SelectorModel sm = gson.fromJson(json, SelectorModel.class);
		

		HtmlText dts = docTagsSelection(doc, sm);

		List<EmphasiModel> cts = dts.getConcernTexts();
		System.out.println(dts);

	}
}
