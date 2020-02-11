package com.avantari.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.avantari.entity.Customer;


@Component
public class CustomerDao extends Dao {
	
	private CustomerDao() {
		System.out.println("CutsomerDao Constructor created");
	}

	public boolean insertNewPassword(Customer customer) {
		boolean status = false;

		Connection con = openConnection();
		Statement stmt = openStatement(con);

		try {
			String sql = "UPDATE user_details SET pwd='" + customer.getPassword() + "'" + "WHERE email='"
					+ customer.getEmail() + "'";

			int rows = stmt.executeUpdate(sql);
			if (rows != 0) {
				status = true;

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			closeStatement(stmt);

			closeConnection(con);
		}

		return status;

	}

	public boolean selectCustomerByEmailAndPassword(Customer customer) {
		boolean status = false;

		Connection con = openConnection();
		Statement stmt = openStatement(con);
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM user_details WHERE email = '" + customer.getEmail() + "' AND pwd = '"
					+ customer.getPassword() + "'";

			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				status = true;

				// employee.setId(rs.getInt(1));
				customer.setName(rs.getString(1));
				

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			closeStatement(stmt);
			closeResultSet(rs);
			closeConnection(con);
		}

		return status;
	}

	
	public boolean insertCustomer(Customer customer) {
		boolean status = false;
		boolean status1 = false;

		Connection con = openConnection();
		Statement stmt = openStatement(con);

		try {
			String sql1 = "SELECT email FROM user_details WHERE email='" + customer.getEmail() + "'";

			ResultSet rs = stmt.executeQuery(sql1);
			if (rs.next()) {
				status1 = true;

			}
			if (!status1) {
				String sql2 = "INSERT INTO user_details(name, email,pwd) " + "VALUES('"
						+ customer.getName() + "', '" +  customer.getEmail()
						+ "', '" + customer.getPassword() + "')";

				int rows = stmt.executeUpdate(sql2);
				if (rows != 0) {
					status = true;

				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			closeStatement(stmt);
			closeConnection(con);
		}

		return status;
	}

	public ArrayList<Customer> selectAllCustomer() {
		ArrayList<Customer> customerList = new ArrayList<Customer>();

		Connection con = openConnection();
		Statement stmt = openStatement(con);
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM user_details";

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Customer customer = new Customer();

				customer.setName(rs.getString(1));
				

				customer.setEmail(rs.getString(3));
				customer.setPassword(rs.getString(4));

				customerList.add(customer);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			closeStatement(stmt);
			closeResultSet(rs);
			closeConnection(con);
		}

		return customerList;
	}
}