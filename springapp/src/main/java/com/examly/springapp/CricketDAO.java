package com.examly.springapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CricketDAO {

	Connection conn = null;
	 PreparedStatement stmt = null;
	 ResultSet rs = null;
	public List<Cricketer> getAllCricketer() {
	 List<Cricketer> cricketers = new ArrayList<>();
	 
	 try {
	 conn = JDBCUtils.getConnection();
	// System.out.println("naveen"+conn);
	 stmt = conn.prepareStatement("SELECT * FROM cricketer");
	 rs = stmt.executeQuery();
	 while (rs.next()) {
	 
	 String name = rs.getString("name");
	 int age = rs.getInt("age");
	 String country = rs.getString("country");
	 
	 Cricketer cricketer = new Cricketer(name, age, country);
	 cricketers.add(cricketer);
	 }
	 } catch (SQLException e) {
	 e.printStackTrace();
	 } finally {
	 JDBCUtils.closeResultSet(rs);
	 JDBCUtils.closeStatement(stmt);
	 JDBCUtils.closeConnection(conn);
	 }
	 return cricketers;
	 }
	public void addCricketer(Cricketer cricketer) {
	 try {
	 conn = JDBCUtils.getConnection();
	 
	 PreparedStatement stmt = conn.prepareStatement("INSERT INTO cricketer (name, age, country) VALUES (?, ?, ?)"); 
	 {
	 stmt.setString(1, cricketer.getName());
	 stmt.setInt(2, (int) cricketer.getAge());
	 stmt.setString(3, cricketer.getCountry());
	 stmt.executeUpdate();
	 }
	 } catch (SQLException e) {
	 e.printStackTrace();
	 }
	 finally {
	 JDBCUtils.closeResultSet(rs);
	 JDBCUtils.closeStatement(stmt);
	 JDBCUtils.closeConnection(conn);
	 }
	} 
	
}
