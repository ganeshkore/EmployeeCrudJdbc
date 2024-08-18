package com.qsp.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qsp.model.Employee;

public class EmployeeController {

	static Connection conn = null;

	static {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/employee", "postgres", "ROOT");

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

	public static boolean insert(Employee emp) {
		try {
			PreparedStatement ps = conn.prepareStatement("insert into employeedata values(?,?,?)");
			ps.setInt(1, emp.getId());
			ps.setString(2, emp.getName());
			ps.setDouble(3, emp.getSalary());

			ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return true;

	}

	public static Employee fetchEmployeeById(int id) {

		Employee emp = new Employee();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from employeedata where id =?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					emp.setId(rs.getInt("id"));
					emp.setName(rs.getString("name"));
					emp.setSalary(rs.getDouble("salary"));
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return emp;
	}

	public static boolean updateNameById(int id, String name) {

		if (fetchEmployeeById(id).getName() != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("update employeedata set name = ? where id =?");
				ps.setString(1, name);
				ps.setInt(2, id);

				ps.executeUpdate();

			} catch (SQLException e) {

				e.printStackTrace();
			}

			return true;
		}

		else {
			return false;
		}

	}

	public static boolean updateSalaryById(int id, double sal) {

		if (fetchEmployeeById(id).getName() != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("update employeedata set salary = ? where id =?");
				ps.setDouble(1, sal);
				ps.setInt(2, id);

				ps.executeUpdate();

			} catch (SQLException e) {

				e.printStackTrace();
			}

			return true;
		}

		else {
			return false;
		}

	}

	public static boolean deleteById(int id) {
		if (fetchEmployeeById(id).getName() != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("delete from employeedata where id =?");
				ps.setInt(1, id);

				ps.executeUpdate();

			} catch (SQLException e) {

				e.printStackTrace();
			}

			return true;
		
		}

		else {
			return false;
		}
	}
	
	public static List<Employee> fetchAll(){
		List <Employee> li = new ArrayList<Employee>();
		
		try {
			PreparedStatement ps = conn.prepareStatement("select * from employeedata");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSalary(rs.getDouble("salary"));
				li.add(emp);
						
			}
			
		}
			catch(Exception e) {
				e.printStackTrace();
			}
		
		return li;
		
	}
}
