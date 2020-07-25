Feature: Adding new customer


  Scenario: Adding new customer with correct credentials
    Given I make post request to "http://dummy.restapiexample.com/api/v1" to "/create/" resource
    And Response Json should contain new Employee info
    When I send Get request with created Id to "/employee/{id}" resource
    And Employee Json body should match with Posted Json