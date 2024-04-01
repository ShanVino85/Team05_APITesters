Feature: Admin Role Program Batch Mapping Controller

  Background: 
    Given Admin sets Authorization to bearer token

  Scenario: admin is able to retreive all Admins with assigned program batches
    When Admin creates a GET Request to retrieve all Admins assigned to programs or batches
    And Admin sends HTTPS Request
    Then Admin receives a 200 OK response

  Scenario: admin is able to retreive all Admins with assigned program batches with No Authorization
    Given Admin sets authorization to No Auth
    When Admin creates a GET Request to retrieve all Admins assigned to programsor batches
    And Admin sends HTTPS Request
    Then Admin receives a status 401 with Unauthorized message

  Scenario: admin is able to retreive assigned program batches for valid AdminId
    When Admin creates a GET Request to retrieve Admin assigned to Program or Batch by AdminId
    And Admin sends HTTPS Request
    Then Admin receives a 200 OK response

  Scenario:  admin is able to retreive assigned program batches for invalid AdminId
    When Admin creates a GET Request to retrieve Admin assigned to Program or Batch by invalid AdminID
    And Admin sends HTTPS Request
    Then Admin receives a 404 response

  Scenario:  admin is able to retreive assigned program batches for valid AdminId with No authorization
    Given Admin sets authorization to No Auth
    When Admin creates a GET Request to retrieve Admin assigned to Program or Batch by valid AdminID
    And Admin sends HTTPS Request
    Then Admin receives a status 401 with Unauthorized message
    