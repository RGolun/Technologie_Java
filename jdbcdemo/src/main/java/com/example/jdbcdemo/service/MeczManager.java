package com.example.jdbcdemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.jdbcdemo.domain.Mecz;

public class MeczManager {

	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTableMecz = 
			"CREATE TABLE Mecz(id bigint GENERATED BY DEFAULT AS IDENTITY UNIQUE, data varchar(40), wynik varchar(40), cena varchar(40))";
	private String dropTableMecz = "DROP TABLE Mecz CASCADE";
			
	private PreparedStatement addMeczStmt;
	private PreparedStatement editMeczStmt;
	private PreparedStatement deleteMeczStmt;
	private PreparedStatement deleteAllmeczeStmt;
	private PreparedStatement getMeczStmt;
	private PreparedStatement getAllmeczeStmt;
	
	

	private Statement statement;

	public MeczManager() {
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null,
					null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("Mecz".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists){
				statement.executeUpdate(createTableMecz);
				//statement.executeUpdate(dropTableMecz);
			}
			

			addMeczStmt = connection
					.prepareStatement("INSERT INTO Mecz (data, wynik, cena) VALUES (?, ?, ?)");
			editMeczStmt = connection
					.prepareStatement("UPDATE Mecz SET data=?, wynik=?, cena=?");
			deleteMeczStmt = connection
					.prepareStatement("DELETE FROM Mecz where id=?");
			deleteAllmeczeStmt = connection
					.prepareStatement("DELETE FROM Mecz");
			getAllmeczeStmt = connection
					.prepareStatement("SELECT id, data, wynik, cena FROM Mecz");
			getMeczStmt = connection
					.prepareStatement("SELECT id, data, wynik, cena FROM Mecz where id=?");
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Connection getConnection() {
		return connection;
	}

	public int deleteMecz(long id) {
		int count = 0;
		try {
			deleteMeczStmt.setLong(1, id);
			count = deleteMeczStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	void clearmecze() {
		try {
			deleteAllmeczeStmt.executeUpdate();
			statement.executeUpdate(dropTableMecz);
			statement.executeUpdate(createTableMecz);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addMecz(Mecz Mecz) {
		int count = 0;
		try {
			addMeczStmt.setString(1, Mecz.getdata());
			addMeczStmt.setString(2, Mecz.getwynik());
			addMeczStmt.setString(3, Mecz.getcena());

			count = addMeczStmt.executeUpdate();
			//System.out.print("udalo sie = " + count);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int editMecz(Mecz Mecz) {
		int count = 0;
		try {
			editMeczStmt.setString(1, Mecz.getdata());
			editMeczStmt.setString(2, Mecz.getwynik());
			editMeczStmt.setString(3, Mecz.getcena());

			count = editMeczStmt.executeUpdate();
			//System.out.print("udalo sie = " + count);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public Mecz getMecz(long id) {
		Mecz p = new Mecz();

		try {
			getMeczStmt.setLong(1, id);
			ResultSet rs = getMeczStmt.executeQuery();
			while (rs.next()) {
				
				p.setId(rs.getInt("id"));
				p.setdata(rs.getString("data"));
				p.setwynik(rs.getString("wynik"));
				p.setcena(rs.getString("cena"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return p;
	}

		
	
	public List<Mecz> getAllmecze() {
		List<Mecz> mecze = new ArrayList<Mecz>();

		try {
			ResultSet rs = getAllmeczeStmt.executeQuery();

			while (rs.next()) {
				Mecz p = new Mecz();
				p.setId(rs.getInt("id"));
				p.setdata(rs.getString("data"));
				p.setwynik(rs.getString("wynik"));
				p.setcena(rs.getString("cena"));
				mecze.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mecze;
	}

	public void wypiszMecz(Mecz Mecz) {
		
		System.out.print("\n/////\n" +
				"Id = " + Mecz.getId() +
				" data = " + Mecz.getdata() +
				" wynik = " + Mecz.getwynik() +
				" cena = " + Mecz.getcena() +
				"\n/////\n"
				);	

	}
	
}