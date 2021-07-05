package com.example.BankWireTransfer.BankWireTransfer.model;


import org.springframework.stereotype.Component;

public class User {
	Long Customer_Id;
	String Customer_name;
	Long Current_Balance;
	
	
	public User(  String Customer_name, Long Current_Balance) {
		super();
		this.Customer_name = Customer_name;
		this.Current_Balance = Current_Balance;
	}
	
	public String getName() {
		return Customer_name;
	}
	public void setName(String Beneficiary_name) {
		this.Customer_name = Beneficiary_name;
	}
 	public Long getBalance() {
		return Current_Balance;
	}
	public void setBalance(Long Current_Balance) {
		this.Current_Balance = Current_Balance;
	}
	@Override
	public String toString() {
		return "Bank Details [Customer Name=" + Customer_name + ", Current Balance=" + Current_Balance + "]";
	}
	
	
}
