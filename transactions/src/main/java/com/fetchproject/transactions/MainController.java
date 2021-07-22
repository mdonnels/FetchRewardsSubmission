package com.fetchproject.transactions;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class MainController {

	@Autowired
	private TransactionsService transactionsService;
	
    @PostMapping(value="/insert")
    public ResponseEntity<?> insert(@RequestBody Transaction transaction){
        this.transactionsService.insertNewTransaction(transaction);
        return new ResponseEntity("Transaction added successfully", HttpStatus.OK);
     }
	
    @GetMapping(value="/showAllTransactions")
    public List<Transaction> showAllTransactions() {
        return transactionsService.findAll();
 
    }
    
    @PostMapping(value="/spendPoints")
    public List<PointDeduction> spendPoints(@RequestBody Points points){
    	return this.transactionsService.spendPoints(points);
    }
    
    @GetMapping(value="/availablePoints")
    public ResponseEntity<?> availablePoints() {
    	HashMap<String, Integer> map = new HashMap<String, Integer>();
    	List<Transaction> transactions = transactionsService.findAll();
		Collections.sort(transactions);
    	for(Transaction transaction : transactions) {
    		if(map.containsKey(transaction.getPayer())) {
    			map.put(transaction.getPayer(), map.get(transaction.getPayer()) + transaction.getPoints());
    		} else {
    			map.put(transaction.getPayer(), transaction.getPoints());
    		}
    	}
    	
    	return ResponseEntity.ok(map);
    }
}
