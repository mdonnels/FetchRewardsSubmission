# Magdalena's Fetch Rewards Submission

### About the project
I was tasked with this [project](https://fetch-hiring.s3.us-east-1.amazonaws.com/points.pdf) for my submission to Fetch Rewards. I used Java 8 with a Spring Boot framework and a Mongo DB storage because those are the ones that I am most familiar with.

### Guides
You should be able to run it from your IDE of choice, just have a MongoDB database running in your local. The application.properties file has it connecting to the test database so be sure to clear that if you already have one.
This REST service runs on the 8081 port with endpoints as follows:
* GET localhost:8081/transactions/showAllTransactions - To show all the stored transactions
* POST localhost:8081/transactions/insert - To insert a new transactions, accepts the transaction in this format in the request body { "payer": "DANNON", "points": 1000, "timestamp": "2020-11-02T14:00:00Z" }
* POST localhost:8081/transactions/spendPoints - To spend the available points, accepts point transactions in this format in the request body { "points": 5000 }
* GET localhost:8081/transactions/availablePoints - Returns a map with payer name and available points for that payer

I look forward to any comments that you might have on this project
