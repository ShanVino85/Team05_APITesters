@user
Feature: User_Post Request

@User_Post_01
  Scenario: create a User with valid endpoint and request body (Admin UserId)
    Given Admin creates POST request with all mandatory fields
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 201 Created Status with response body.

 @User_Get_V2Filter
  Scenario: Check if admin is able to retrieve all Admins with filters
    Given Admin creates GET Request with filters
    When Admin sends HTTPS Request with filters endpoint
    Then Admin receives "200" OK


 @User_Put_byUserId
  Scenario: Check if admin is able to update Admin details with Admin id and mandatory fields
    Given Admin creates PUT Request with valid data in request body (values only in mandatory fields) 
    When Admin sends HTTPS request for update by userId with endpoiint
    Then Admin receives "200" OK
    

@User_Put_byRoleStatus
  Scenario: Check if admin is able to update role status of a Admin with valid Admin id
    Given Admin creates PUT Request with valid user data in request body 
    When Admin sends HTTPS Request for update by roleStatus with endpoint
    Then Admin receives "200" OK
 