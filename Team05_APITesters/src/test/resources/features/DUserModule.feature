#@tag2
#Feature: User Module request 
#Background: Admin sets authorization to bearer token
#
#@Test02
#PostRequest_Positive
  #Scenario: Check if admin is able to create a new Admin with valid endpoint and request body with mandatory fields
    #Given Admin creates POST request with all mandatory fields 
    #When Admin sends HTTPS Request with endpoint
   #Then Admin receives 201 Created Status with response body. 
   #
   #@Test03
#GetRequest_Positive(Gets count of active and inactive users)
  #Scenario: Check if admin is able to get count of active and inactive Admins 
    #Given  Admin creates GET Request with active and inactive Admins endpoint
    #When Admin sends Get count of active and inactive users  HTTPS Request with endpoint
    #Then Admin receives 200 OK
  #
   #@Test04
#GetRequest_Positive(All Active Users )
  #Scenario: Check if admin able to retrieve all active Admins
    #Given  Admin creates GET Request 
    #When Admin sends Get All Active Users HTTPS Request with endpoint
    #Then Admin receives 200 OK
    #
     #@Test05
 #UpdateRequest_Positive(Assign Update User Role Program Batch Status)
    #Scenario: Check if admin is able to assign Admin to with program/batch by Admin Id	
#	Given Admin creates PUT Request with valid data in request body 
#		When Admin sends PUT HTTPS Request with User Role Program Batch Status endpoint	
#		Then  Admin receives 200 OK     
    #@Test06
#GetRequest_Positive(Get User by Program Batches)
  #Scenario: Check if admin is able to get the Admins by program batches for valid batch ID
    #Given  Admin creates GET Request with valid batch Id
    #When Admin sends Get User by Program Batches HTTPS Request with endpoint
    #Then Admin receives 200 OK  
    #
    #@Test07
#GetRequest_Positive(Gets Users for Program)
  #Scenario: Check if admin is able to get the Admins for valid program Id
    #Given  Admin creates GET Request with valid program Id
    #When Admin sends Gets Users for Program HTTPS Request with endpoint
    #Then  Admin receives 200 OK
    #
    #@Test08
 #GetRequest_Positive(Gets Users by roleId)
  #Scenario: Check if admin is able to retreive Admins by valid role ID
    #Given  Admin creates GET Request with valid role ID 
    #When Admin sends Gets Users by roleId HTTPS Request with endpoint
    #Then  Admin receives 200 OK
    #
                                   