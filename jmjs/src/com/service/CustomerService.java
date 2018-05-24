package com.service;

import com.dao.CustomerDao;
import com.pojo.Customer;
import java.util.List;

public class CustomerService {
	private CustomerDao dao = new CustomerDao();
	
	public void delete(Customer customer) {
		dao.delete(customer);
	}

	public void update(Customer customer) {
		dao.update(customer);
	}

	public Customer findOne(Customer customer) {
		return dao.findOne(customer);
	}

	public void add(Customer customer) {
		dao.add(customer);
	}

	public List<Customer> findAll() {
		return dao.findAll();
	}
	public void deleteAll(String[] cIds) {
		dao.deleteAll(cIds);
	}

	public List<Customer> findAllCusIdAndName() {
		// TODO Auto-generated method stub
		return dao.findAllCusIdAndName();
	}
	
}
