package com.fetchproject.transactions;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionsRepository extends MongoRepository<Transaction, Long>{
	
	Transaction findByPayer(String payer);
	
}
