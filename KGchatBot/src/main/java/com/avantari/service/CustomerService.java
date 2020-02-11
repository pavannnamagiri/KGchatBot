package com.avantari.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avantari.dao.CustomerDao;
import com.avantari.entity.Customer;


@Service
public class CustomerService  {
	
	private CustomerDao customerDao;
	
	
	
	public CustomerService() {
		System.out.println("CustomerService constructor created");
	}
	@Autowired
	public CustomerService(CustomerDao customerDao) {
		
		this.customerDao=customerDao;
		
		System.out.println(this.customerDao.hashCode());
		System.out.println("CustomerService(CustomerDao,WelcomeMail)");
	}
	
	
	
	
	public boolean loginCustomer(Customer customer) {
		
		System.out.println(customerDao.hashCode());
		return customerDao.selectCustomerByEmailAndPassword(customer);
	}
	
	public boolean registerCustomer(Customer customer) {
		System.out.println(customerDao);
		boolean status = customerDao.insertCustomer(customer);
		
		
		
		
		
		return status;
	}
	public boolean changePasswordCustomer(Customer customer) {
	
		return customerDao.insertNewPassword(customer);
	}
	
	
	public ArrayList<Customer> getAllEmployees() {
		ArrayList<Customer> customerList = customerDao.selectAllCustomer();
		
		return customerList;
	}
}