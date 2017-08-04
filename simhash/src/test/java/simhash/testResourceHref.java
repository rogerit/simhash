package simhash;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.junit.Test;


public class testResourceHref {
	@Test
	public void test1() 
{
			String link = "http://news.buct.edu.cn/images/content/xwimagesa/11134.jpeg";
			Connection connect = Jsoup.connect(link);
			Response execute;
			try {
				execute = connect.execute();
			} catch (IOException e) {
				if(e instanceof UnsupportedMimeTypeException){
					System.err.println("ok");
				}else {
					System.err.println("err");
				}
			}
	}
}
