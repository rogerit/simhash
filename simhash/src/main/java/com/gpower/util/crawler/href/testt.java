package com.gpower.util.crawler.href;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class testt {

	public static void main(String[] args) throws MalformedURLException {
		try {
			String link = "http://hq.bucea.edu.cn/pftp/%20.";
			String es = URLEncoder.encode(link, "utf-8");
			String ds = URLDecoder.decode(link, "utf-8");
			System.out.println(es);
			System.out.println(ds);
			Connection connect = Jsoup.connect(ds);
			Document document = connect.get();
			System.out.println(document.title());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
