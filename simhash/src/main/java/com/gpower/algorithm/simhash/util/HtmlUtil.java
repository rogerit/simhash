package com.gpower.algorithm.simhash.util;



import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
public class HtmlUtil {  
    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // ����script��������ʽ  
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // ����style��������ʽ  
    private static final String regEx_html = "<[^>]+>"; // ����HTML��ǩ��������ʽ  
    private static final String regEx_space = "\\s*|\t|\r|\n";//����ո�س����з�  
      
    /** 
     * @param htmlStr 
     * @return 
     *  ɾ��Html��ǩ 
     */  
    public static String delHTMLTag(String htmlStr) {  
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);  
        Matcher m_script = p_script.matcher(htmlStr);  
        htmlStr = m_script.replaceAll(""); // ����script��ǩ  
  
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);  
        Matcher m_style = p_style.matcher(htmlStr);  
        htmlStr = m_style.replaceAll(""); // ����style��ǩ  
  
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
        Matcher m_html = p_html.matcher(htmlStr);  
        htmlStr = m_html.replaceAll(""); // ����html��ǩ  
  
        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);  
        Matcher m_space = p_space.matcher(htmlStr);  
        htmlStr = m_space.replaceAll(""); // ���˿ո�س���ǩ  
        return htmlStr.trim(); // �����ı��ַ���  
    }  
      
    public static String getTextFromHtml(String htmlStr){  
        htmlStr = delHTMLTag(htmlStr);  
        htmlStr = htmlStr.replaceAll(" ", "");  
        htmlStr = htmlStr.substring(0, htmlStr.indexOf("��")+1);  
        return htmlStr;  
    }  
      
    public static void main(String[] args) {  
        String str = "<div style='text-align:center;'> ���Ρ��ķ硱   ��׳���<br/><span style='font-size:14px;'> </span><span style='font-size:18px;'>��˾�ٿ�����Ⱥ��·�߽���ʵ�����Ա���</span><br/></div>";  
        System.out.println(getTextFromHtml(str));  
        System.out.println(1);
    }  
}