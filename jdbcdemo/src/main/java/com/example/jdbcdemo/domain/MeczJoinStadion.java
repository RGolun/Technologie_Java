package com.example.jdbcdemo.domain;

public class MeczJoinStadion {
	
	private long id;
	private String data;
	private String wynik;
	private String cena;
	private long id_mecz;
	private String nazwa_stadion;
	private String miasto_stadion;
	
	public MeczJoinStadion() {
		
	}

	public MeczJoinStadion(String data, String wynik,
			String cena, long id_mecz, String nazwa_stadion,
			String miasto_stadion) {
		super();
		this.data =data;
		this.wynik = wynik;
		this.cena = cena;
		this.id_mecz = id_mecz;
		this.nazwa_stadion =nazwa_stadion;
		this.miasto_stadion = miasto_stadion;
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
		this.data =data;
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

	public long getid_mecz() {
		return id_mecz;
	}

	public void setid_mecz(long id_mecz) {
		this.id_mecz = id_mecz;
	}

	public String getnazwa_stadion() {
		return nazwa_stadion;
	}

	public void setnazwa_stadion(String nazwa_stadion) {
		this.nazwa_stadion =nazwa_stadion;
	}

	public String getmiasto_stadion() {
		return miasto_stadion;
	}

	public void setmiasto_stadion(String miasto_stadion) {
		this.miasto_stadion = miasto_stadion;
	}

	
	
	
}
