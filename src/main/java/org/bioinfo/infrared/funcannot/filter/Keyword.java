package org.bioinfo.infrared.funcannot.filter;

public class Keyword {

	private String keyword;
	private boolean positive;
	
	public Keyword(String keyword) {
		if(keyword != null) {
			if(keyword.startsWith("-")) {
				this.keyword = keyword.substring(1);
				positive = false;
			}else {
				if(keyword.startsWith("+")) {
					this.keyword = keyword.substring(1);
				}else {
					this.keyword = keyword;
				}
				positive = true;
			}
		}else {
			this.keyword = "";
			positive = true;
		}
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setPositive(boolean positive) {
		this.positive = positive;
	}
	public boolean isPositive() {
		return positive;
	}
	
	public String toString() {
		if(positive) {
			return " LIKE '%"+keyword+"%' ";
		}else {
			return " NOT LIKE '%"+keyword+"%' ";
		}
	}
	
}
