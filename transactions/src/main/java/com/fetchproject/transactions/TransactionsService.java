package com.fetchproject.transactions;

import java.util.List;

public interface TransactionsService {
	
	List<Transaction> findAll();
	
	Transaction insertNewTransaction(Transaction transaction);
	
	List<PointDeduction> spendPoints(Points points);
		
}
