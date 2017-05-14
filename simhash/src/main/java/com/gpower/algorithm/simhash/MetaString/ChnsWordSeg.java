package com.gpower.algorithm.simhash.MetaString;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;

public class ChnsWordSeg implements IWordSeg {
	
	//word's less then MIN_LEN will be ignore.
	private static final int MIN_LEN = 2;
	
	@Override
	public List<String> tokens(String doc ) {
		StringReader reader = new StringReader(doc);
		IKSegmentation ik = new IKSegmentation(reader, true);
		Lexeme lexeme = null;
		List<String> tokens = new ArrayList<String>();
		String word = null;
		try {
			while ((lexeme = ik.next()) != null) {
				word = lexeme.getLexemeText();
				if (word.length()>=MIN_LEN){
					tokens.add(word);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tokens;
	}
}
