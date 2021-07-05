package com.example.BankWireTransfer.BankWireTransfer.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.*;
import com.example.BankWireTransfer.BankWireTransfer.model.User;

@Repository
public class UserRepository {
	// we are autowiring jdbc template, 
	 // using the properties we have configured spring automatically 
	 // detects and creates jdbc template using the configuration
	 @Autowired
	 JdbcTemplate jdbcTemplate;
	 
	 
	 public List<String> getAllUserNames() {
	  List<String> userNameList = new ArrayList<>();
	  userNameList.addAll(jdbcTemplate.queryForList("select Customer_name from BANK_ACCOUNT;", String.class));
	  return userNameList;
	 }
	 public List<User> getDetails(String username) {
		 List<User> user = new ArrayList<User>() ;
		 user.addAll(jdbcTemplate.query("select * from BANK_ACCOUNT where Customer_name='"+username+"';", 
				 (rs, rowNum) ->
	     			new User(
	             rs.getString("Customer_name"),
	             rs.getLong("Current_Balance"))));
		 System.out.println("--------");
		 System.out.println(user);
		 System.out.println("--------");
		 return user;
		 }
		 
	 public String getDate() {
		 Date date= new Date();
		 long time = date.getTime();
		 Timestamp ts = new Timestamp(time);
		 return ts.toString();
	 }

	@Override
	public String toString() {
		return "UserRepository [jdbcTemplate=" + jdbcTemplate + "]";
	}
	public int transfer(String source, String dest,String date, int amount) {
		String type = "Debit";
		System.out.println("--"+source+"--"+dest+"--"+date+"--"+amount+"--");
		jdbcTemplate.update("insert into TRANSACTIONS " +
		"(Customer_name,Beneficiary_name,Amount,Transaction_Type) values(?,?,?,?);",source,dest,amount,type);
		int samt=jdbcTemplate.queryForObject("select Current_Balance from BANK_ACCOUNT where Customer_name='"+source+"';",Integer.class);
		int damt=jdbcTemplate.queryForObject("select Current_Balance from BANK_ACCOUNT where Customer_name='"+dest+"';",Integer.class);
		if(samt-amount>0) {
			samt-=amount;
			damt+=amount;
		}
		else return 0;
		System.out.println("--"+source+"--"+dest+"--"+date+"--"+amount+"--");
		jdbcTemplate.update("update BANK_ACCOUNT set Current_Balance="+samt+" where Customer_name='"+source+"';"); 
		System.out.println("##source##"+source+"#dest#"+dest+"--"+date+"--"+amount+"--yes");
		jdbcTemplate.update("update BANK_ACCOUNT set Current_Balance="+damt+" where Customer_name='"+dest+"';");
		return 1;
	}
}
