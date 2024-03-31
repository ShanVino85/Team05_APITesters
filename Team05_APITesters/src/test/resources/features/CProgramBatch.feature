#@programBatch
#Feature: Post request 
 #	 	                                                          	
#Background: Admin sets Authorization to Bearer Token.
#Positive
  #Scenario: Check if admin able to create a Batch with valid endpoint and request body (non existing values)
    #Given Admin creates POST Request  with valid data in request body
    #When Admin sends POST HTTPS Request with endpoint of program batch
    #Then Admin receives 201 Created Status with response body of Post Batch request
 #
#Positive
  #Scenario: Check if admin able to retrieve all batches  with valid LMS API
    #Given Admin creates GET Request of program batch of all batches
    #When Admin sends HTTPS Request with endpoint of program batch of all batches
    #Then Admin receives 200 OK Status with response body of program batch of all batches. 
    #
#Positive
  #Scenario: Check if admin able to retrieve all batches with search string
    #Given Admin creates GET Request with search string of program batch
    #When Admin sends HTTPS Request with endpoint of program batch
    #Then Admin receives 200 Ok status with response body of program batch of search string                                                             
