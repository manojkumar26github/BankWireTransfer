package com.example.BankWireTransfer.BankWireTransfer.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.BankWireTransfer.BankWireTransfer.model.User;
import com.example.BankWireTransfer.BankWireTransfer.model.Transaction;
 

@Repository
public class DataRepository {
	// we are autowiring jdbc template, 
	 // using the properties we have configured spring automatically 
	 // detects and creates jdbc template using the configuration
	 @Autowired
	 JdbcTemplate jdbcTemplate;
	 
	 public List<Transaction> showTransaction(String username) {
		 List<Transaction> transaction = new ArrayList<Transaction>() ;
		 transaction.addAll(jdbcTemplate.query("select * from TRANSACTIONS where `Beneficiary_name`='"+username+"';", 
				 (rs, rowNum) ->
				  new Transaction(
			      rs.getString("from"),
			      rs.getString("to"),
			      rs.getString("date"),
			      rs.getInt("amount"))));
			System.out.println("--------");
			System.out.println(transaction.toString());
			System.out.println("--------");
			return transaction;
	}
	 
	 public List<User> getAll(String username) {
			List<String> userid = new ArrayList<String>() ;
			 userid.addAll(jdbcTemplate.queryForList("select Customer_name from BANK_ACCOUNT where `Customer_name`='"+username+"';",String.class));
			List<User> user=new ArrayList<User>() ;
			for(int i=0;i<userid.size();i++)
			{
				user.addAll(jdbcTemplate.query("select * from BANK_ACCOUNT where Customer_name='"+userid.get(i)+"';", 
						 (rs, rowNum) ->
			     			new User(
			     		 rs.getString("Customer_name"),
			             rs.getLong("Current_Balance"))));
			}
			 return user;
		}

}
