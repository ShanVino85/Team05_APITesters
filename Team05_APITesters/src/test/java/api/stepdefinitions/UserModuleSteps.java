package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import api.resourses.UserPostAddidata;
import api.resourses.UserPostReqdata;
import api.resourses.UserPostInvaliddata;
import api.resourses.UserpostMissManddata;
import api.utils.RestUtils;
import api.utils.IdHolder;
import api.utils.Logger;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UserModuleSteps extends RestUtils {
	
	private static org.apache.logging.log4j.Logger logger= LogManager.getLogger();
	UserPostReqdata UserPostReqdata= new UserPostReqdata(); 
	UserPostAddidata UserPostAddidata= new UserPostAddidata();
	UserPostInvaliddata UserPostInvaliddata= new UserPostInvaliddata();
	UserpostMissManddata UserpostMissManddata=new UserpostMissManddata();
	
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;

	@Given("Admin creates POST request with all mandatory fields")
	public void admin_creates_post_request_with_all_mandatory_fields() throws FileNotFoundException, IOException {
		request=given()
				.spec(requestSpecification())
				.headers("Authorization","Bearer "+IdHolder.token)
				.body( UserPostReqdata.dataBuild());
		logger.info("Create User with Mandatory Fields");
		
	}

	@When("Admin sends HTTPS Request with endpoint")
	public void admin_sends_https_request_with_endpoint() {
		logger.info("Request sent with valid Endpoint and request body");
		response = request.when()
				 .post(routes.getString("Post_Userurl"))
				 .then().log().all().extract().response();
		    System.out.println(response);
			IdHolder.userId =  UserKeyJson(response,"userId");
			
			System.out.println("******UserId Stored" + IdHolder.userId);	
	}

	@Then("Admin receives {int} Created Status with response body")
	public void admin_receives_created_status_with_response_body(Integer int1) {
	   	assertEquals(response.getStatusCode(),201);
		assertEquals(response.header("Content-Type"),"application/json");
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/UserCreationSchema.json"));
		logger.info("User Schema Validation is successful");
		}
	@Given("Admin creates POST request with all mandatory fields and an additional fields")
	public void admin_creates_post_request_with_all_mandatory_fields_and_an_additional_fields() throws FileNotFoundException, IOException {
		request=given()
				.spec(requestSpecification())
				.headers("Authorization","Bearer "+IdHolder.token)
				.body(UserPostAddidata.dataBuild());
		logger.info("Create User with Mandatory & additional Fields");
	}

	@Given("Admin creates POST request with all mandatory fields and additional fields")
	public void admin_creates_post_request_with_all_mandatory_fields_and_additional_fields() throws FileNotFoundException, IOException {
		request=given()
				.spec(requestSpecification())
				.headers("Authorization","Bearer "+IdHolder.token)
				.body(UserPostInvaliddata.dataBuild());
		      logger.info("Create User with Invalid data");
	}

	@Then("Admin receives {int} Bad Request Status with message and boolean success details")
	public void admin_receives_bad_request_status_with_message_and_boolean_success_details(Integer int1) {
	   assertEquals(400,response.getStatusCode());
	}

	@Given("Admin creates POST request with missing mandatory fields in request body")
	public void admin_creates_post_request_with_missing_mandatory_fields_in_request_body() throws FileNotFoundException, IOException {
		request=given()
				.spec(requestSpecification())
				.headers("Authorization","Bearer "+IdHolder.token)
				.body(UserpostMissManddata.dataBuild());
		    logger.info("Create User with Missing data");
	}

	@Then("Admin receives {int} Bad Request Status with error message")
	public void admin_receives_bad_request_status_with_error_message(Integer int1) {
		assertEquals(400,response.getStatusCode());
	}
	
	@Given("Admin creates POST request with all mandatory fields and additional fields with No Auth")
	public void admin_creates_post_request_with_all_mandatory_fields_and_additional_fields_with_no_auth() throws FileNotFoundException, IOException {
		request=given()
				.spec(requestSpecification())
				//.headers("Authorization","Bearer "+IdHolder.token)
				.body( UserPostReqdata.dataBuild());
	}


	@Then("Admin receives status {int} with Unauthorized message")
	public void admin_receives_status_with_unauthorized_message(Integer int1) {
		assertEquals(401,response.getStatusCode());
	}


	// Get All Roles
	@Given("Admin creates GET Request")
	public void admin_creates_get_request() throws FileNotFoundException, IOException {
		request=given()
				.spec(requestSpecification())
				.headers("Authorization","Bearer "+IdHolder.token);
		     	
	}

	@When("Admin sends HTTPS Request with GET All Roles endpoint")
	public void admin_sends_https_request_with_get_all_roles_endpoint() {
		response = request.when()
				 .get(routes.getString("Get_AllRoles"))
				 .then().log().all().extract().response();
		    System.out.println(response);
		    logger.info("Request to get all Roles");
			
	}
	@Then("Admin receives {int} OK")
	public void admin_receives_ok(Integer int1) {
		assertEquals(response.getStatusCode(),200);
		assertEquals(response.header("Content-Type"),"application/json");
		response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/Usergetallroles_schema.json"));
		logger.info("User Get Roles schema validation successful");
		
	}
// Get All Users
	@When("Admin sends HTTPS Request with valid endpoint")
	public void admin_sends_https_request_with_valid_endpoint() {
	   		response = request.when()
				 .get(routes.getString("Get_Alluser"))
				 .then().log().all().extract().response();
		    System.out.println(response);
		    logger.info("Request to get all Users");
	}

	@Then("Admin receives {int} OK Status with response body")
	public void admin_receives_ok_status_with_response_body(Integer int1) {
			
		assertEquals(response.getStatusCode(),200);
		assertEquals(response.header("Content-Type"),"application/json");
	}
	@Given("Admin creates GET Request with valid AdminId")
	public void admin_creates_get_request_with_valid_admin_id() throws FileNotFoundException, IOException {
		request=given()
				.spec(requestSpecification())
				.headers("Authorization","Bearer "+IdHolder.token)
				.pathParam("id", IdHolder.userId);
					
	}
    //Get User by UserID
	@When("Admin sends HTTPS Request with a valid endpoint")
	public void admin_sends_https_request_with_a_valid_endpoint() {
		response = request.when()
				 .get(routes.getString("Get_UserinformationbyUserId"))
				 .then().log().all().extract().response();
		    System.out.println(response);
		logger.info("Request to get User by UserId");
	}

	@When("Admin sends HTTPS Request with an endpoint")
	public void admin_sends_https_request_with_an_endpoint() {
		response = request.when()
				 .get(routes.getString("Get_allUserswithroles"))
				 .then().log().all().extract().response();
		    System.out.println(response);
	}
	@Given("Admin creates GET Request without Authorization")
	public void admin_creates_get_request_without_authorization() throws FileNotFoundException {
		request=given()
				.spec(requestSpecification());
	}
	@When("Admin sends HTTPS Request with invalid endpoint")
	public void admin_sends_https_request_with_invalid_endpoint() {
		response = request.when()
				 .get(routes.getString("Invalid_Endpoint"))
				 .then().log().all().extract().response();
		    System.out.println(response);
	}

	@Then("Admin receives status {int} with Not Found error message")
	public void admin_receives_status_with_not_found_error_message(Integer int1) {
		assertEquals(404,response.getStatusCode());
		assertEquals(response.header("Content-Type"),"application/json");
	}

	@Given("Admin creates GET Request with invalid AdminId")
	public void admin_creates_get_request_with_invalid_admin_id() throws FileNotFoundException {
		request=given()
				.spec(requestSpecification())
				.headers("Authorization","Bearer "+IdHolder.token)
				.pathParam("id", IdHolder.invalidId);
	}
	// Get all users with Roles
	@When("Admin sends HTTPS Request with endpoint as Alluserswithroles")
	public void admin_sends_https_request_with_endpoint_as_alluserswithroles() {
		response = request.when()
				 .get(routes.getString("Get_allUserswithroles"))
				 .then().log().all().extract().response();
		    System.out.println(response);
		    Logger.info("Request to get all users with Roles");
	}
	@Given("Admin creates GET Request with valid AdminId and with No Auth")
	public void admin_creates_get_request_with_valid_admin_id_and_with_no_auth() throws FileNotFoundException {
		request=given()
				.spec(requestSpecification())
				//.headers("Authorization","Bearer "+IdHolder.token)
				.pathParam("id", IdHolder.userId);
	}

@When("Admin sends HTTPS Request with invalid endpointId")
public void admin_sends_https_request_with_invalid_endpoint_id() {
	response = request.when()
			 .get(routes.getString("Invalid_Endpoint_Id"))
			 .then().log().all().extract().response();
	    System.out.println(response);
}



	
}
