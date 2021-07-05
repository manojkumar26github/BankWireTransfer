package com.example.BankWireTransfer.BankWireTransfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BankWireTransfer.BankWireTransfer.repository.UserRepository;

@Service
public class TransferService {
	@Autowired
	UserRepository userRepository;
	public int transfer(String source,String dest,int amount)
	{
		String timestamp=userRepository.getDate();
		return userRepository.transfer(source,dest,timestamp,amount);
	}
}
