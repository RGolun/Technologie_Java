package com.example.jdbcdemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.jdbcdemo.domain.MeczJoinStadion;

public class MeczJoinStadionManager {

	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private PreparedStatement getMeczJoinStadionStmt;
	private PreparedStatement getAllStadionyJoinStadionStmt;
	
	

	private Statement statement;

	public MeczJoinStadionManager() {
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			getAllStadionyJoinStadionStmt = connection
					.prepareStatement("SELECT Mecz.id, Mecz.Data, wynik, cena, Stadion.id, Stadion.nazwa, Stadion.miasto FROM Mecz JOIN Stadion ON (Mecz.id = Stadion.Mecz)");
			getMeczJoinStadionStmt = connection
					.prepareStatement("SELECT Mecz.id, Mecz.Data, wynik, cena, Stadion.id, Stadion.nazwa, Stadion.miasto, Stadion.Mecz FROM Mecz JOIN Stadion ON (Mecz.id = Stadion.Mecz) where Mecz.id=?");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Connection getConnection() {
		return connection;
	}

/*
	void clearStadiony() {
		try {
			deleteAllStadionyStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
*/
	public List<MeczJoinStadion> getMeczJoinStadion(long id) {
		List<MeczJoinStadion> Stadiony = new ArrayList<MeczJoinStadion>();

		try {
			getMeczJoinStadionStmt.setLong(1, id);
			ResultSet rs = getMeczJoinStadionStmt.executeQuery();
			
			while (rs.next()) {
				MeczJoinStadion p = new MeczJoinStadion();
				p.setId(rs.getInt("Mecz.id"));
				p.setdata(rs.getString("Mecz.Data"));
				p.setwynik(rs.getString("wynik"));
				p.setcena(rs.getString("cena"));
				p.setid_mecz(rs.getInt("Stadion.id"));
				p.setnazwa_stadion(rs.getString("Stadion.nazwa"));
				p.setmiasto_stadion(rs.getString("Stadion.miasto"));
				Stadiony.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return Stadiony;
	}

	public List<MeczJoinStadion> getAllStadionyJoinStadion() {
		List<MeczJoinStadion> Stadiony = new ArrayList<MeczJoinStadion>();

		try {
			ResultSet rs = getAllStadionyJoinStadionStmt.executeQuery();

			while (rs.next()) {
				MeczJoinStadion p = new MeczJoinStadion();
				p.setId(rs.getInt("Mecz.id"));
				p.setdata(rs.getString("Mecz.Data"));
				p.setwynik(rs.getString("wynik"));
				p.setnazwa_stadion(rs.getString("Stadion.nazwa"));
				p.setmiasto_stadion(rs.getString("Stadion.miasto"));
				Stadiony.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Stadiony;
	}

}
