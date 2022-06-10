package com.bkap.Entities;

import java.util.List;

public class page<T> {

	private int number;
	private long totalElements;
	private int size;
	private List<T> content;
	public page() {
		super();
		// TODO Auto-generated constructor stub
	}
	public page(int number, long l, int size, List<T> content) {
		super();
		this.number = number;
		this.totalElements = l;
		this.size = size;
		this.content = content;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	
}
