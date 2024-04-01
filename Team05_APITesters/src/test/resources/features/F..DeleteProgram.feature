Feature: Delete Programs in User program controller
Background: Given Admin sets authorization to Delete Programs with valid Admin ID 
 Scenario: Delete program/batch assigned to an Admin with ID
   Given Admin creates a DELETE Request to delete Admin assigned to program/batch by AdminId
    When Admin sends HTTPS Request
    Then Admin receives a 200 OK response for successful deletion of program

  Scenario: Fail to delete the program batch for an invalid Admin
  Given Admin creates a DELETE Request to delete Admin assigned to program/batch by invalid AdminId
    When Admin sends HTTPS Request
    Then Admin receives a 404 response with invalid id to delete program

  Scenario: Fail to delete the program batch for valid Admin with No Authorization
    Given Admin sets authorization to No Auth
    When Admin creates a DELETE Request to delete Admin assigned to program/batch by valid AdminId
    Then Admin receives a status 401 with Unauthorized message to delete program for no auth
    