package com.gpower.algorithm.simhash.modifystring;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.stream.StreamFilter;

import org.jsoup.Jsoup;

import com.gpower.algorithm.simhash.modifystring.EncodingDetect;

public class MetaString {

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public MetaString(String str){
		this.text = str;
	}
	
	public MetaString(InputStream inputStream ){
		String str = stream2String(inputStream);
		this.setText(str);
	}
	
	public MetaString(File file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			String str = stream2String(fis);
			this.setText(str);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (fis != null){
					fis.close();
				}
			} catch (IOException e) {
			}
		}

	}

	public MetaString(URL url) {
		HttpURLConnection httpUrlConn = null;
		InputStream inputStream = null;
		try {
			httpUrlConn = (HttpURLConnection) url
					.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");

			inputStream = httpUrlConn.getInputStream();
			String str = stream2String(inputStream);
			this.setText(str);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {}
			}
			if (httpUrlConn!=null){
				httpUrlConn.disconnect();
			}
		}
	}

	
	private String stream2String(InputStream instreams) {
		try {
			int length = instreams.available();
			byte[] buffer = new byte[length];

			instreams.read(buffer);

			String encode = EncodingDetect.getJavaEncode(buffer);

			return new String(buffer, encode);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String filteredText(){
		//return HtmlTagFilter.html2Text(this.getText());
		return Jsoup.parse(this.getText()).text();
	}
	
	
	
	public List<String> chnsTokens(){
		IWordSeg bw = new ChnsWordSeg();
		List<String> tokens = bw.tokens(this.filteredText());
		System.out.println(this.filteredText());
		return tokens;
	}
	
	public List<String> neighborTokens(){
		IWordSeg bw = new BinaryWordSeg();
		List<String> tokens = bw.tokens(this.filteredText());
		
		return tokens;
	}

}




