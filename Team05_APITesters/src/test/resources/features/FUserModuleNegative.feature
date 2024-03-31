#@tag3
#Feature: User Module request 
#Background: Admin sets authorization to bearer token
#
#Gets count of active and inactive users. Unless role id is specified
#
#
 #@Test01 
 #Scenario: Check if admin is able to get count of active and inactive Admins with invalid endpoint	
  #Given  Admin creates GET Request 	
 #When Admin sends HTTPS Request with endpoint with invalid endpoint		
 #Then Admin receives status 404 with Not Found error message	Negative
#
 #@Test02 
 #Scenario: Check if admin is able to get count of active and inactive Admins by invalid role ID 	
 #Given  Admin creates GET Request with active and inactive Admins Negative endpoint
 #When  Admin sends HTTPS Request with endpoint	invalid role ID 
 #Then  Admin receives status 401 with unauthorized error message	Negative 
 #
  #Need to check
 #@Test03  
 #Scenario: Check if admin is able to get count of active and inactive Admins with no authorization	
 #Given Admin creates authorization GET Request		
 #When Admin sends HTTPS Request with endpoint	no authorization	
 #Then Admin receives status 401 with unauthorized error message	Negative
 #
 #Get All Active Users
 #@Test03 
 #Scenario:  Check if admin able to retrieve all active Admins with invalid endpoint		
 #Given  Admin creates GET Request 	
 #When Admin sends HTTPS Request with all active Admins with invalid endpoint	
 #Then Admin receives status 404 with Not Found error message	Negative
 #
 #Gets User by Program BatchesId 
  #@Test04 
 #Scenario: Check if admin is able to get the Admins by program batches for invalid batch ID	
 #Given Admin creates GET Request  with invalid batchId
#When Admin sends GET Request  with invalid batchId by Program Batches endpoint
#Then Admin receives status 404 with Not Found error message	Negative
#
 #@Test05
 #Scenario: Check if admin is able to get the Admins by program batches for valid batch ID with invalid endpoint	
  #Given	 Admin creates GET Request with valid batch Id 
#	When Admin sends HTTPS Request with valid batch ID with invalid endpoint
#	Then Admin receives status 404 with Not Found error message	Negative
#
#Gets Users By ProgramId
#@Test06
#	Scenario:Check if admin is able to get the Admins for invalid program Id	
#	Given Admin creates GET Request with invalid program Id	
 #When Admin sends GET Request  with invalid program Id by Program  endpoint
 #Then Admin receives status 404 with Not Found error message	Negative
 #
 #Gets Users by invalid role ID	
#@Test07
 #Scenario: Check if admin is able to retreive Admins by invalid role ID	
#	Given Admin creates GET Request for GET with invalid role ID	
#	When Admin sends GET HTTPS Request  endpoint with	invalid role ID
#	Then Admin receives status 404 with Not Found error message	Negative
#
#		
#	