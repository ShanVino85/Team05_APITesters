@programBatchNoAuthNegative
Feature: Program Batch module  
  Background: 
  Given Admin sets no Authorization 
  
  #Positive - POST REQUEST
	Scenario: Check if admin able to create a Batch with valid endpoint and request body (non existing values)
		Given Admin creates POST Request  with valid data in request body 
	  When Admin sends POST HTTPS Request with endpoint of program batch without Authorization
	  Then Admin receives 401 status with error message Unauthorized.
	                                                	
	#Negative - GET REQUEST (All Batches)
	Scenario: Check if admin able to retrieve all batches without Authorization
		Given Admin creates GET Request to retrieve all batches
	  When Admin sends HTTPS Request with endpoint to retrieve all batches
	  Then Admin receives 401 status with error message Unauthorized.
	  
	#Negative - GET REQUEST (By Batch Name)
	Scenario: Check if admin able to retrieve all batches using batch name without Authorization
		Given Admin creates GET Request with batch Name
	  When Admin sends HTTPS Request with endpoint to retrieve batch by batch name
	  Then Admin receives 401 status with error message Unauthorized.
	  
	#Negative - GET REQUEST (By Program Id)
	Scenario: Check if admin able to retrieve a batch using program id without authorization
		Given Admin creates GET Request with program id
	  When Admin sends HTTPS Request with endpoint with program id
	  Then Admin receives 401 status with error message Unauthorized.
	  
	#Negative - PUT REQUEST (Update Batch by Batch Id)
	Scenario: Check if admin able to update a Batch with valid batchID and mandatory fields in request body without authorization
		Given Admin creates PUT Request with valid BatchId and Data for put request
	  When Admin sends HTTPS Request  with endpoint with valid BatchId and Data for put request
	  Then Admin receives 401 status with error message Unauthorized.

	#Negative - DELETE REQUEST (By Batch Id)
	Scenario: Check if admin able to delete a Batch without authorization
		Given Admin creates DELETE Request with valid BatchId
	  When Admin sends HTTPS Request with endpoint for delete request
	  Then Admin receives 401 status with error message Unauthorized.