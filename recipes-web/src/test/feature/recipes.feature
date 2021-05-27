Feature: Testing a recipes-web service REST API Users should be able to submit GET, PUT, POST and DELETE requests.

  Background:
    Given I Set sample recipes-web REST API url

  Scenario: GET recipes data from recipes-web service for unauthorized
    Given I Set GET recipes-web service api endpoint "1"
    When I Set request HEADER param content type as "application/json."
    And I set request HEADER param Authorization "Key as Authorization and Value as Basic ABC"
    And Send GET HTTP request
    Then I receive valid HTTP response code 401 for "GET."
    And Response BODY "GET" is empty

  Scenario: POST recipes data to a recipes-web service
    Given I Set POST recipes-web service api endpoint
    When I Set request HEADER param content type as "application/json."
    And I set request HEADER param Authorization as "Key as Authorization and Value as Basic YWRtaW46YWRtaW4="
    And Set request Body as raw from postman
    And Send a POST HTTP request
    Then I receive valid HTTP response code 201
    And Response BODY "POST" is "Recipe saved successfully..".

  Scenario: GET recipes data from recipes-web service
    Given I Set GET recipes-web service api endpoint "1"
    When I Set request HEADER param content type as "application/json."
    And I set request HEADER param Authorization "Key as Authorization and Value as Basic YWRtaW46YWRtaW4="
    And Send GET HTTP request
    Then I receive valid HTTP response code 200 for "GET."
    And Response BODY "GET" is non-empty

  Scenario: GET recipes data from recipes-web service which recipe doesnt exist
    Given I Set GET recipes-web service api endpoint "2"
    When I Set request HEADER param content type as "application/json."
    And I set request HEADER param Authorization "Key as Authorization and Value as Basic YWRtaW46YWRtaW4="
    And Send GET HTTP request
    Then I receive valid HTTP response code 404 for "GET."
    And Response BODY "GET" is non-empty
    And Response BODY with "error": "Error_CODE-0001" and "message": "No recipe found with Id 2"

  Scenario: GET ALL recipes data from recipes-web service
    Given I Set GET recipes-web service api endpoint without PathVariable
    When I Set request HEADER param content type as "application/json."
    And I set request HEADER param Key as Authorization and Value as Basic YWRtaW46YWRtaW4="
    And Send GET ALL HTTP request
    Then I receive valid HTTP response code 200 for "GET ALL."
    And Response BODY "GET" is non-empty and list of recipes

  Scenario: GET ALL recipes data from recipes-web service which is empty list
    Given I Set GET recipes-web service api endpoint without PathVariable
    When I Set request HEADER param content type as "application/json."
    And I set request HEADER param Key as Authorization and Value as Basic YWRtaW46YWRtaW4="
    And Send GET HTTP request
    Then I receive valid HTTP response code 204 for "GET "
    And Response BODY "GET" is empty


  Scenario: UPDATE recipes data to a recipes-web service
    Given I Set PUT recipes-web service api endpoint "1"
    When I Set request HEADER param content type as "application/json."
    And I set request HEADER param Key as Authorization and Value as Basic YWRtaW46YWRtaW4="
    And I Set PUT request Body as raw from postman
    And Send PUT HTTP request
    Then I receive valid HTTP response code 200 for "PUT."
    And Response BODY "PUT" is non-empty and updated recipe object

  Scenario: UPDATE recipes data from recipes-web service which recipe doesnt exist
    Given I Set UPDATE recipes-web service api endpoint "2"
    When I Set request HEADER param content type as "application/json."
    And I set request HEADER param Key as Authorization and Value as Basic YWRtaW46YWRtaW4="
    And I Set PUT request Body as raw from postman
    And Send PUT HTTP request
    Then I receive valid HTTP response code 404 for "PUT."
    And Response BODY "GET" is non-empty
    And Response BODY with "error": "Error_CODE-0001" and "message": "No recipe found with Id 2"


  Scenario: DELETE recipes data from a recipes-web service
    Given I Set DELETE recipes-web service api endpoint for "1"
    When I Set request HEADER param content type as "application/json."
    And I set request HEADER param Key as Authorization and Value as Basic YWRtaW46YWRtaW4="
    And I Send DELETE HTTP request
    Then I receive valid HTTP response code 200 for "DELETE."
    And Response BODY "DELETE" is "Recipe deleted successfully..".

  Scenario: DELETE recipes data from recipes-web service which recipe doesnt exist
    Given I Set DELETE recipes-web service api endpoint "2"
    When I Set request HEADER param content type as "application/json."
    And I set request HEADER param Key as Authorization and Value as Basic YWRtaW46YWRtaW4="
    And I Set DELETE request Body as raw from postman
    And Send DELETE HTTP request
    Then I receive valid HTTP response code 404 for "PUT."
    And Response BODY "GET" is non-empty
    And Response BODY with "error": "Error_CODE-0001" and "message": "No recipe found with Id 2"