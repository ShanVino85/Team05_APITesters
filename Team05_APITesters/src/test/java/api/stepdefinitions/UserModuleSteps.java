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
	api.resourses.UserModuledata UserModuledata=new api.resourses.UserModuledata();
	//userPUTdata userPUTdata=new userPUTdata();
	
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
		//response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/Usergetallroles_schema.json"));
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
				.pathParam("id", IdHolder.userId);
	}

@When("Admin sends HTTPS Request with invalid endpointId")
public void admin_sends_https_request_with_invalid_endpoint_id() {
	response = request.when()
			 .get(routes.getString("Invalid_Endpoint_Id"))
			 .then().log().all().extract().response();
	    System.out.println(response);
}
// #( Get count of active and inactive Admins with Invalid endpoint
@When("Admin sends HTTPS Request with endpoint with invalid endpoint")
public void admin_sends_https_request_with_endpoint_with_invalid_endpoint() throws FileNotFoundException {
    
	 response =request
				.when().get(routes.getString("invalidUrl"))
				.then().log().all().extract().response();
				//.spec(resSpecBuilder()).statusCode(404).log().all().extract().response();
	 
	 logger.info("===========Negative with invalid endpoint =====================  ");
}
//(====================================active and inactive Admins by invalid role ID===========================================  )	 

	@Given("Admin creates GET Request with active and inactive Admins Negative endpoint")
	public void admin_creates_get_request_with_active_and_inactive_admins_negative_endpoint() throws FileNotFoundException {
		
		request=given().spec(requestSpecification()).header("Authorization","Bearer"+IdHolder.token).queryParam("Id", "R3");
	}	
		
   @When("Admin sends HTTPS Request with endpoint  invalid role ID")
public void admin_sends_https_request_with_endpoint_invalid_role_id() {
	response = request
			.when().get(routes.getString("Get_countofactiveandinactiveusers"))
			.then().log().all().extract().response();
			//.spec(resSpecBuilder()).statusCode(401).log().all().extract().response();
 
 logger.info("===========Negative of active and inactive Admins with invalid role ID =====================  ");
}

@Then("Admin receives status {int} with unauthorized error message  Negative")
public void admin_receives_status_with_unauthorized_error_message_negative(Integer int1) {
	assertEquals(401,response.getStatusCode());
}

//(==================================== active and inactive Admins with no authorization	===========================================  )

	@Given("Admin creates no authorization GET Request")
public void admin_creates_authorization_get_request() throws FileNotFoundException {
		request=given().spec(requestSpecification());
		
		logger.info("===========Negative TestCases=====================  ");
	}

	
	@When("Admin sends HTTPS Request with endpoint no authorization")
	public void admin_sends_https_request_with_endpoint_no_authorization() {
		response = request
				.when().get(routes.getString("Get_countofactiveandinactiveusers"))
				.then().log().all().extract().response();
				//.spec(resSpecBuilder()).statusCode(401).log().all().extract().response();
	 logger.info("===========Negative no authorization =====================  ");
	}

	//(====================================Get All Active Users===========================================  )	 
		@When("Admin sends HTTPS Request with all active Admins with invalid endpoint")
		public void admin_sends_https_request_with_all_active_admins_with_invalid_endpoint() {
			 response = request
						.when().get(routes.getString("invalidUrl"))
						.then().log().all().extract().response();
						//.spec(resSpecBuilder()).statusCode(404).log().all().extract().response();
			 
			 logger.info("===========Negative with invalid endpoint =====================  ");
			 
	}

		@When("Admin sends HTTPS Request with endpoint with no auth")
		public void admin_sends_https_request_with_endpoint_with_no_auth() {
			 response = request
						.when().get(routes.getString("Get_AllActiveUsers"))
						.then().log().all().extract().response();
						//.spec(resSpecBuilder()).statusCode(401).log().all().extract().response();
		 }

		@Then("Admin receives status {int} with Unauthorized")
		public void admin_receives_status_with_unauthorized(Integer int1) {
			assertEquals(401,response.getStatusCode());
		}
		
		//(====================================Get invalid batchId===========================================  )	
		@Given("Admin creates GET Request  with invalid batchId")
		public void admin_creates_get_request_with_invalid_batch_id() throws FileNotFoundException {
		   
			request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("Id", 167);
		}
		
		@When("Admin sends GET Request  with invalid batchId by Program Batches endpoint")
		public void admin_sends_get_request_with_invalid_batch_id_by_program_batches_endpoint() {
			response = request
					.when().get(routes.getString("Get_UserbyProgramBatches"))
					.then().log().all().extract().response();
					//.spec(resSpecBuilder()).statusCode(404).log().all().extract().response();
			
			logger.info("===========Negative with invalid batchId =====================  ");
		}
		//(====================================Get invalid program Id===========================================  )
		
		@Given("Admin creates GET Request with invalid program Id")
		public void admin_creates_get_request_with_invalid_program_id() throws FileNotFoundException {
		    
			request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("programId", 6765);
		}

		@When("Admin sends GET Request  with invalid program Id by Program  endpoint")
		public void admin_sends_get_request_with_invalid_program_id_by_program_endpoint() {
			
			response = request
					.when().get(routes.getString("Get_UsersforProgram"))
					.then().log().all().extract().response();
					//.spec(resSpecBuilder()).statusCode(404).log().all().extract().response();
			
			logger.info("===========Negative with invalid ProgramId =====================  ");
		}

		//(====================================Get invalid role ID===========================================  )
		
		@Given("Admin creates GET Request for GET with invalid role ID")
		public void admin_creates_get_request_for_get_with_invalid_role_id() throws FileNotFoundException {
		     
			request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("roleId","R05");
		}

		@When("Admin sends GET HTTPS Request  endpoint with invalid role ID")
		public void admin_sends_get_https_request_endpoint_with_invalid_role_id() {
			response = request
					.when().get(routes.getString("Get_UsersbyroleId"))
					.then().log().all().extract().response();
					//.spec(resSpecBuilder()).statusCode(404).log().all().extract().response();
			
			logger.info("===========Negative with invalid role ID endpoint =====================  ");
		}
		// -------@Get_Admin_with_filters_401-----------

		@Given("Admin creates GET Request with filters with no authorization")
		public void admin_creates_get_request_with_filters_with_no_authorization() throws FileNotFoundException {
			request = given().spec(requestSpecification());
			logger.info("====Negative Admin_with_filters_401 for role v2=======");
		}

		@When("Admin sends HTTPS Request with filters with no authorization with endpoint")
		public void admin_sends_https_request_with_filters_with_no_authorization_with_endpoint() {
			response = request.when().get(routes.getString("Get_UsersbyroleIdV2")).then().log().all().extract().response();

		}

		@Then("Admin receives status {string} with Unauthorized message")
		public void admin_receives_status_with_unauthorized_message(String statuscode) {
			assertEquals(statuscode, response.getStatusCode());
			logger.info("====401 unAuthorized=======");
		}

		// -----@Get_Admin_with_filters_404------

		@Given("Admin creates GET Request with filters with invalid endpoint")
		public void admin_creates_get_request_with_filters_with_invalid_endpoint() throws FileNotFoundException {
			request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.token);
			logger.info("Request for Get All users by RoleId v2");
		}

		@When("Admin sends HTTPS Request with filters with invalid endpoint")
		public void admin_sends_https_request_with_filters_with_invalid_endpoint() {
			response = request.when().get(routes.getString("invalidUrl"))
					.then().log().all().extract().response();
		}
		@Then("Admin receives status {string} with Not Found error message")
		public void admin_receives_status_with_not_found_error_message(String statuscode) {
			assertEquals(statuscode, response.getStatusCode());
			logger.info("====404 Not Found=======");
		}
		//Delete Usermap----------------------------------------------------------------------------------
		@Given("Admin creates DELETE Request to delete Admin assigned to program\\/batch by invalid AdminId")
		public void admin_creates_delete_request_to_delete_admin_assigned_to_program_batch_by_invalid_admin_id() throws FileNotFoundException {
			request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("userId",IdHolder.invalidId);
		}

		@When("Admin sends HTTPS Request")
		public void admin_sends_https_request() {
			response = request.when()
					.delete(routes.getString("delete_AllPrograms/BatchesByUserId"))
					.then().log().all().extract().response();
		}

		@Then("Admin receives {int}")
		public void admin_receives(Integer int1) {
			assertEquals(404,response.getStatusCode());
			assertEquals(response.header("Content-Type"),"application/json");
		}

		

		@Given("Admin creates DELETE Request to delete Admin assigned to program\\/batch by valid AdminId")
		public void admin_creates_delete_request_to_delete_admin_assigned_to_program_batch_by_valid_admin_id() throws FileNotFoundException {
			request=given().spec(requestSpecification())
						.pathParam("userId",IdHolder.userId);

		}

		
}
