# Super Simple Stock Market

## Local run:
run 'mvn install' from project root
run 'com.example.supersimplestockmarket.SuperSimpleStockMarketApplication' from your IDE.
Server is listening on http://localhost:8080

# Assumptions about the solution:
Data is stored in memory. Modifications will be lost after restart. 
Sample Stock information and trades are created in DAO classes. New can be added by using endpoints.

For Global Stock Exchange module units are as described in document - Pennies. In all other modules they are used as double values.


## how to use the solution
solution is designed as a backend server with endpoints:

GET /dividend-yield?price={double}&stock={symbol}
GET /pe-ratio?price={double}&stock={symbol}
GET /VWSP?minutes={int}&stock={symbol}
GET /GBCE

GET /trades?minutes={int}&stock={symbol}
GET /trades/id
PUT /trades

GET /stock-info/{symbol}
PUT /stock-info

Use Postman or call urls in browser, to get calculated values. 

### To get Trade use a
http://localhost:8080/trades/{id}
Id is an int. there are 19 trades added by default.

### To add Trade use and include trade in body as json object:
Price is double value and quantity is int value.

http://localhost:8080/trades

{
    "stockSymbol": "TEA",
    "price": 1.8,
    "quantity": 199,
    "tradeType": "PURCHASE",
    "timestamp": "2022-04-25T11:00:47.347063100Z"
}


### To get Stock use a
http://localhost:8080/stock-info/{symbol}
Symbol is three letter stock symbol e.g. "TEA". There are stock info added for the ones mentioned in the homework description. 

### To add Stock info: 
http://localhost:8080/stock-info 
with jason object:
lastDividend, parValue are integer values. fixedDividend is double.

{
    "symbol": "NEW",
    "type": "COMMON",
    "lastDividend": 10,
    "fixedDividend": 0.0,
    "parValue": 100
}