package com.demo.test;

public class SlidingItembean {

	private String num;
	private String name;
	private String path;
	private String setTop;

	public SlidingItembean() {
		super();
	}



	public SlidingItembean(String num, String name, String path, String setTop) {
		super();
		this.num = num;
		this.name = name;
		this.path = path;
		this.setTop = setTop;
	}



	public String getSetTop() {
		return setTop;
	}



	public void setSetTop(String setTop) {
		this.setTop = setTop;
	}



	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
