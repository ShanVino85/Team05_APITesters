package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.utils.IdHolder;
import api.utils.RestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import api.resourses.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
public class UserLogin extends RestUtils {
	
	Logger logger = LogManager.getLogger("UserLogin.java");

	UserLogindata UserLogindata = new UserLogindata();
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	private static String token;

	// @Test01
	@Given("Add UserLogin Payload")
	public void add_user_login_payload() throws FileNotFoundException, IOException {

		logger.info("Post Request sent with valid request body");
		request = given().spec(requestSpecification()).body(UserLogindata.dataBuild());
		
	}

	@When("Admin calls Post Https method  with valid endpoint")
	public void admin_calls_post_https_method_with_valid_endpoint() {

		
		response = request.when().post(routes.getString("Post_UserLoginurl"))
				.then()
				.log().all().extract().response();
		System.out.println(response);
		
		// extract token value from response and saving it in RestUtils
		if (response.getStatusCode() == 200) {
			token = UserKeyJson(response, "token");
			System.out.println("token extracted from response:" + token);
			//RestUtils.bearerToken = token;
			IdHolder.bearerToken = token;
			System.out.println("**********token saved*****: " + IdHolder.bearerToken);
		}
		
	}

	@Then("Admin receives {int} OK with auto generated token")
	public void admin_receives_OK_with_auto_generated_token(int statusCode) {

		assertEquals(statusCode,response.getStatusCode());
		logger.info("----User successfully created token------:" + IdHolder.bearerToken);
		response
		.then()
	    .assertThat().body(matchesJsonSchemaInClasspath("Schema/UserLogin/UserLoginSchema.json"));
	}

	// @TestN1 <----- POST REQUEST 401 UNAUTHORIZED--------->
	@When("Admin calls Post Https method  with invalid endpoint")
	public void admin_calls_post_https_method_with_invalid_endpoint() {
		response = request.when().post(routes.getString("invalid_endpoint")).then().log().all().extract().response();
		logger.info("Post Request sent with invalid Endpoint");
	}

	@Then("Admin receives {int} unauthorized")
	public void admin_receives_unauthorized(Integer int1) {
		assertEquals(401,response.getStatusCode());
	}

	// @TestN2 <----- POST REQUEST 401 UNAUTHORIZED--------->
	@Given("Admin creates request with invalid credentials")
	public void admin_creates_request_with_invalid_credentials() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()).body(UserLogindata.invaliddataBuild());
	}

	@Then("Admin receives {int} Bad request")
	public void admin_receives_bad_request(Integer int1) {
		assertEquals(401,response.getStatusCode());
	}
	
	// @TestN3 <----- POST REQUEST 400 BAD REQUEST--------->
	@Given("Add creates request with missing mandatory field")
	public void add_creates_request_with_missing_mandatory_field() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()).body(UserLogindata.missingdataBuild());
		logger.info("Post Request sent with missing field info");
	}

	@Then("Admin receives status {int} Bad request")
	public void admin_receives_status_bad_request(Integer int1) {
		assertEquals(400,response.getStatusCode());
	}

}
