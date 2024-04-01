Feature: Delete User in usermodule 
Scenario: Delete Admin with valid Admin ID in usermodule for delete reaquest
    Given Admin set authentication with token to perform delete
    When Admin sends Delete request to perfom delete in user module for delete reaquest
    Then the Admin receives a 200 OK status code and a success message user deletd

  Scenario: Delete Admin with invalid Admin ID
    Given Admin is authenticated with a bearer token to delete user in user module
    When Admin sends a DELETE request to delete an Admin with an invalid user ID
    Then the Admin receives a 404 Not Found error message

  Scenario: Delete Admin with no authorization
    Given Admin is not authenticated
    When Admin sends a DELETE request to delete an Admin by ID
    Then the Admin receives a 401 Unauthorized error message
