package com.tricon.poc.paginationusingtypes.modal;

public class Data {

	private String publisher;
	private String authors;
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public Data(String publisher, String authors) {
		super();
		this.publisher = publisher;
		this.authors = authors;
	}
	public Data() {
		super();
	}
	
}
