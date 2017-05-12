package com.gpower.algorithm.simhash.htmltexts;

public class CriteriaModel {
	public final static Integer SIGN_DEL = 0;
	public final static Integer SIGN_NOR = 1;

	private Integer weight = SIGN_NOR;
	private String note = null;
	private String criteria = null;
	private String html = null;
	private String text = null;
	private String error = null;

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
