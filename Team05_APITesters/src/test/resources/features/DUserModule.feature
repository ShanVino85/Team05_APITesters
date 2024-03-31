Feature: Update Admin Login Status

  Background: 
  Given Admin sets authorization to bearer token For Update Admin Login Status feature

  Scenario: Admin updates Admin login status with valid data
    Given Admin creates PUT Request with valid data in request body to update Admin Login status
    When Admin sends HTTPS Request with endpoint to update Admin Login status
    Then Admin receives 200 OK with Sucessful update of admin login status

  Scenario: Admin updates Admin login status with invalid data
    Given Admin creates PUT Request with invalid data in request body to update Admin Login status
    When Admin sends HTTPS request and invalid data in request body to update Admin Login status
    Then Admin receives 400 Bad Request Status with failure message update of admin login status

  Scenario: Admin is not able to update Admin login status with invalid AdminId
    Given Admin creates PUT Request with valid data in request body to update Admin Login status with invalid Adminid
    When Admin sends HTTPS Request with invalid AdminId to update Admin Login status
    Then Admin receives status 404 with Admin Not Found error message to update
    
Scenario: Admin updates Admin login status with invalid endpoint
    Given Admin creates invalid PUT Request with valid data in request body to update Admin Login status
    When Admin sends HTTPS Request with invalid endpoint to update Admin Login status
    Then Admin receives 401 OK with invalid endpoint to update  admin login status