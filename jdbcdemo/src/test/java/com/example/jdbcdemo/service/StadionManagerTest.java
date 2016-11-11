package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Stadion;
import com.example.jdbcdemo.domain.Mecz;
import com.example.jdbcdemo.domain.MeczJoinStadion;

public class StadionManagerTest {
	
	StadionManager StadionManager = new StadionManager();
	MeczManager MeczManager = new MeczManager();
	MeczJoinStadionManager MeczJoinStadionManager = new MeczJoinStadionManager();
	
	//Mecz
	private final static String Mecz_NAZWA_1 = "Lechia-Wisla";
	private final static String Mecz_wynik_1 = "1:0";
	private final static String Mecz_cena_1 = "200zl";
	
	//Stadiony
	private final static String NAZWA_1 = "PGE Arena";
	private final static String miasto_1 = "Gdansk";
	private final static int Mecz_1 = 0;
	private final static String NAZWA_2 = "Lazienkowska Arena";
	private final static String miasto_2 = "Warszawa";
	private final static int Mecz_2 = 0;
	private final static String NAZWA_3 = "Slask";
	private final static String miasto_3 = "Wroclaw";
	private final static int Mecz_3 = 1;
	
	private final static String NAZWA_1_EDIT = "Lechia-Wisla";
	private final static String miasto_1_EDIT = "Gdansk";
	private final static int Mecz_1_EDIT = 0;
	private final static String NAZWA_2_EDIT = "Legia-Slask";
	private final static String miasto_2_EDIT = "Warszawa";
	private final static int Mecz_2_EDIT = 1;
	
	
	@Test
	public void checkConnection(){
		assertNotNull(StadionManager.getConnection());
	}
	

	
	@Test
	public void checkAdding(){
		
		Mecz Mecz = new Mecz(Mecz_NAZWA_1, Mecz_wynik_1, Mecz_cena_1);
		Stadion Stadion = new Stadion(NAZWA_1, miasto_1, Mecz_1);
		
		MeczManager.clearmecze();
		StadionManager.clearStadiony();
		assertEquals(1,MeczManager.addMecz(Mecz));
		assertEquals(1,StadionManager.addStadion(Stadion));

		List<Stadion> Stadiony = StadionManager.getAllStadiony();
		Stadion StadionRetrieved = Stadiony.get(0);

		assertEquals(NAZWA_1, StadionRetrieved.getNazwa());
		assertEquals(miasto_1, StadionRetrieved.getmiasto());
		assertEquals(Mecz_1, StadionRetrieved.getmecz());
				
	}
	
	@Test
	public void checkGetStadion(){
		
		Mecz Mecz = new Mecz(Mecz_NAZWA_1, Mecz_wynik_1, Mecz_cena_1);
		Stadion Stadion = new Stadion(NAZWA_1, miasto_1, Mecz_1);
		
		MeczManager.clearmecze();
		StadionManager.clearStadiony();
		assertEquals(1,MeczManager.addMecz(Mecz));
		assertEquals(1,StadionManager.addStadion(Stadion));

		Stadion StadionSql = StadionManager.getStadion(0);

		assertEquals(NAZWA_1, StadionSql.getNazwa());
		assertEquals(miasto_1, StadionSql.getmiasto());
		assertEquals(Mecz_1, StadionSql.getmecz());
				
	}
	
	@Test
	public void checkGetStadionNotExisting(){
		
		Mecz Mecz = new Mecz(Mecz_NAZWA_1, Mecz_wynik_1, Mecz_cena_1);
		Stadion Stadion = new Stadion(NAZWA_1, miasto_1, Mecz_1);
		
		MeczManager.clearmecze();
		StadionManager.clearStadiony();
		assertEquals(1,MeczManager.addMecz(Mecz));
		assertEquals(1,StadionManager.addStadion(Stadion));

		Stadion StadionSql = StadionManager.getStadion(2);
		// nie istniejace int dostaja 0, a string nulla
		assertEquals(null, StadionSql.getNazwa());
		assertEquals(null, StadionSql.getmiasto());
		assertEquals(0, StadionSql.getmecz());
				
	}
	
	@Test
public void checkEditing(){
		
		Mecz Mecz = new Mecz(Mecz_NAZWA_1, Mecz_wynik_1, Mecz_cena_1);
		Stadion Stadion = new Stadion(NAZWA_1, miasto_1, Mecz_1);
		Stadion Stadion_edit = new Stadion(NAZWA_1_EDIT, miasto_1_EDIT, Mecz_1_EDIT);
		
		MeczManager.clearmecze();
		StadionManager.clearStadiony();
		assertEquals(1,MeczManager.addMecz(Mecz));
		assertEquals(1,StadionManager.addStadion(Stadion));
		
		assertEquals(1,StadionManager.editStadion(Stadion_edit));
		
		List<Stadion> Stadiony = StadionManager.getAllStadiony();
		Stadion StadionRetrieved = Stadiony.get(0);
		
		assertEquals(NAZWA_1_EDIT, StadionRetrieved.getNazwa());
		assertEquals(miasto_1_EDIT, StadionRetrieved.getmiasto());
		assertEquals(Mecz_1_EDIT, StadionRetrieved.getmecz());
				
	}

	@Test
	public void checkEditingNotExistingMecz(){
		
		Mecz Mecz = new Mecz(Mecz_NAZWA_1, Mecz_wynik_1, Mecz_cena_1);
		Stadion Stadion = new Stadion(NAZWA_1, miasto_1, Mecz_1);
		Stadion Stadion_edit = new Stadion(NAZWA_1_EDIT, miasto_1_EDIT, 1);
		
		MeczManager.clearmecze();
		StadionManager.clearStadiony();
		assertEquals(1,MeczManager.addMecz(Mecz));
		assertEquals(1,StadionManager.addStadion(Stadion));
		
		// tutaj poiwnno sypnac sqlexception
		System.out.print("\n/////////////////\ntutaj poiwnno sypnac sqlexception\n/////////////////\n");
		assertEquals(0,StadionManager.editStadion(Stadion_edit));
		
		List<Stadion> Stadiony = StadionManager.getAllStadiony();
		Stadion StadionRetrieved = Stadiony.get(0);
		
		assertEquals(NAZWA_1, StadionRetrieved.getNazwa());
		assertEquals(miasto_1, StadionRetrieved.getmiasto());
		assertEquals(Mecz_1, StadionRetrieved.getmecz());
		System.out.print("\n/////////////////\nKoniec tego testu\n/////////////////\n");
				
	}

	@Test
	public void checkDeleteOne(){
		
		Mecz Mecz = new Mecz(Mecz_NAZWA_1, Mecz_wynik_1, Mecz_cena_1);
		
		Stadion Stadion1 = new Stadion(NAZWA_1, miasto_1, Mecz_1);
		Stadion Stadion2 = new Stadion(NAZWA_2, miasto_2, Mecz_2);
		
		MeczManager.clearmecze();
		StadionManager.clearStadiony();
		
		assertEquals(1,MeczManager.addMecz(Mecz));
		assertEquals(1,StadionManager.addStadion(Stadion1));
		assertEquals(1,StadionManager.addStadion(Stadion2));
		
		List<Stadion> Stadiony = StadionManager.getAllStadiony();
		long usuwanyNr = Stadiony.get(1).getId(); // drugi numer z listy
		
		int usuniecie = StadionManager.deleteStadion(usuwanyNr);
		assertEquals(1, usuniecie);
		Stadiony = StadionManager.getAllStadiony();
				
		Stadion StadionRetrieved = Stadiony.get(0);
		assertEquals(NAZWA_1,StadionRetrieved.getNazwa());
		assertEquals(miasto_1,StadionRetrieved.getmiasto());
		assertEquals(Mecz_1,StadionRetrieved.getmecz());	
	}

	@Test
	public void checkDeleteOneMecz(){
		
		Mecz Mecz = new Mecz(Mecz_NAZWA_1, Mecz_wynik_1, Mecz_cena_1);
		
		Stadion Stadion1 = new Stadion(NAZWA_1, miasto_1, Mecz_1);
		Stadion Stadion2 = new Stadion(NAZWA_2, miasto_2, Mecz_2);
		
		MeczManager.clearmecze();
		StadionManager.clearStadiony();
		
		assertEquals(1,MeczManager.addMecz(Mecz));
		assertEquals(1,StadionManager.addStadion(Stadion1));
		assertEquals(1,StadionManager.addStadion(Stadion2));
		
		
		List<Mecz> mecze = MeczManager.getAllmecze();
		long usuwanyNr = mecze.get(0).getId(); // pierwszy numer z listy
		int usuniecie = MeczManager.deleteMecz(usuwanyNr);
		
		assertEquals(1, usuniecie);
		List<Stadion> Stadiony = StadionManager.getAllStadiony();
		List<Stadion> PustaTablica= new ArrayList<Stadion>();
		
		assertEquals(PustaTablica,Stadiony);
			
	}

	
}
