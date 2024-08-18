package com.qsp.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.qsp.controller.EmployeeController;
import com.qsp.model.Employee;

public class EmployeeView {

	public static void main(String[] args) {
		
	Scanner sc = new Scanner(System.in);
	
	System.out.println("___________________WELCOME______________________________");
	
	do {
		System.out.println("\n1 --> Insert employee");
		System.out.println("2 --> Update  employee name by id");
		System.out.println("3 --> Update  employee salary by id");
		System.out.println("4 --> Delete employee by id");
		System.out.println("5 --> Fetch employee by id");
		System.out.println("6 --> Fetch all employee list");
		
		switch(sc.nextInt()) {
		case 1:{
			Employee e = new Employee();
			System.out.print("Enter the id: ");
			e.setId(sc.nextInt());
			System.out.print("\n Enter the name: ");
			e.setName(sc.next());
			System.out.print("\n Enter the salary: ");
			e.setSalary(sc.nextDouble());
			
			boolean rs = EmployeeController.insert(e);
			if(rs) System.out.println("Employee Inserted successfully");
			
		}
		break;
		
		case 2:{
			System.out.print("Enter the id to update employee name");
			int id= sc.nextInt();
			System.out.print("\n Enter the new Name: ");
			String name = sc.next();
			boolean rs = EmployeeController.updateNameById(id, name);
			if(rs) {
				System.out.println("Name Updated Successfully");
				
			}else {
				System.out.println("Employee not found with given id ");
			}
		}
		break;
		
		case 3:{
			System.out.println("Enter the id to update employee salary");
			int id= sc.nextInt();
			System.out.print("\n Enter the new Salary: ");
			double salary = sc.nextDouble();
			boolean rs = EmployeeController.updateSalaryById(id, salary);
			if(rs) {
				System.out.println("Salary Updated Successfully");
				
			}else {
				System.out.println("Employee not found with given id ");
			}
		}
		break;
		
		case 4:{
			System.out.println("Enter the id to delete employee ");
			int id= sc.nextInt();
			boolean rs = EmployeeController.deleteById(id);
			if(rs) {
				System.out.println("Employee Deleted Successfully");
				
			}else {
				System.out.println("Employee not found with given id ");
			}
		}
		break;
		
		case 5:{
			System.out.print("Enter the id to get the employee");
			int id= sc.nextInt();		 
			Employee emp = EmployeeController.fetchEmployeeById(id);
			System.out.println(emp);
	
		}
		break;
	
		
		
	case 6:{
		System.out.println("\n_____Employees______\n");		 
		List<Employee> li =new ArrayList<>();
		li=EmployeeController.fetchAll();
		
		for(Employee e:li) {
			System.out.println(e);
		}
	
	}
	break;
	
	default:
		System.out.println("Wrong info");
	
	}
	System.out.println("\nPress Y to Continue\n");
	}
	while("Y".equalsIgnoreCase(sc.next()));

}

}
