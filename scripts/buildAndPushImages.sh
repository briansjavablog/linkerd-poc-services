#!/bin/sh

cd ..
cd CurrentAccountService
mvn clean package
docker image build -t briansjavablog/current-account-service:latest .
#docker push briansjavablog/current-account-service:latest
cd ..
cd SavingsAccountService
mvn clean package
docker image build -t briansdevblog/savings-account-service:latest .
#docker push briansdevblog/saving-account-service:latest
cd ..
cd CreditCardService
mvn clean package
docker image build -t briansdevblog/credit-card-service:latest .
#docker push briansdevblog/credit-card-service:latest
cd ..
cd BankingService
mvn clean package
docker image build -t briansdevblog/banking-service:latest .
#docker push briansdevblog/banking-service:latest