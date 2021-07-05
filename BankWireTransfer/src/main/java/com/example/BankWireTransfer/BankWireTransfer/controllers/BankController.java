package com.example.BankWireTransfer.BankWireTransfer.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.BankWireTransfer.BankWireTransfer.repository.UserRepository;
import com.example.BankWireTransfer.BankWireTransfer.service.CheckProfile;
import com.example.BankWireTransfer.BankWireTransfer.service.TransferService;

@Controller
@RestController
public class BankController {
	 @Autowired
	 UserRepository userRepository;
	@Autowired
	CheckProfile checkProfile;
	@Autowired
	TransferService transferService;
	String message;
	
	@RequestMapping("/")
	public String hello()   
	{  
	return "Hello Welcome to Sprint Boot Bank Wire Transfer";  
	} 
 
	 @GetMapping(path="/getDetails")
	 public String showDetails(@RequestParam String customername,ModelMap model) {
		 System.out.println("------ -----------	----------------"+customername+"-------");
		 //model.put("user",checkProfile.getDetails(customername)); 
		 System.out.println(checkProfile.getDetails(customername).toString());
		return checkProfile.getDetails(customername).toString();
	 }
	 
	 @GetMapping(path="/transferMoney")
	 public String transferMoney() {
		return "transfer";
	 }

	 @PostMapping(path="/"
	 		+ "transferMoney")
	 public String transferMoney(@RequestParam String customername,@RequestParam int amount,@RequestParam String beneficiaryname,ModelMap model, RedirectAttributes redirAttrs) {
		 System.out.println("##########"+customername+"######");
		 int s= transferService.transfer(customername, beneficiaryname, amount);
		 if(s==0)
		 {
			 redirAttrs.addFlashAttribute("message", "Transaction Unsuccessful");
			 message = "Transaction Unsuccessful";
		 }else if(s==1)
		 {
			 redirAttrs.addFlashAttribute("message", "Transaction Successful");
			 message = "Transaction Successful";
		 }
		 return message.toString();
		 //return "redirect:/transfermoney";
	 }
	 
}
