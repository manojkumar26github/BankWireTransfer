package com.example.BankWireTransfer.BankWireTransfer.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BankWireTransfer.BankWireTransfer.model.User;
import com.example.BankWireTransfer.BankWireTransfer.repository.DataRepository;
import com.example.BankWireTransfer.BankWireTransfer.repository.UserRepository;

@Service
public class CheckProfile {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DataRepository dataRepository;
	
	public List<User> getDetails(String username)
	{
		return userRepository.getDetails(username);
	}

	public List<User> getAll(String role) {
		
		return dataRepository.getAll(role);
	}
}
