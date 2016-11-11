package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Stadion;
import com.example.jdbcdemo.domain.Mecz;
import com.example.jdbcdemo.domain.MeczJoinStadion;

public class MeczManagerTest {
	
	StadionManager StadionManager = new StadionManager();
	MeczManager MeczManager = new MeczManager();
	MeczJoinStadionManager MeczJoinStadionManager = new MeczJoinStadionManager();
	
	//Mecz
	private final static String Mecz_data_1 = "02-10-2015";
	private final static String Mecz_wynik_1 = "0:2";
	private final static String Mecz_cena_1 = "200zl";

	private final static String Mecz_data_1_EDIT = "02-12-2015";
	private final static String Mecz_wynik_1_EDIT = "2:1";
	private final static String Mecz_cena_1_EDIT = "150zl";

	//stadiony
	private final static String data_1 = "01-12-2016";
	private final static String miasto_1 = "Warszawa";
	private final static int Mecz_1 = 0;
	private final static String data_2 = "15-06-2016";
	private final static String miasto_2 = "Gdansk";
	private final static int Mecz_2 = 0;
	private final static String data_3 = "02-12-2015";
	private final static String miasto_3 = "Poznan";
	private final static int Mecz_3 = 1;
	
	private final static String data_1_EDIT = "20-10-2015";
	private final static String miasto_1_EDIT = "Szczecin";
	private final static int Mecz_1_EDIT = 0;
	private final static String data_2_EDIT = "02-12-2014";
	private final static String miasto_2_EDIT = "Wroclaw";
	private final static int Mecz_2_EDIT = 1;
	
	
	@Test
	public void checkConnection(){
		assertNotNull(StadionManager.getConnection());
	}
	

	
	@Test
	public void checkAdding(){
		
		Mecz Mecz = new Mecz(Mecz_data_1, Mecz_wynik_1, Mecz_cena_1);
		
		MeczManager.clearmecze();
		assertEquals(1,MeczManager.addMecz(Mecz));

		List<Mecz> mecze = MeczManager.getAllmecze();
		Mecz MeczRetrieved = mecze.get(0);

		assertEquals(Mecz_data_1, MeczRetrieved.getdata());
		assertEquals(Mecz_wynik_1, MeczRetrieved.getwynik());
		assertEquals(Mecz_cena_1, MeczRetrieved.getcena());
				
	}
	
	@Test
	public void checkMecz(){
		
		Mecz Mecz = new Mecz(Mecz_data_1, Mecz_wynik_1, Mecz_cena_1);
		
		MeczManager.clearmecze();
		assertEquals(1,MeczManager.addMecz(Mecz));
		
		Mecz getMecz = MeczManager.getMecz(0);
		
		assertEquals(Mecz_data_1, getMecz.getdata());
		assertEquals(Mecz_wynik_1, getMecz.getwynik());
		assertEquals(Mecz_cena_1, getMecz.getcena());
				
	}
	
	@Test
	public void checkMeczNotExisting(){
		
		Mecz Mecz = new Mecz(Mecz_data_1, Mecz_wynik_1, Mecz_cena_1);
		
		MeczManager.clearmecze();
		assertEquals(1,MeczManager.addMecz(Mecz));
		
		Mecz getMecz = MeczManager.getMecz(1);
		//ma nulle bo nie istnieje
		assertEquals(null, getMecz.getdata());
		assertEquals(null, getMecz.getwynik());
		assertEquals(null, getMecz.getcena());
				
	}
	
	
	
	@Test
	public void checkEditing(){
		
		Mecz Mecz = new Mecz(Mecz_data_1, Mecz_wynik_1, Mecz_cena_1);
		Mecz Mecz_edit = new Mecz(Mecz_data_1_EDIT, Mecz_wynik_1_EDIT, Mecz_cena_1_EDIT);

		
		MeczManager.clearmecze();

		assertEquals(1,MeczManager.addMecz(Mecz));
		assertEquals(1,MeczManager.editMecz(Mecz_edit));
		
		List<Mecz> mecze = MeczManager.getAllmecze();
		Mecz MeczRetrieved = mecze.get(0);

		assertEquals(Mecz_data_1_EDIT, MeczRetrieved.getdata());
		assertEquals(Mecz_wynik_1_EDIT, MeczRetrieved.getwynik());
		assertEquals(Mecz_cena_1_EDIT, MeczRetrieved.getcena());
				
	}

	@Test
	public void checkDeleteOne(){
		
		Mecz Mecz1 = new Mecz(Mecz_data_1, Mecz_wynik_1, Mecz_cena_1);
		Mecz Mecz2 = new Mecz(Mecz_data_1_EDIT, Mecz_wynik_1_EDIT, Mecz_cena_1_EDIT);
			
		MeczManager.clearmecze();
		
		assertEquals(1,MeczManager.addMecz(Mecz1));
		assertEquals(1,MeczManager.addMecz(Mecz2));
		
		List<Mecz> mecze = MeczManager.getAllmecze();
		long usuwanyNr = mecze.get(0).getId(); // pierwszy numer z listy
		
		int usuniecie = MeczManager.deleteMecz(usuwanyNr);
		assertEquals(1, usuniecie);
		//odswiezenie listy po usuneiciu
		mecze = MeczManager.getAllmecze();
		
		Mecz MeczRetrieved = mecze.get(0);
		assertEquals(Mecz_data_1_EDIT,MeczRetrieved.getdata());
		assertEquals(Mecz_wynik_1_EDIT,MeczRetrieved.getwynik());
	}


	
}
