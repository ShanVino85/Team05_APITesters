Feature: Program Batch module  
  Background: 
  Given Admin sets Authorization to Bearer Token.                                                	
	#Positive - POST REQUEST
	Scenario: Check if admin able to create a Batch with valid endpoint and request body (non existing values)
		Given Admin creates POST Request  with valid data in request body
	  When Admin sends POST HTTPS Request with endpoint of program batch
	  Then Admin receives 201 Created Status with response body of Post Batch request
	  And Validate the Schema after post batch request
	  And Validate the header after post batch request
	
	#Positive - GET REQUEST (All Batches)
	Scenario: Check if admin able to retrieve all batches  with valid LMS API
		Given Admin creates GET Request of program batch of all batches
	  When Admin sends HTTPS Request with endpoint of program batch of all batches
	  Then Admin receives 200 OK Status with response body of program batch of all batches. 
	
	#Positive
	Scenario: Check if admin able to retrieve all batches with search string
		Given Admin creates GET Request with search string of program batch
		When Admin sends HTTPS Request with endpoint of program batch of search string
	  Then Admin receives 200 Ok status with response body of program batch of search string 
	  
	#Positive - GET REQUEST (By Batch ID)
	Scenario: Check if admin able to retrieve a batch with valid BATCH ID
		Given Admin creates GET Request with valid Batch ID
		When Admin sends HTTPS Request with endpoint of program batch with batch id
	  Then Admin receives 200 Ok status with response body of program batch of batch id 
	  
	#Positive
	Scenario: Check if admin able to retrive a batch after deleting the batch
		Given Admin creates GET Request with valid Batch ID after deleting batch
		When Admin sends HTTPS Request with endpoint of program batch with batch id after deleting batch
	  Then Admin receives 200 OK Status with batchStatus field "Inactive" in the response body.

	#Positive - GET REQUEST (By Batch Name)
	Scenario: Check if admin able to retrieve a batch with valid BATCH NAME
		Given Admin creates GET Request with valid batch name
		When Admin sends HTTPS Request with endpoint with batch name
	  Then Admin receives 200 OK Status with response body of batch name. 
	  
	#Positive
	Scenario: Check if admin able to retrive a batch after deleting the batch using batch name
		Given Admin creates GET Request with batch Name after deleting batch
		When Admin sends HTTPS Request with endpoint of program batch with batch name after deleting batch
	  Then Admin receives 200 OK Status with  batchStatus field "Inactive" in the response body using batchName.
	
	#Positive - GET REQUEST (By Program Id)
	Scenario: Check if admin able to retrieve a batch with valid Program ID
		Given Admin creates GET Request with valid Program Id
		When Admin sends HTTPS Request with endpoint with valid program id
	  Then Admin receives 200 OK Status with response body with valid program id. 
	  
	#Positive - PUT REQUEST (Update Batch by batchId)
	Scenario: Check if admin able to update a Batch with valid batchID and mandatory fields in request body
		Given Admin creates PUT Request with valid BatchId and Data
		When Admin sends HTTPS Request with endpoint with PUT in request body
	  Then Admin receives 200 OK Status with updated value in response body with PUT.   
	  
	#Positive - DELETE REQUEST (Delete Batch by batchId)
	Scenario: Check if admin able to delete a Batch with valid Batch ID
		Given Admin creates DELETE Request with valid BatchId
		When Admin sends HTTPS Request  with endpoint for delete batch 
	  Then Admin receives 200 Ok status with message for delete batch. 