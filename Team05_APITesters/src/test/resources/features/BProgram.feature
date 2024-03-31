#@tag1
  #Scenario: Check if admin can create a new Admin with valid endpoint and request body with mandatory fields
    #Given Admin creates POST request with all mandatory fields 
    #When Admin sends HTTPS Request with endpoint
    #Then Admin receives 201 Created Status with response body.   
       #
  #Scenario: Check if admin able to create a new Admin with valid endpoint and request body with mandatory and additional fields
    #Given Admin creates POST request with all mandatory fields and additional fields
    #When  Admin sends HTTPS Request with endpoint
    #Then  Admin receives 201 Created Status with response body. 
     #
  #Scenario: Check if admin is able to create a Admin with valid endpoint and invalid values in request body
    #Given Admin creates POST request with all mandatory fields and additional fields
    #When  Admin sends HTTPS Request with endpoint
    #Then  Admin receives 400 Bad Request Status with message and boolean success details
    #
  #Scenario: Check if Admin able to create a Admin missing mandatory fields in request body
      #Given Admin creates POST request with missing mandatory fields in request body
      #When  Admin sends HTTPS Request with endpoint     
      #Then  Admin receives 400 Bad Request Status with error message
      #
  #Scenario: Check if admin able to create a new Admin with request body without authorization
      #Given Admin creates POST request with all mandatory fields and an additional fields
      #When  Admin sends HTTPS Request with endpoint
      #Then  Admin receives status 401 with Unauthorized message   