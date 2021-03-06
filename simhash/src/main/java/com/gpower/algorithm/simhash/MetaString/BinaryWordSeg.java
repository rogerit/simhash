/**
 * 
 */
package com.gpower.algorithm.simhash.MetaString;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zhangcheng
 *
 */
public class BinaryWordSeg implements IWordSeg {

	public List<String> tokens(String doc) {
		List<String> binaryWords = new LinkedList<String>();
		for(int i = 0; i < doc.length() - 1; i += 1) {
			StringBuilder bui = new StringBuilder();
			bui.append(doc.charAt(i)).append(doc.charAt(i + 1));
			binaryWords.add(bui.toString());
		}
		return binaryWords;
	}


}
