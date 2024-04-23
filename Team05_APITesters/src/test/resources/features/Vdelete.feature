
@deleteuserMap
Feature: Check if admin is able to delete the program batch for invalid Admin
  
  @tag1
  Scenario: Check if admin is able to delete the program batch for invalid Admin
    Given Admin creates DELETE Request to delete Admin assigned to program/batch by invalid AdminId
    When Admin sends HTTPS Request 
    Then Admin receives 404
    
  @tag2
  Scenario: Check if admin is able to delete the program batch for valid Admin and No Authorization
    Given Admin creates DELETE Request to delete Admin assigned to program/batch by valid AdminId
    When Admin sends HTTPS Request 
    Then Admin receives status 401 with Unauthorized message

    
