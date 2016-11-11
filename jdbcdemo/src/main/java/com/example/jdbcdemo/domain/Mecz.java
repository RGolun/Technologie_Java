package com.example.jdbcdemo.domain;

public class Mecz {
	
	private long id;
	private String data;
	private String wynik;
	private String cena;
	
	public Mecz() {
		
	}

	public Mecz( String data, String wynik, String cena) {
		super();
		this.data = data;
		this.wynik = wynik;
		this.cena = cena;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getdata() {
		return data;
	}

	public void setdata(String data) {
		this.data = data;
	}

	public String getwynik() {
		return wynik;
	}

	public void setwynik(String wynik) {
		this.wynik = wynik;
	}

	public String getcena() {
		return cena;
	}

	public void setcena(String cena) {
		this.cena = cena;
	}

	
	
}
