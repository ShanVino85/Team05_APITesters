
Feature: Negative scenario for User Module

Scenario: Check if admin is able to retreive all the available roles without Authorization
   Given Admin creates GET Request without Authorization
   When Admin sends HTTPS Request with GET All Roles endpoint
   Then Admin receives status 401 with Unauthorized message
   
 Scenario: Check if admin is able to retreive all the available roles with invalid End point
    Given Admin creates GET Request 
    When Admin sends HTTPS Request with invalid endpoint 
    Then Admin receives status 404 with Not Found error message
    
 Scenario: Check if admin able to retrieve all Admin without Authorization
    Given Admin creates GET Request without Authorization
    When  Admin sends HTTPS Request with valid endpoint
    Then  Admin receives status 401 with Unauthorized message
      
 Scenario: Check if admin able to retrieve all Admin with invalid endpoint
    Given Admin creates GET Request
    When Admin sends HTTPS Request with invalid endpoint
    Then Admin receives status 404 with Not Found error message
    
 Scenario: Check if admin able to retrieve a Admin with valid Admin ID and invalid endpoint
    Given Admin creates GET Request with valid AdminId
    When Admin sends HTTPS Request with invalid endpointId
    Then Admin receives status 404 with Not Found error message
    
 Scenario: Check if admin able to retrieve a Admin with valid Admin ID with No authorization
    Given Admin creates GET Request with valid AdminId and with No Auth
    When Admin sends HTTPS Request with a valid endpoint
    Then Admin receives status 401 with Unauthorized message
    
 Scenario: Check if Admin able to retrieve a Admin with invalid Admin ID
    Given Admin creates GET Request with invalid AdminId
    When  Admin sends HTTPS Request with a valid endpoint
    Then Admin receives status 404 with Not Found error message
    
  Scenario: Check if admin able to retrieve all Admins with roles with No authorization
    Given Admin creates GET Request without Authorization
    When  Admin sends HTTPS Request with endpoint as Alluserswithroles
    Then Admin receives status 401 with Unauthorized message
    
  Scenario: Check if admin able to retrieve all Admins with roles with invalid endpoint
    Given Admin creates GET Request 
    When  Admin sends HTTPS Request with invalid endpoint
    Then  Admin receives status 404 with Not Found error message   
    
    #Scenario: Check if admin is able to create a Admin with valid endpoint and invalid values in request body
    #Given Admin creates POST request with all mandatory fields and additional fields
    #When  Admin sends HTTPS Request with endpoint
    #Then  Admin receives 400 Bad Request Status with message and boolean success details
    #
  #Scenario: Check if Admin able to create a Admin missing mandatory fields in request body
      #Given Admin creates POST request with missing mandatory fields in request body
      #When  Admin sends HTTPS Request with endpoint     
      #Then  Admin receives 400 Bad Request Status with error message
      #
  #Scenario: Check if admin able to create a new Admin with request body without authorization
      #Given Admin creates POST request with all mandatory fields and additional fields with No Auth
      #When  Admin sends HTTPS Request with endpoint
      #Then  Admin receives status 401 with Unauthorized message     
 
      