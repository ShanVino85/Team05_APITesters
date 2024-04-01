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

public class DeleteUserStepDefinition extends RestUtils {
	private UserControllerData userControllerData = new UserControllerData();
	Logger logger = LogManager.getLogger(" DeleteUserStepDefinition.java");
	@Given("Admin set authentication with token to perform delete")
	public void admin_set_authentication_with_token_to_perform_delete() {
		logger.info("======Admin sets Authorization to bearer token======");
	}

	@When("Admin sends Delete request to perfom delete in user module for delete reaquest")
	public void admin_sends_delete_request_to_perfom_delete_in_user_module_for_delete_reaquest() {
		logger.info("======Admin send delete request to Delete user using userid ======");
		
		response = RestAssured.given()
	            .header("Authorization", "Bearer " + IdHolder.getToken())
	            .pathParam("userID", IdHolder.getUserId())
	            .get(routes.getString("base_url") + routes.getString("delete_DeleteUser"));

	}

	@Then("the Admin receives a {int} OK status code and a success message user deletd")
	public void the_admin_receives_a_ok_status_code_and_a_success_message_user_deletd(Integer int1) {
		logger.info("======Admin receives sucessful maessage======");
	}

	@Given("Admin is authenticated with a bearer token to delete user in user module")
	public void admin_is_authenticated_with_a_bearer_token_to_delete_user_in_user_module() throws IOException {
		logger.info("======Admin send invalid delete request to Delete user using userid ======");
		List<String> invalidUserIds = userControllerData.getInvalidUserIds();
		for (String invalidUserId : invalidUserIds) {
		    try {
		        IdHolder.setUserId(invalidUserId); 
		        Response response = RestAssured.given()
		                .header("Authorization", "Bearer " + IdHolder.getToken())
		                .pathParam("userID", IdHolder.getUserId()) 
		                .get(routes.getString("base_url") + routes.getString("delete_DeleteUser"));
		        Assert.assertEquals(404, response.getStatusCode());
		    } catch (Exception e) {
		        e.printStackTrace();
		    }}
	}

	@When("Admin sends a DELETE request to delete an Admin with an invalid user ID")
	public void admin_sends_a_delete_request_to_delete_an_admin_with_an_invalid_user_id() {
	   
	}

	@Then("the Admin receives a {int} Not Found error message")
	public void the_admin_receives_a_not_found_error_message(Integer int1) {
		logger.info("======Invalid user or request Uncessfull delete ======");
	}

	@Given("Admin is not authenticated")
	public void admin_is_not_authenticated() {
		logger.info("====== user sets no auth ======");
	}

	@When("Admin sends a DELETE request to delete an Admin by ID")
	public void admin_sends_a_delete_request_to_delete_an_admin_by_id() {
		logger.info("======Admin send delete request to Delete user using userid ======");
		Response response = RestAssured.given()
                .pathParam("userID", IdHolder.getUserId()) 
                .get(routes.getString("base_url") + routes.getString("delete_DeleteUser"));
		Assert.assertEquals(401, response.getStatusCode());
		
	}

	@Then("the Admin receives a {int} Unauthorized error message")
	public void the_admin_receives_a_unauthorized_error_message(Integer int1) {
		logger.info("======Admin receives unauthorized delete request  ======");
	}




}
