package com.gpower.algorithm.simhash.MetaString;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;

public class ChnsWordSeg implements IWordSeg {

	@Override
	public List<String> tokens(String doc) {
		StringReader reader = new StringReader(doc);
		IKSegmentation ik = new IKSegmentation(reader, true);
		Lexeme lexeme = null;
		List<String> tokens = new ArrayList<String>();
		String word = null;
		try {
			while ((lexeme = ik.next()) != null) {
				word = lexeme.getLexemeText();
				//word selection: length with 2 or more.
				if (word.length()>1){
					tokens.add(word);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tokens;
	}
}
