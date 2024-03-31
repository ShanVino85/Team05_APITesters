package api.stepdefinitions;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import api.resourses.*;
import api.utils.RestUtils;
import api.utils.IdHolder;
import api.utils.LoggerLoad;
//import api.utils.*;
public class UserModule extends RestUtils {
	
	 Logger logger = LogManager.getLogger("UserModule.java");
	 
	
	UserModuledata UserModuledata= new UserModuledata(); 
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	String asString;
	public static String token;
	//LoggerLoad log = new LoggerLoad();
	 
	
	

	
	@Given("Admin creates POST request with all mandatory fields")
	public void admin_creates_post_request_with_all_mandatory_fields() throws FileNotFoundException, IOException {
		 
		//request=given().spec(requestSpecification()).header("Authorization","Bearer "+TokenHolder.token);
		request=given().spec(requestSpecification()).body( UserModuledata.usermodulePostdata()).header("Authorization","Bearer "+IdHolder.token);
		 System.out.println(IdHolder.token );
		 
		
		 logger.info("===========POST request with all mandatory fields=====================  ");
	}

	@When("Admin sends HTTPS Request with endpoint")
	public void admin_sends_https_request_with_endpoint() {
		 response = request.when().post(routes.getString("Post_Userurl"));
		 asString = response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/UserModule/PostSchema.json")).log().all().extract().asString();
		 
		 IdHolder.userId =  UserKeyJson(response,"userId");
		  System.out.println("userId ="  +IdHolder.userId);
		  
		  logger.info("===========User POST request =====================  ");
	}

	@Then("Admin receives {int} Created Status with response body.")
	public void admin_receives_created_status_with_response_body(int int1) {
		assertEquals(int1,response.getStatusCode());
	}
	
	
	
	@Given("Admin creates GET Request with active and inactive Admins endpoint")
	public void admin_creates_get_request_with_active_and_inactive_admins_endpoint() throws FileNotFoundException {
	   	
		request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).queryParam("Id", "all/R01/R02/R03");;
		// response = request.when().post(routes.getString("Post_Userurl"));
		logger.info("=========== Get count of active and inactive users  HTTPS Request =====================  ");	
	}
	
	@When("Admin sends Get count of active and inactive users  HTTPS Request with endpoint")
	public void admin_sends_get_count_of_active_and_inactive_users_https_request_with_endpoint() {
		 response = request
					.when().get(routes.getString("Get_countofactiveandinactiveusers"))
					.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/UserModule/GetActiveandinactiveSchema.json"))
					.spec(resSpecBuilder()).statusCode(200).log().all().extract().response();
	}

	@Then("Admin receives {int} OK")
	public void admin_receices_ok(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}
	
	
	//(===========Get All Active Users HTTPS Request=====================)  	
	
	@Given("Admin creates GET Request")
	public void admin_creates_get_request() throws FileNotFoundException {
		
		request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token);
		// response = request.when().post(routes.getString("Post_Userurl"));
		
	}
	
	@When("Admin sends Get All Active Users HTTPS Request with endpoint")
	public void admin_sends_get_all_active_users_https_request_with_endpoint() {
	   
		response = request
				.when().get(routes.getString("Get_AllActiveUsers"))
				.then()
				.spec(resSpecBuilder()).statusCode(200).log().all().extract().response();
		logger.info("=========== Get All Active Users HTTPS Request=====================  ");
	}
	
	//(===========Assign Update User Role Program Batch Status=====================)  
	
	@Given("Admin creates PUT Request with valid data in request body")
	public void admin_creates_put_request_with_valid_data_in_request_body() throws FileNotFoundException {
		request=given().spec(requestSpecification()).body(UserModuledata.UpdateUserRoleProgramBatchStatus(IdHolder.programId, IdHolder.batchId))
				.header("Authorization","Bearer "+IdHolder.token).pathParam("userId", IdHolder.userId);
	}

	@When("Admin sends PUT HTTPS Request with User Role Program Batch Status endpoint")
	public void admin_sends_put_https_request_with_user_role_program_batch_status_endpoint() {
	   
		response = request
				.when().put(routes.getString("update_UpdateUserRoleProgramBatch"))
				.then().spec(resSpecBuilder()) 
				.statusCode(200).log().all().extract().response();
	}

	
	
//	(=========== Get User by Program Batches=====================)	
	
	@Given("Admin creates GET Request with valid batch Id")
	public void admin_creates_get_request_with_valid_batch_id() throws FileNotFoundException {
	   
		request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("Id",IdHolder.batchId);
	
	}
//8635
	@When("Admin sends Get User by Program Batches HTTPS Request with endpoint")
	public void admin_sends_get_user_by_program_batches_https_request_with_endpoint() {
		
		response = request
				.when().get(routes.getString("Get_UserbyProgramBatches"))
				.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/UserModule/GetProgramBatchSchema.json"))
				.spec(resSpecBuilder()).statusCode(200).log().all().extract().response();
		logger.info("=========== Get User by Program Batches=====================  ");
	}
	
	//(=========== Gets Users for Program===================== );	
	
	@Given("Admin creates GET Request with valid program Id")
	public void admin_creates_get_request_with_valid_program_id() throws FileNotFoundException {
	    
		request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("programId", IdHolder.programId);
		
		
	}
//16765
	@When("Admin sends Gets Users for Program HTTPS Request with endpoint")
	public void admin_sends_gets_users_for_program_https_request_with_endpoint() {
	   
		response = request
				.when().get(routes.getString("Get_UsersforProgram"))
				.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/UserModule/GetProgramBatchSchema.json"))
				.spec(resSpecBuilder()).statusCode(200).log().all().extract().response();
		logger.info("=========== Gets Users for Program=====================  ");
	}

	//(=========== Gets Users with valid role ID=====================  );
	
	@Given("Admin creates GET Request with valid role ID")
	public void admin_creates_get_request_with_valid_role_id() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("roleId","R03");
	}

	@When("Admin sends Gets Users by roleId HTTPS Request with endpoint")
	public void admin_sends_gets_users_by_role_id_https_request_with_endpoint() {
		response = request
				.when().get(routes.getString("Get_UsersbyroleId"))
				.then()
				.spec(resSpecBuilder()).statusCode(200).log().all().extract().response();
		
		logger.info("=========== Gets Users with valid role ID=====================  ");
	}
	
	//(=========== Negative TestCases=====================  );
	
	@Given("Admin creates authorization GET Request")
public void admin_creates_authorization_get_request() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization","Basic "+IdHolder.token);
		
		logger.info("===========Negative TestCases=====================  ");
	}

	
	
	@When("Admin sends HTTPS Request with endpoint	no authorization")
	public void admin_sends_https_request_with_endpoint_no_authorization() {
		 response = request
					.when().get(routes.getString("Get_countofactiveandinactiveusers"))
					.then()
					.spec(resSpecBuilder()).statusCode(401).log().all().extract().response();
		 logger.info("===========Negative no authorization =====================  ");
	  	}
	

	@When("Admin sends HTTPS Request with endpoint with invalid endpoint")
	public void admin_sends_https_request_with_endpoint_with_invalid_endpoint() throws FileNotFoundException {
	    
		 response =request
					.when().get(routes.getString("invalidUrl"))
					.then()
					.spec(resSpecBuilder()).statusCode(404).log().all().extract().response();
		 
		 logger.info("===========Negative with invalid endpoint =====================  ");
	}

	@Then("Admin receives status {int} with Not Found error message	Negative")
	public void admin_receives_status_with_not_found_error_message_negative(Integer int1) {
		assertEquals(404,response.getStatusCode());
	}
	
	@Given("Admin creates GET Request with active and inactive Admins Negative endpoint")
	public void admin_creates_get_request_with_active_and_inactive_admins_negative_endpoint() throws FileNotFoundException {
		
		request=given().spec(requestSpecification()).header("Authorization","Bearer"+IdHolder.token).queryParam("Id", "R3");
		
		
	}

	@When("Admin sends HTTPS Request with endpoint	invalid role ID")
	public void admin_sends_https_request_with_endpoint_invalid_role_id() {
	    
		 response = request
					.when().get(routes.getString("Get_countofactiveandinactiveusers"))
					.then()
					.spec(resSpecBuilder()).statusCode(401).log().all().extract().response();
		 
		 logger.info("===========Negative of active and inactive Admins with invalid role ID =====================  ");
	}
	@Then("Admin receives status {int} with unauthorized error message	Negative")
	public void admin_receives_status_with_unauthorized_error_message_negative(Integer int1) {
		assertEquals(401,response.getStatusCode());
	}
	
	@When("Admin sends HTTPS Request with all active Admins with invalid endpoint")
	public void admin_sends_https_request_with_all_active_admins_with_invalid_endpoint() {
		 response = request
					.when().get(routes.getString("invalidUrl"))
					.then()
					.spec(resSpecBuilder()).statusCode(404).log().all().extract().response();
		 
		 logger.info("===========Negative with invalid endpoint =====================  ");
		 
}
	
	@Given("Admin creates GET Request  with invalid batchId")
	public void admin_creates_get_request_with_invalid_batch_id() throws FileNotFoundException {
	   
		request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("Id", 167);
	}
	
	@When("Admin sends GET Request  with invalid batchId by Program Batches endpoint")
	public void admin_sends_get_request_with_invalid_batch_id_by_program_batches_endpoint() {
		response = request
				.when().get(routes.getString("Get_UserbyProgramBatches"))
				.then()
				.spec(resSpecBuilder()).statusCode(404).log().all().extract().response();
		
		logger.info("===========Negative with invalid batchId =====================  ");
	}
	
	

//	@When("Admin sends HTTPS Request with valid batch ID with invalid endpoint")
//	public void admin_sends_https_request_with_valid_batch_id_with_invalid_endpoint() {
//		 response = request
//				.when().get(routes.getString("invalidUrl"))
//			.then()
//				.spec(resSpecBuilder()).log().all().extract().response();
//	    
//	}
	
	@Given("Admin creates GET Request with invalid program Id")
	public void admin_creates_get_request_with_invalid_program_id() throws FileNotFoundException {
	    
		request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("programId", 6765);
	}

	@When("Admin sends GET Request  with invalid program Id by Program  endpoint")
	public void admin_sends_get_request_with_invalid_program_id_by_program_endpoint() {
		
		response = request
				.when().get(routes.getString("Get_UsersforProgram"))
				.then()
				.spec(resSpecBuilder()).statusCode(404).log().all().extract().response();
		
		logger.info("===========Negative with invalid ProgramId =====================  ");
	}
  
	@Given("Admin creates GET Request for GET with invalid role ID")
	public void admin_creates_get_request_for_get_with_invalid_role_id() throws FileNotFoundException {
	     
		request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("roleId","R05");
	}

	@When("Admin sends GET HTTPS Request  endpoint with	invalid role ID")
	public void admin_sends_get_https_request_endpoint_with_invalid_role_id() {
		response = request
				.when().get(routes.getString("Get_UsersbyroleId"))
				.then()
				.spec(resSpecBuilder()).statusCode(404).log().all().extract().response();
		
		logger.info("===========Negative with invalid role ID endpoint =====================  ");
	}

	
}
