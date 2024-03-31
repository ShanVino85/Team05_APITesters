@batch
Feature: Post request 
 	 	                                                          	
Background: Admin sets Authorization to Bearer Token.
#Positive
  Scenario: Check if admin able to create a Batch with valid endpoint and request body (non existing values)
    Given Admin creates POST Request for batch with valid data in request body
    When Admin sends POST HTTPS Request for batch with endpoint of program batch
    Then Admin receives 201 Created Status with response body.
 
                                                            