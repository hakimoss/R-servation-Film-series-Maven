package com.codeboxx.reservationFilm.AVisioner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class CatalogueFilm implements ICatalogueFilm {

	public List<Film> produitsParMc(String mc) {
		List<Film> films = new ArrayList<Film>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn=DriverManager.getConnection("jdbc:mysql://us-cdbr-east-05.cleardb.net/heroku_ef65a64460fe7c0", "b7678791a2c61b", "251d221e");
			PreparedStatement ps = conn.prepareStatement("select * from reservation where name like ?");
			ps.setString(1, "%"+mc+"%");
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				Film f = new Film();
				f.setIdFilm(rs.getInt("id"));
				f.setName(rs.getString("name"));
				f.setNomFilm(rs.getString("film_series"));
				f.setSource(rs.getString("sources"));
				f.setNombreMinute(rs.getInt("dure"));
				films.add(f);
				
			}
			ps.close();conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return films;
	}

	public void addFilm(Film f) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://us-cdbr-east-05.cleardb.net/heroku_ef65a64460fe7c0", "b7678791a2c61b", "251d221e");
			PreparedStatement ps = conn.prepareStatement("insert into reservation(name,film_series,sources,dure) value(?,?,?,?)");
			ps.setString(1, f.getName());
			ps.setString(2, f.getNomFilm());
			ps.setString(3, f.getSource());
			ps.setInt(4, f.getNombreMinute());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	} 

	public void deleteFilm(Film f) {
		Connection conn;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://us-cdbr-east-05.cleardb.net/heroku_ef65a64460fe7c0", "b7678791a2c61b", "251d221e");
			PreparedStatement ps = conn.prepareStatement("delete from reservation where id = ?");
			
			ps.setInt(1, f.getIdFilm());
			ps.execute();
			ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Film> getAllFilm() {
		List<Film> films = new ArrayList<Film>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://us-cdbr-east-05.cleardb.net/heroku_ef65a64460fe7c0", "b7678791a2c61b", "251d221e");
			PreparedStatement ps = conn.prepareStatement("select * from reservation");
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				Film f = new Film();
				f.setIdFilm(rs.getInt("id"));
				f.setName(rs.getString("name"));
				f.setNomFilm(rs.getString("film_series"));
				f.setSource(rs.getString("sources"));
				f.setNombreMinute(rs.getInt("dure"));
				films.add(f);
			}
		
			ps.close();conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return films;		
		
	}
	
}
