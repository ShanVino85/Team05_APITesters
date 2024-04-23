@Login
  Feature: Post request 
  
@Test01
  Scenario: Check if Admin able to generate token with valid credential
    Given Add UserLogin Payload
    When Admin calls Post Https method  with valid endpoint
    Then Admin receives 200 created with auto generated token
   
  #Negative
  #@Test02
    #Scenario: Check if Admin able to generate token with invalid endpoint
     #Given Add UserLogin Payload
     #When Admin calls Post Https method  with invalid endpoint
     #Then Admin receives 401 unauthorized
   #@Test03
    #Scenario:Check if Admin able to generate token with invalid credentials
     #Given Admin creates request with invalid credentials
     #When Admin calls Post Https method  with valid endpoint
     #Then Admin receives 400 Bad request
    #
   