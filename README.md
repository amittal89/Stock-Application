# Stock-Application

Steps to install and run  the application  :

1. Download the zip file and extract it to a local directory

2. Open the terminal and browse to the root directory - it will look like ".../Stock-Application-master"

3. Run 'mvn clean' to clean project's working directory

4. Run 'mvn test' to run the tests

5. Run 'mvn install -DskipTests'  to install the application

6. Run 'java -jar target/stockapp-0.0.1-SNAPSHOT.jar'  to start the application and you can see the output in the console


#Project Overview:

App.java is the main class which does all the calculations

Stock.java & Trade.java are the entity classes,whose objects are used to hold the data.

StockService.java contains all the methods required for various calculations. The methods from this service class are called by App.java with appropriate parameters.

StockType.java & TradeType.java are the enum classes.

StockServicetest.java contains unit tests, and they can be used for continuous integration with Jenkins. A web hook can be added, which builds and runs all tests as soon as a commit is done to the repository.
