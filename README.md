# ATM Backend


Simple ATM machine in Java and SpringBoot and simple database H2 (in memory).

###Install

```
- git clone https://github.com/Maczi01/atm.git

- open in IDE

- run

or try with Docker:

docker-compose up -d

App will be available on http://localhost:8080/

```

### Endpoints:
```
   GET /cash - to check money in ATM
   POST /getBalance - to check your balance (account number, pin are required)
   POST /makeWithdrawal - to make withdrawal (account number, pin and amount are required)
```
#### Postman file to check app: 
https://www.getpostman.com/collections/24198c19d4131ca355dd


