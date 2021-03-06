package com.codeboxx.reservationFilm.Profil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class CatalogueProfil implements ICatalogueProfil {
	
	public List<Profil> profilParMc(String mc) {
		List<Profil> profils = new ArrayList<Profil>();
		try {
			Connection conn=DriverManager.getConnection("jdbc:mysql://us-cdbr-east-05.cleardb.net/heroku_ef65a64460fe7c0", "b7678791a2c61b", "251d221e");
			PreparedStatement ps = conn.prepareStatement("select * from profils where name like ?");
			ps.setString(1, "%"+mc+"%");
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				Profil p = new Profil();
				p.setIdProfil(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setScore(rs.getInt("score"));
				profils.add(p);
				
			}
			ps.close();conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return profils;
	}

	public void addProfil(Profil p) {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://us-cdbr-east-05.cleardb.net/heroku_ef65a64460fe7c0", "b7678791a2c61b", "251d221e");
			PreparedStatement ps = conn.prepareStatement("insert into profils(name,score) value(?,?)");
			ps.setString(1, p.getName());
			ps.setInt(2, p.getScore());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void deleteProfil(Profil p) {
		Connection conn;
		try {
			conn=DriverManager.getConnection("jdbc:mysql://us-cdbr-east-05.cleardb.net/heroku_ef65a64460fe7c0", "b7678791a2c61b", "251d221e");
			PreparedStatement ps = conn.prepareStatement("delete from profils where id = ?");
			
			ps.setInt(1, p.getIdProfil());
			ps.execute();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Profil> allProfil() {
		List<Profil> profils = new ArrayList<Profil>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://us-cdbr-east-05.cleardb.net/heroku_ef65a64460fe7c0", "b7678791a2c61b", "251d221e");
			PreparedStatement ps = conn.prepareStatement("select * from profils");
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				Profil p = new Profil();
				p.setIdProfil(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setScore(rs.getInt("score"));
				profils.add(p);
			}
		
			ps.close();conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return profils;		
	}
	
	public int avgScore() {
		//List<Profil> profils = new ArrayList<Profil>();
		int avgScore = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://us-cdbr-east-05.cleardb.net/heroku_ef65a64460fe7c0", "b7678791a2c61b", "251d221e");
			PreparedStatement ps = conn.prepareStatement("select AVG(score) from profils");
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				avgScore = rs.getInt(1);			
			}
		
			ps.close();conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return avgScore;		
	}

}
