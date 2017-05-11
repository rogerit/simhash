package com.gpower.algorithm.simhash.htmlText;

import java.util.List;

public class EmphasiModel {

	private int level = 0;

	private String text = null;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "EmphasiModel [text=" + text + "]";
	}
	

}