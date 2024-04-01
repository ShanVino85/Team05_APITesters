package api.stepdefinitions;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.pojo.UserLoginstatusPojo;
import api.resourses.UserModuleData;
import api.resourses.UserloginstatusData;
import api.utils.IdHolder;
import api.utils.RestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserModule extends RestUtils {
	private String auth;
	private String requestBody;
	Logger logger = LogManager.getLogger("UserModule.java");
	List<UserLoginstatusPojo> requestBodies = UserloginstatusData.buildUserRequestBodies("sheet5");
	@Given("Admin sets authorization to bearer token For Update Admin Login Status feature")
	public void admin_sets_authorization_to_bearer_token_for_update_admin_login_status_feature() {
		logger.info("======Admin sets Authorization to bearer token======");
		auth = "Bearer " + IdHolder.getToken();
	}

	@Given("Admin creates PUT Request with valid data in request body to update Admin Login status")
	public void admin_creates_put_request_with_valid_data_in_request_body_to_update_admin_login_status() {
		requestBodies = UserloginstatusData.buildUserRequestBodies("sheet5");
	
	}

	@When("Admin sends HTTPS Request with endpoint to update Admin Login status")
	public void admin_sends_https_request_with_endpoint_to_update_admin_login_status() {
		Response response = RestAssured.given()
	            .header("Authorization", auth)
	            .contentType(ContentType.JSON)
	            .pathParam("userID", "U649")
	            .body(requestBodies)
	            .put(routes.getString("base_url") + routes.getString("update_UserRoleID")); // Use put() instead of post()
	    
	    String jsonResponse = response.getBody().asString();
	    System.out.println(jsonResponse);
	    
	}

	@Then("Admin receives {int} OK with Sucessful update of admin login status")
	public void admin_receives_ok_with_sucessful_update_of_admin_login_status(Integer int1) {
	    
	}

	@Given("Admin creates PUT Request with invalid data in request body to update Admin Login status")
	public void admin_creates_put_request_with_invalid_data_in_request_body_to_update_admin_login_status() {
	    
	}

	@When("Admin sends HTTPS request and invalid data in request body to update Admin Login status")
	public void admin_sends_https_request_and_invalid_data_in_request_body_to_update_admin_login_status() {
	    
	}

	@Then("Admin receives {int} Bad Request Status with failure message update of admin login status")
	public void admin_receives_bad_request_status_with_failure_message_update_of_admin_login_status(Integer int1) {
	    
	}

	@Given("Admin creates PUT Request with valid data in request body to update Admin Login status with invalid Adminid")
	public void admin_creates_put_request_with_valid_data_in_request_body_to_update_admin_login_status_with_invalid_adminid() {
	    
	}

	@When("Admin sends HTTPS Request with invalid AdminId to update Admin Login status")
	public void admin_sends_https_request_with_invalid_admin_id_to_update_admin_login_status() {
	    
	}

	@Then("Admin receives status {int} with Admin Not Found error message to update")
	public void admin_receives_status_with_admin_not_found_error_message_to_update(Integer int1) {
	    
	}
	@Given("Admin creates invalid PUT Request with valid data in request body to update Admin Login status")
	public void admin_creates_invalid_put_request_with_valid_data_in_request_body_to_update_admin_login_status() {
	    
	}

	@When("Admin sends HTTPS Request with invalid endpoint to update Admin Login status")
	public void admin_sends_https_request_with_invalid_endpoint_to_update_admin_login_status() {
	    
	}
	@Then("Admin receives {int} OK with invalid endpoint to update  admin login status")
	public void admin_receives_ok_with_invalid_endpoint_to_update_admin_login_status(Integer int1) {
	    
	}



}
