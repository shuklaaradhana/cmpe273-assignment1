package edu.sjsu.cmpe.library.domain;

public class Author {
	private int id;
	private String name;
	private static int authorSeed = 1;
	
	public Author(){
		id = authorSeed++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
