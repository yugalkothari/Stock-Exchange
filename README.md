# Stock-Exchange
Stock-Exchange Order Matcher

Its a simple Java(Spring) based application consisting of:

1 controller
   -helps you add orders
   -show orders and transaction

2 models and repositories for
    -buy orders
    -sell orders
    -transactions

3 service which runs every second to check if there is any unprocesssed buy transaction and if there is any unprocessed
sell transaction which matches the buy transaction and update the transaction table.


It contains a client application as well in the client folder which can be used.

![alt text](https://github.com/yugalkothari/Stock-Exchange/blob/master/client/stocker.png)

![alt text](https://github.com/yugalkothari/Stock-Exchange/blob/master/client/stocker2.png)


   

