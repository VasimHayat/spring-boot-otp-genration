package com.app.auth.service;

import java.util.List;
import java.util.Optional;

import com.app.auth.beans.EOUser;
import com.app.auth.exceptions.GlbException;
import com.app.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	
	public List<EOUser> findAllUsers(){
		return userRepository.findAll();
	}

	
	public EOUser findUserByEmail(String emailId) {
		List<EOUser> optEmp =  userRepository.findByEmail(emailId);
		if(!optEmp.isEmpty()) {
			return optEmp.get(0);
		}
		throw new GlbException("Employee not found with id "+emailId);
	}
	
	public EOUser addUser(EOUser employee) {
		List<EOUser> empList =  userRepository.findByEmail(employee.getEmail());
		if(!empList.isEmpty()) {
			throw new GlbException("Employee already exist with EmailID  "+employee.getEmail());
		}
		return userRepository.save(employee);
	}
	
	
	public EOUser updateUser(EOUser emp, long id) {
		Optional<EOUser> oldEmployee = userRepository.findById(id);
		if(oldEmployee.isPresent()) {
			EOUser user = oldEmployee.get();
			user.setName(emp.getName());
			user.setEmail(emp.getEmail());
			user.setMobileNumber(emp.getMobileNumber());
			user.setPassword(emp.getPassword());
			return userRepository.save(user);
		}
		throw new GlbException("User Update Failed!! Employee not found with id "+id);
	}
	
	public EOUser deleteUser(long id) {
		Optional<EOUser> userObj = userRepository.findById(id);
		if(userObj.isPresent()) {
			EOUser emp = userObj.get();
			userRepository.delete(emp);
			return emp;
		}
		throw new GlbException("User Deletion Failed!! Employee does not exist with id "+id);
	}
	
	
}
