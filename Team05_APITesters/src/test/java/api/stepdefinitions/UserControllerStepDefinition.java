package api.stepdefinitions;

import api.resourses.UserControllerData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.resourses.UserLogindata;
import api.utils.ExcelReader;
import api.utils.RestUtils;
import api.utils.IdHolder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.Assert;
public class UserControllerStepDefinition extends RestUtils {
	private UserControllerData userControllerData = new UserControllerData();
	Logger logger = LogManager.getLogger("UserControllerStepDefinition.java");
	   ExcelReader excelReader = new ExcelReader();
	 
	private Response response;
	@Given("Admin sets Authorization to bearer token")
	public void admin_sets_authorization_to_bearer_token() {
		
		logger.info("======Admin sets Authorization to bearer token======");
		
	}
	@When("Admin creates a GET Request to retrieve all Admins assigned to programs or batches")
	public void admin_creates_a_get_request_to_retrieve_all_admins_assigned_to_programs_or_batches() {
		logger.info("======Admin sends Request to retrieve all Admins assigned to programs or batches======");
			
	}

	@When("Admin sends HTTPS Request")
	public void admin_sends_https_request() {
		Response response = RestAssured.given()
				.header("Authorization", "Bearer " + IdHolder.getToken()) 
				.get(routes.getString("base_url") + routes.getString("Get_AssignedProgram/Batch(es)ofAllUsers"));	
		String jsonResponse = response.getBody().asString();
		Assert.assertEquals(200, response.getStatusCode());   
	}

	@Then("Admin receives a {int} OK response")
	public void admin_receives_a_ok_response(Integer int1) {
		

	}

	@Given("Admin sets authorization to No Auth")
	public void admin_sets_authorization_to_no_auth() {
		logger.info("======Admin sets no Authorization ======");
	}

	@When("Admin creates a GET Request to retrieve all Admins assigned to programsor batches")
	public void admin_creates_a_get_request_to_retrieve_all_admins_assigned_to_programsor_batches() {
		logger.info("======Admin sends Request without auth to retrieve all Admins assigned to programs or batches======");
		Response response = RestAssured.given() 
	            .get(routes.getString("base_url") + routes.getString("Get_AssignedProgram/Batch(es)ofAllUsers"));	

	    Assert.assertEquals(401, response.getStatusCode());
	}

	@Then("Admin receives a status {int} with Unauthorized message")
	public void admin_receives_a_status_with_unauthorized_message(Integer int1) {
	    
	}

	@When("Admin creates a GET Request to retrieve Admin assigned to Program or Batch by AdminId")
	public void admin_creates_a_get_request_to_retrieve_admin_assigned_to_program_or_batch_by_admin_id() {

		response = RestAssured.given()
	            .header("Authorization", "Bearer " + IdHolder.getToken())
	            .pathParam("userId", IdHolder.getUserId())
	            .get(routes.getString("base_url") + routes.getString("Get_AssignedProgram/BatchofaUserByUserId"));

	Assert.assertEquals(200, response.getStatusCode());

	String jsonResponse = response.getBody().asString();
	System.out.println("JSON Response: " + jsonResponse);
	}

	@When("Admin creates a GET Request to retrieve Admin assigned to Program or Batch by invalid AdminID")
	public void admin_creates_a_get_request_to_retrieve_admin_assigned_to_program_or_batch_by_invalid_admin_id() throws IOException {
	
		List<String> invalidUserIds = userControllerData.getInvalidUserIds();
		for (String invalidUserId : invalidUserIds) {
		    try {
		        IdHolder.setUserId(invalidUserId); 
		        Response response = RestAssured.given()
		                .header("Authorization", "Bearer " + IdHolder.getToken())
		                .pathParam("userId", IdHolder.getUserId()) 
		                .get(routes.getString("base_url") + routes.getString("Get_AssignedProgram/BatchofaUserByUserId"));
		        Assert.assertEquals(404, response.getStatusCode());
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

        }
    
	@Then("Admin receives a {int} response")
	public void admin_receives_a_response(Integer int1) {
		
	}

	@When("Admin creates a GET Request to retrieve Admin assigned to Program or Batch by valid AdminID")
	public void admin_creates_a_get_request_to_retrieve_admin_assigned_to_program_or_batch_by_valid_admin_id() {
		Response response = RestAssured.given()
                .pathParam("userId", IdHolder.getUserId()) 
                .get(routes.getString("base_url") + routes.getString("Get_AssignedProgram/BatchofaUserByUserId"));
        Assert.assertEquals(401, response.getStatusCode());
	}

}
