@userNegative
Feature: Post request (Negative Scenarios)

 @TestN1
 	Scenario: Check if Admin able to generate token with invalid endpoint
    Given Add UserLogin Payload
    When Admin calls Post Https method  with invalid endpoint
    Then Admin receives 401 unauthorized
   
  @TestN2
   Scenario: Check if Admin able to generate token with invalid credentials
    Given Admin creates request with invalid credentials
    When Admin calls Post Https method  with valid endpoint
    Then Admin receives 401 Bad request
    
  #Extra  
  @TestN3
 	Scenario: Check if Admin able to generate token with missing field(s)
    Given Add creates request with missing mandatory field
    When Admin calls Post Https method  with valid endpoint
    Then Admin receives status 400 Bad request
