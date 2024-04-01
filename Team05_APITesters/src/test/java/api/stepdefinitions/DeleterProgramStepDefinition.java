package api.stepdefinitions;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import api.resourses.UserControllerData;
import api.utils.IdHolder;
import api.utils.RestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleterProgramStepDefinition extends RestUtils{
	Logger logger = LogManager.getLogger("DeleterProgramStepDefinition");
	private UserControllerData userControllerData = new UserControllerData();
	@Given("Admin creates a DELETE Request to delete Admin assigned to program\\/batch by AdminId")
	public void admin_creates_a_delete_request_to_delete_admin_assigned_to_program_batch_by_admin_id() {
		logger.info("======Admin send delete request to Delete program using valid userid ======");
		response = RestAssured.given()
	            .header("Authorization", "Bearer " + IdHolder.getToken())
	            .pathParam("userId", IdHolder.getUserId())
	            .get(routes.getString("base_url") + routes.getString("delete_AllPrograms/BatchesByUserId"));
	}

	@Then("Admin receives a {int} OK response for successful deletion of program")
	public void admin_receives_a_ok_response_for_successful_deletion_of_program(Integer int1) {
		String jsonResponse = response.getBody().asString();
		System.out.println("JSON Response: " + jsonResponse);
	}

	@Given("Admin creates a DELETE Request to delete Admin assigned to program\\/batch by invalid AdminId")
	public void admin_creates_a_delete_request_to_delete_admin_assigned_to_program_batch_by_invalid_admin_id() throws IOException {
		logger.info("======Admin send delete request to Delete program using Invalid userid ======");
		List<String> invalidUserIds = userControllerData.getInvalidUserIds();
		for (String invalidUserId : invalidUserIds) {
		    try {
		        IdHolder.setUserId(invalidUserId); 
		        Response response = RestAssured.given()
		                .header("Authorization", "Bearer " + IdHolder.getToken())
		                .pathParam("userId", IdHolder.getUserId()) 
		                .get(routes.getString("base_url") + routes.getString("delete_AllPrograms/BatchesByUserId"));
		        Assert.assertEquals(405, response.getStatusCode());
		       
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

	}

	@Then("Admin receives a {int} response with invalid id to delete program")
	public void admin_receives_a_response_with_invalid_id_to_delete_program(Integer int1) {
	    
	}

	@When("Admin creates a DELETE Request to delete Admin assigned to program\\/batch by valid AdminId")
	public void admin_creates_a_delete_request_to_delete_admin_assigned_to_program_batch_by_valid_admin_id() {
		
		logger.info("======Admin send delete request to Delete program using valid userid without authorization ======");
		Response response = RestAssured.given()
                .pathParam("userId", IdHolder.getUserId()) 
                .get(routes.getString("base_url") + routes.getString("delete_AllPrograms/BatchesByUserId"));
		Assert.assertEquals(401, response.getStatusCode());
	}

	@Then("Admin receives a status {int} with Unauthorized message to delete program for no auth")
	public void admin_receives_a_status_with_unauthorized_message_to_delete_program_for_no_auth(Integer int1) {
		
	}



}
