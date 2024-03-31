@logout
Feature: User Logout

  @logout1
  Scenario: Check if Admin able to logout with valid endpoint
    Given Admin sets authorization to bearer Token with token
    When Admin calls Get Https method with valid endpoint
    Then Admin receives 200 ok  and response with "Logout successful"
    
  @logout2
  Scenario: Check if Admin able to logout with invalid endpoint
    Given Admin sets authorization to bearer Token with token
    When Admin calls Get Https method with invalid endpoint
    Then Admin receives 404 Not found
    
  @logout3
  Scenario: Check if Admin able to logout with valid endpoint with NoAuth
    Given Admin sets No authorization 
    When Admin calls Get Https method with valid endpoint
    Then Admin receives 401 unauthorized
