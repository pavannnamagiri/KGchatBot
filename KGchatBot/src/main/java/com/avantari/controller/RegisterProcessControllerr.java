package com.avantari.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.avantari.entity.Customer;
import com.avantari.service.CustomerService;

@Controller
public class RegisterProcessControllerr {

	private CustomerService customerService;

	@Autowired
	private RegisterProcessControllerr(CustomerService customerService) {
		System.out.println("RegisterController(CustomerService)");
		this.customerService = customerService;

	}

	@RequestMapping(value = "/registerServ", method = RequestMethod.POST)
	protected String registerUser(HttpServletRequest req) {
		System.out.println("reg serv");
		String name = req.getParameter("username");

		System.out.println("hello reg control");
		String email = req.getParameter("email");
		String password = req.getParameter("pwd");

		Customer customer = new Customer();

		customer.setName(name);

		customer.setEmail(email);
		customer.setPassword(password);

		// CustomerService customerService = new CustomerService();
		boolean status = customerService.registerCustomer(customer);
		if (status) {
			System.out.println("reg controller hit DB");
			req.setAttribute("stat", "Y");
			return "login.jsp?success";
		} else {
			System.out.println("error");
			req.setAttribute("stat", "N");
			return "login.jsp?fail";
		}
		

	}
}