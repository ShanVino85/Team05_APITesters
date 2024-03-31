package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import api.utils.IdHolder;
import api.utils.RestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UserModule_Negative extends RestUtils {
	
//	RequestSpecification request;
//	ResponseSpecification responseSpec;
//	Response response;
//	
//	
//	@Given("Admin creates authorization GET Request")
//	public void admin_creates_authorization_get_request() throws FileNotFoundException {
//		request=given().spec(requestSpecification()).header("Authorization","Basic "+IdHolder.token);
//	}
//
//	
//	
//	@When("Admin sends HTTPS Request with endpoint	no authorization")
//	public void admin_sends_https_request_with_endpoint_no_authorization() {
//		 response = request
//					.when().get(routes.getString("Get_countofactiveandinactiveusers"))
//					.then()
//					.spec(resSpecBuilder()).log().all().extract().response();
//	    
//	}
//	
//
//	@When("Admin sends HTTPS Request with endpoint with invalid endpoint")
//	public void admin_sends_https_request_with_endpoint_with_invalid_endpoint() throws FileNotFoundException {
//	    
//		 response =request
//					.when().get(routes.getString("invalidUrl"))
//					.then()
//					.spec(resSpecBuilder()).log().all().extract().response();
//	}
//
//	@Then("Admin receives status {int} with Not Found error message	Negative")
//	public void admin_receives_status_with_not_found_error_message_negative(Integer int1) {
//		assertEquals(response.getStatusCode(),404);
//	}
//	
//	@Given("Admin creates GET Request with active and inactive Admins Negative endpoint")
//	public void admin_creates_get_request_with_active_and_inactive_admins_negative_endpoint() throws FileNotFoundException {
//		
//		request=given().spec(requestSpecification()).header("Authorization","Bearer"+IdHolder.token).queryParam("Id", "R04");;
//	}
//
//	@When("Admin sends HTTPS Request with endpoint	invalid role ID")
//	public void admin_sends_https_request_with_endpoint_invalid_role_id() {
//	    
//		 response = request
//					.when().get(routes.getString("Get_countofactiveandinactiveusers"))
//					.then()
//					.spec(resSpecBuilder()).log().all().extract().response();
//	}
//

}
