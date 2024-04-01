
@UserPositive
Feature: POST and Get Request for User Module
Background: Admin Sets Bearer Token Authentication
   Given Add UserLogin Payload
   When  Admin calls Post Https method  with valid endpoint
   Then  Admin receives 200 created with auto generated token
  
 @User_Post_01
  Scenario: Check if admin can create a new Admin with valid endpoint and request body with mandatory fields
    Given Admin creates POST request with all mandatory fields 
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 201 Created Status with response body   
  @User_Post_02     
  Scenario: Check if admin able to create a new Admin with valid endpoint and request body with mandatory and additional fields
    Given Admin creates POST request with all mandatory fields and an additional fields
    When  Admin sends HTTPS Request with endpoint
    Then  Admin receives 201 Created Status with response body
                                                      
  @User_Get_all_roles
  Scenario: Check if admin is able to retreive all the available roles
     Given Admin creates GET Request
     When  Admin sends HTTPS Request with GET All Roles endpoint
     Then  Admin receives 200 OK
     
  @User_Get_all_users
   Scenario: Check if admin able to retrieve all Admin with valid endpoint
     Given Admin creates GET Request
     When  Admin sends HTTPS Request with valid endpoint
     Then  Admin receives 200 OK Status with response body
   @User_Get_User_byUserId      
   Scenario: Check if admin able to retrieve a Admin with valid Admin ID
     Given Admin creates GET Request with valid AdminId
     When  Admin sends HTTPS Request with a valid endpoint
     Then  Admin receives 200 OK Status with response body
    @User_Get_alluserswithroles                                                                     
   Scenario: Check if admin able to retrieve all Admins with roles
     Given  Admin creates GET Request 
     When   Admin sends HTTPS Request with an endpoint
     Then   Admin receives 200 OK Status with response body                                                                 
      
                                                                