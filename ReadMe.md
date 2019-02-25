#Run a specific test case in a specific test class
mvn test -Dtest=BlogWebControllerIT#homePage

#Run all test cases in defined test class
mvn test -Dtest=BlogWebControllerIT

#Run all test cases in matching test class
mvn test -Dtest=BlogWeb*IT