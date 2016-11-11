package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Stadion;
import com.example.jdbcdemo.domain.Mecz;
import com.example.jdbcdemo.domain.MeczJoinStadion;

public class MeczJoinStadionManagerTest {
	
	StadionManager StadionManager = new StadionManager();
	MeczManager MeczManager = new MeczManager();
	MeczJoinStadionManager MeczJoinStadionManager = new MeczJoinStadionManager();
	
	//Mecz
	private final static String mecz_data_1 = "12-12-2014";
	private final static String Mecz_wynik_1 = "2:0";
	private final static String Mecz_cena_1 = "100zl";
	
	//Stadiony
	private final static String nazwa_1 = "PGE Arena Gdansk";
	private final static String miasto_1 = "Gdansk";
	private final static int Mecz_1 = 0;
	private final static String nazwa_2 = "Olimpia";
	private final static String miasto_2 = "Sopot";
	private final static int Mecz_2 = 0;
	private final static String nazwa_3 = "Narodowy";
	private final static String miasto_3 = "Warszawa";
	private final static int Mecz_3 = 1;
	
	private final static String nazwa_1_EDIT = "PGE Arena";
	private final static String miasto_1_EDIT = "Gdynia";
	private final static int Mecz_1_EDIT = 0;
	private final static String nazwa_2_EDIT = "Olimp";
	private final static String miasto_2_EDIT = "Bydgoszcz";
	private final static int Mecz_2_EDIT = 1;
	
	
	@Test
	public void checkConnection(){
		assertNotNull(StadionManager.getConnection());
	}
	


	@Test
	public void checkMeczJoinStadionyOne(){
		
		Mecz Mecz = new Mecz(mecz_data_1, Mecz_wynik_1, Mecz_cena_1);
		
		Stadion Stadion1 = new Stadion(nazwa_1, miasto_1, Mecz_1);
		Stadion Stadion2 = new Stadion(nazwa_2, miasto_2, Mecz_2);
		
		MeczManager.clearmecze();
		StadionManager.clearStadiony();
		
		assertEquals(1,MeczManager.addMecz(Mecz));
		assertEquals(1,StadionManager.addStadion(Stadion1));
		assertEquals(1,StadionManager.addStadion(Stadion2));
				
		List<MeczJoinStadion> meczeJoinStadion = MeczJoinStadionManager.getMeczJoinStadion(0);
		
		assertEquals(mecz_data_1, meczeJoinStadion.get(0).getdata());
		assertEquals(Mecz_wynik_1, meczeJoinStadion.get(0).getwynik());
		assertEquals(Mecz_cena_1, meczeJoinStadion.get(0).getcena());
		assertEquals(nazwa_1, meczeJoinStadion.get(0).getnazwa_stadion());
		assertEquals(miasto_1, meczeJoinStadion.get(0).getmiasto_stadion());
		
	}
	

	@Test
	public void checkMeczJoinStadionyOneNotExistingMecz(){
		
		Mecz Mecz = new Mecz(mecz_data_1, Mecz_wynik_1, Mecz_cena_1);
		
		Stadion Stadion1 = new Stadion(nazwa_1, miasto_1, Mecz_1);
		Stadion Stadion2 = new Stadion(nazwa_2, miasto_2, Mecz_2);
		
		MeczManager.clearmecze();
		StadionManager.clearStadiony();
		
		assertEquals(1,MeczManager.addMecz(Mecz));
		assertEquals(1,StadionManager.addStadion(Stadion1));
		assertEquals(1,StadionManager.addStadion(Stadion2));
				
		List<MeczJoinStadion> meczeJoinStadion = MeczJoinStadionManager.getMeczJoinStadion(1);
		List<MeczJoinStadion> PustaTablica= new ArrayList<MeczJoinStadion>();
		assertEquals(PustaTablica, meczeJoinStadion);
		
	}
	
	

	@Test
	public void checkMeczJoinStadionyAll(){
		
		Mecz Mecz = new Mecz(mecz_data_1, Mecz_wynik_1, Mecz_cena_1);
		
		Stadion Stadion1 = new Stadion(nazwa_1, miasto_1, Mecz_1);
		Stadion Stadion2 = new Stadion(nazwa_2, miasto_2, Mecz_2);
		
		MeczManager.clearmecze();
		StadionManager.clearStadiony();
		
		assertEquals(1,MeczManager.addMecz(Mecz));
		assertEquals(1,StadionManager.addStadion(Stadion1));
		assertEquals(1,StadionManager.addStadion(Stadion2));
				
		List<MeczJoinStadion> meczeJoinStadion = MeczJoinStadionManager.getMeczJoinStadion(0);
		
		assertEquals(mecz_data_1, meczeJoinStadion.get(0).getdata());
		assertEquals(Mecz_wynik_1, meczeJoinStadion.get(0).getwynik());
		assertEquals(Mecz_cena_1, meczeJoinStadion.get(0).getcena());
		assertEquals(nazwa_1, meczeJoinStadion.get(0).getnazwa_stadion());
		assertEquals(miasto_1, meczeJoinStadion.get(0).getmiasto_stadion());

		assertEquals(mecz_data_1, meczeJoinStadion.get(1).getdata());
		assertEquals(Mecz_wynik_1, meczeJoinStadion.get(1).getwynik());
		assertEquals(Mecz_cena_1, meczeJoinStadion.get(1).getcena());
		assertEquals(nazwa_2, meczeJoinStadion.get(1).getnazwa_stadion());
		assertEquals(miasto_2, meczeJoinStadion.get(1).getmiasto_stadion());

	}
}
