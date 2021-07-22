package com.fetchproject.transactions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionsServiceImpl implements TransactionsService {

	@Autowired
	private TransactionsRepository transactionsRespository;
	
	@Override
	public List<Transaction> findAll() {
		// TODO Auto-generated method stub
		return transactionsRespository.findAll();
	}
	
	@Override
	public Transaction insertNewTransaction(Transaction transaction) {
		return transactionsRespository.save(transaction);
	}
	
	@Override
	public List<PointDeduction> spendPoints(Points points) {
		Integer totalPoints = points.points;
		List<Transaction> allCurrentTransactions = findAll();
		List<PointDeduction> deductions = new ArrayList<>();
		Collections.sort(allCurrentTransactions);
		for(Transaction transaction : allCurrentTransactions) {
			if(totalPoints == 0) {
				break;
			}
			if((totalPoints - transaction.getPoints()) >= 0) {
				if(transaction.getPoints() < 0) {
					transactionsRespository.save(new Transaction(transaction.getPayer(), -transaction.getPoints(), new Date()));
					PointDeduction prevDeduction = deductions.stream().filter(ded -> ded.payer.equals(transaction.getPayer())).findFirst().get();
					PointDeduction newDeduction = new PointDeduction(prevDeduction.getPayer(), prevDeduction.points - transaction.getPoints());
					deductions.set(deductions.indexOf(prevDeduction), newDeduction);
					totalPoints -= transaction.getPoints();
				} else {
					transactionsRespository.save(new Transaction(transaction.getPayer(), -transaction.getPoints(), new Date()));
					deductions.add(new PointDeduction(transaction.getPayer(), -transaction.getPoints()));
					totalPoints -= transaction.getPoints();
				}
			} else {
				transactionsRespository.save(new Transaction(transaction.getPayer(), -totalPoints, new Date()));
				deductions.add(new PointDeduction(transaction.getPayer(), -totalPoints));
				totalPoints = 0;
			}
		}
		return deductions;
	}
}
