Feature:


  Scenario:
    Given Base Uri "http://dummy.restapiexample.com/api/v1"
    And Content type Json
    When I post a new Employee  to resource "/create"
    Then Status code should be 200
    And Response Json should contain new Employee info
    When I send Get request with created Id
    Then Status code should be 200
    And Employee Json body should match with Posted Json