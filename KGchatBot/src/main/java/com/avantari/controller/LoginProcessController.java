package com.avantari.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.avantari.entity.Customer;
import com.avantari.service.CustomerService;

@Controller
public class LoginProcessController extends HttpServlet {
	
	
	private CustomerService customerService;
	public LoginProcessController(CustomerService customerService) {
		System.out.println("LoginProcesController(CustomerService, Customer)");
		
		this.customerService=customerService;
	}

	@RequestMapping("/loginServ")
	protected String loginUser(HttpServletRequest req)  {
		String email = req.getParameter("email");
		String password = req.getParameter("pwd");
		

		Customer customer = new Customer();
		customer.setEmail(email);
		customer.setPassword(password);

		//CustomerService customerService = new CustomerService();
		boolean status = customerService.loginCustomer(customer);
		if (status) {
			HttpSession session = req.getSession();
			
			System.out.println("login Success");
			
			
			
			session.setAttribute("USERNAME", customer.getName());
			System.out.println(session.getAttribute("USERNAME"));
		

			req.setAttribute("loginStat", "Y");
			return "chat.jsp";
			
		} else {
			
			req.setAttribute("loginStat", "N");
			return "login.jsp";

		}
	}
}