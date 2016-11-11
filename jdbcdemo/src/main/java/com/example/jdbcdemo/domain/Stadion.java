package com.example.jdbcdemo.domain;

public class Stadion {
	
	private long id;
	private String nazwa;
	private String miasto;
	private int mecz;
	
	public Stadion() {
		
	}

	public Stadion(String nazwa, String miasto, int mecz) {
		super();
		this.nazwa = nazwa;
		this.miasto = miasto;
		this.mecz = mecz;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getmiasto() {
		return miasto;
	}

	public void setmiasto(String miasto) {
		this.miasto = miasto;
	}

	public int getmecz() {
		return mecz;
	}

	public void setmecz(int mecz) {
		this.mecz = mecz;
	}

	
}
