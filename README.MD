# QuickStockSearch API.

The QuickStockSearch API is a simple stock lookup API based on the stock symbol. `i.e:APPL which is Apple Inc`. This will get the latest stock and company information from **AlphaVantage API**. Please click [here](https://www.alphavantage.co/ "AlphaVantage") to get more details.

### Features

The following API`s are implemented in this application.

* Search stocks/symbols - `http://localhost:8001/api/search?query=<YOUR_VALUE>`
* Get the company and stock details - `http://localhost:8001/api/stock?symbol=<YOUR_VALUE>`

### Software requirement

* JRE and JDK 1.8
* Apache Maven

### How to run this application?

In order to run this application in your local machine, please follow the below steps. 

* Checkout this repository to your local machine and navigate to the folder(quick-stock-search-api).
* Run `mvn clean install` in the terminal, it will pull all Java dependency from artifact repository.
* Run `mvn spring-boot:run` in the terminal, the application should run in this `http://localhost:8001` port number.

**Note:** *please find the attached screenshot in this `README.md` as a reference. You should see the same output in your terminal if the application runs successfully in your machine.* 

![Reference](/screen_shots/reference.png)