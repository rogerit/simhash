/*package com.gpower.platform.saronclient.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class SiteHealthUtil {
	private Map<String, Integer> allUrl = new HashMap<String, Integer>();
	// roger
	private Map<String, String> sensitiveUrl = new HashMap<String, String>();
	private Map<String, String> notFoundUrl = new HashMap<String, String>();
	private Map<String, String> dangerUrl = new HashMap<String, String>();
	private List<SensitiveWord> wordList = null;

	*//**
	 * @author zhuyy 下面为新增的 关键词以及包含关键词的链接
	 *//*
	private List<ImpWord> impwordList = null;
	
	private Map<String, String> impwordUrl = new HashMap<String, String>();
	*//**
	 * 判断是否含有设置的风险挂马链接
	 * 
	 *//*
	private List<TrojanUrl> trojanurlList = null;
	private boolean isTrojan = false;

	

	public void doUrl(String url, String baseUrl) throws Exception {

		String content = null;
		content = this.getUrlHtml(url);
		
		Parser parser = Parser.createParser(content, "UTF-8");
		NodeList list = parser.extractAllNodesThatMatch(new TagNameFilter("a"));
		for (int i = 0; i < list.size(); i++) {
			LinkTag node = (LinkTag) list.elementAt(i);
			String link = node.getLink();
			
			if (link != null && !"".equals(link) && !"#".equals(link)
					&& !";".equals(link)) {


				if (!this.getAllUrl().containsKey(link)) {
					if (link.startsWith(this.getDomain())) {
						this.getAllUrl().put(link, null);

						// roger
						this.getAllUrlSimHash().put(link, null);
					} else {
						if (this.getDangerUrl().entrySet().contains(link)) {
							String val = this.getDangerUrl().get(link)
									.concat("," + url);
							this.getDangerUrl().put(link, val);

						} else {
							this.getDangerUrl().put(link, url);
						}

					}
					this.doUrl(link, url);
				} else {
					if (this.getNotFoundUrl().entrySet().contains(link)) {
						String val = this.getNotFoundUrl().get(link)
								.concat("," + url);
						this.getNotFoundUrl().put(link, val);

					}
				}
			}
		}

	}

	public String getUrlHtml(String htmlurl) throws Exception {
		URL url;

		
		String result = new String(in2b, encode);

		this.getAllUrl().put(htmlurl, result.hashCode());

		
		// this.getAllUrlSimHash().put(key, value);
		// 查找敏感词的链接
		for (int i = 0, len = this.getWordList() == null ? 0 : this
				.getWordList().size(); i < len; i++) {
			SensitiveWord word = this.getWordList().get(i);
			if (result.contains(word.getName())) {
				if (this.getSensitiveUrl().containsKey(htmlurl)) {
					String val = this.getSensitiveUrl().get(htmlurl)
							.concat("," + word.getName());
					this.getSensitiveUrl().put(htmlurl, val);
				} else {
					this.getSensitiveUrl().put(htmlurl, word.getName());
				}

			}
		}
		// 查找包含关键词的链接
		for (int i = 0, len = this.getImpwordList() == null ? 0 : this
				.getImpwordList().size(); i < len; i++) {
			ImpWord word = this.getImpwordList().get(i);
			if (result.contains(word.getName())) {
				if (this.getImpwordUrl().containsKey(htmlurl)) {
					String val = this.getImpwordUrl().get(htmlurl)
							.concat("," + word.getName());
					this.getImpwordUrl().put(htmlurl, val);
				} else {
					this.getImpwordUrl().put(htmlurl, word.getName());
				}

			}
		}
		// 查看是否包含危险链接
		for (int i = 0, len = this.getTrojanurlList() == null ? 0 : this
				.getTrojanurlList().size(); i < len; i++) {
			TrojanUrl trojanUrl = this.getTrojanurlList().get(i);
			if (result.contains(trojanUrl.getName())) {
				this.setTrojan(true);
				continue;
			}
		}
		return result;

	}

*/