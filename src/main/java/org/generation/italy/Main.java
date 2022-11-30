package org.generation.italy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
	
	private static final String URL = "jdbc:mysql://localhost:3306/nations";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	
	public static void main(String[] args) {
		step4();
	}
	
	
	
	private static void step3() {
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
			final String sql = "select countries.country_id, countries.name, regions.name as region_name, continents.name as continent_name from countries \r\n"
					+ "join regions \r\n"
					+ "on countries.region_id = regions.region_id \r\n"
					+ "join continents\r\n"
					+ "on regions.continent_id = continents.continent_id \r\n"
					+ "order by countries.name asc ";
			try (PreparedStatement ps = con.prepareStatement(sql)){
				try(ResultSet rs = ps.executeQuery()){
					while(rs.next()) {
						final int id = rs.getInt(1);
						final String name= rs.getString(2);
						final String region_name= rs.getString(3);
						final String continent_name= rs.getString(4);
						System.out.println(id  + " -  " + name + " - " + region_name + " - " + continent_name);
					}
				}
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void step4() {
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
			System.out.println("Inserire il nome di una regione:");
			Scanner sn = new Scanner(System.in);
			String nameIn = sn.nextLine();
			
			final String sql = "select countries.country_id, countries.name, regions.name as region_name, continents.name as continent_name from countries \r\n"
					+ "join regions \r\n"
					+ "on countries.region_id = regions.region_id \r\n"
					+ "join continents\r\n"
					+ "on regions.continent_id = continents.continent_id \r\n"
					+ "where countries.name like ? \r\n"
					+ "order by countries.name asc";
			try (PreparedStatement ps = con.prepareStatement(sql)){
				ps.setString(1, nameIn);
				try(ResultSet rs = ps.executeQuery()){
					while(rs.next()) {
						final int id = rs.getInt(1);
						final String name= rs.getString(2);
						final String region_name= rs.getString(3);
						final String continent_name= rs.getString(4);
						System.out.println(id  + " -  " + name + " - " + region_name + " - " + continent_name);
					}
				}
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
