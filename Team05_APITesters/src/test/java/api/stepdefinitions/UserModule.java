package api.stepdefinitions;

import api.utils.IdHolder;
import api.utils.RestUtils;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import api.resourses.*;

public class UserModule extends RestUtils {

	Logger logger = LogManager.getLogger("UserModule.java");

	UserModuledata UserModuledata = new UserModuledata();
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	String asString;
	private static String user_Id;

	@Given("Admin creates POST request with all mandatory fields")
	public void admin_creates_post_request_with_all_mandatory_fields() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()).body(UserModuledata.usermodulePostdata()).header("Authorization",
				"Bearer " + IdHolder.bearerToken);
		
	}

	@When("Admin sends HTTPS Request with endpoint")
	public void admin_sends_https_request_with_endpoint() {
		logger.info("Request sent with valid Endpoint and request body");
		response = request.when().post(routes.getString("Post_Userurl"));
		asString = response.then().log().all().extract().asString();
		System.out.println(response);

		// extract userId value from response and saving it in RestUtils
		if (response.getStatusCode() == 201) {
			user_Id = UserKeyJson(response, "userId");
			IdHolder.userId = user_Id;
			System.out.println("**********userId saved*****: " + IdHolder.userId);
		}

	}

	@Then("Admin receives {int} Created Status with response body.")
	public void admin_receives_created_status_with_response_body(int statuscode) {
		assertEquals(statuscode,response.getStatusCode());
		logger.info("userId got created :" + IdHolder.userId);
		response.then().assertThat().body(matchesJsonSchemaInClasspath("Schema/UserModule/PostUserCreationSchema.json"));
		logger.info("User Schema Validation is successful");
	}
    
	// <--------Get all users by facets/ filter (v2 users)--------------->
		@Given("Admin creates GET Request with filters")
		public void admin_creates_get_request_with_filters() throws FileNotFoundException {
			request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.bearerToken);
			logger.info("Request for Get All users by RoleId v2");
		}

		@When("Admin sends HTTPS Request with filters endpoint")
		public void admin_sends_https_request_with_filters_endpoint() {
			response = request.when().get(routes.getString("Get_UsersbyroleIdV2"));
			asString = response.then().log().all().extract().asString();
			System.out.println(response);
		}

		@Then("Admin receives {string} OK")
		public void admin_receives_ok(String statuscode) {
			int GetAllstatuscode = response.getStatusCode();
			logger.info("Response Status is= " + GetAllstatuscode);
			if (GetAllstatuscode == 200) {
				response.then().statusCode(Integer.parseInt(statuscode));
				response.then().assertThat().header("Connection", "keep-alive");
				logger.info("User request is successful");

			} else if (GetAllstatuscode == 400) {
				logger.info("User Request unsuccessful");
			}
		}

		// <------update user by AdminID with mandatory fields--------->
		@Given("Admin creates PUT Request with valid data in request body \\(values only in mandatory fields)")
		public void admin_creates_put_request_with_valid_data_in_request_body_values_only_in_mandatory_fields()
				throws IOException {

			logger.info("---update user by Admin Id---");
			user_Id = IdHolder.userId;
			request = given().spec(requestSpecification())
					// .pathParam("userId",user_Id)
					.body(UserModuledata.userPUTdata()).header("Authorization", "Bearer " + IdHolder.bearerToken);

		}

		@When("Admin sends HTTPS request for update by userId with endpoiint")
		public void admin_sends_https_request_for_update_by_user_id_with_endpoiint() {
			response = request.when().put(routes.getString("Get_Alluser") + "/" + IdHolder.userId);
			asString = response.then().log().all().extract().asString();
			System.out.println(response);
			logger.info("updated userVisaStatus :" + UserKeyJson(response, "userVisaStatus"));
		}

		// <---------update user by Rolestatus------>

		@Given("Admin creates PUT Request with valid user data in request body")
		public void admin_creates_put_request_with_valid_user_data_in_request_body() throws FileNotFoundException, IOException {
			logger.info("---update user by Rolestatus---");
			user_Id = IdHolder.userId;
			request = given().spec(requestSpecification())
					.body(UserModuledata.userPUTRoleStatusdata()).header("Authorization", "Bearer " + IdHolder.bearerToken);

		}

		@When("Admin sends HTTPS Request for update by roleStatus with endpoint")
		public void admin_sends_https_request_for_update_by_role_status_with_endpoint() {
			
			response = request.when().put(routes.getString("Post_Userurl") + "/" + IdHolder.userId);
			asString = response.then().log().all().extract().asString();
			System.out.println(response);
			logger.info("updated userVisaStatus :" + UserKeyJson(response, "userVisaStatus"));
			logger.info("---Updated user by Rolestatus----");
		}

	
}
