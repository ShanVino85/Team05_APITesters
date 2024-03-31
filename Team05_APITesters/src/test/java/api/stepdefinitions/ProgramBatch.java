package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONObject;

import api.resourses.CreateBatchData;
import api.utils.IdHolder;
import api.utils.RestUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ProgramBatch extends RestUtils {
	
	CreateBatchData cbd = new CreateBatchData();
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	JSONObject requestBody;
	
	@Given("Admin creates POST Request  with valid data in request body")
	public void admin_creates_post_request_with_valid_data_in_request_body() throws FileNotFoundException, IOException {
		// Build the request body including the programId attribute
		requestBody = new JSONObject(cbd.dataBuild());
        requestBody.put("programId", IdHolder.programId);
        // Set the request body as a string and create the request
        request = given().spec(requestSpecification()).body(requestBody.toString());
	}

	@When("Admin sends POST HTTPS Request with endpoint of program batch")
	public void admin_sends_https_request_with_endpoint() {
	    response = request.header("Authorization", "Bearer " + IdHolder.token).when()
				.post(routes.getString("Post_CreateBatch")).then()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/ProgramBatchModule/CreateBatch.json"))
				.log().all().extract().response();
		int i = Integer.parseInt(UserKeyJson(response, "batchId"));
		IdHolder.batchId = i;
		IdHolder.batchName = UserKeyJson(response, "batchName");
	}
	
	@Then("Admin receives {int} Created Status with response body of Post Batch request")
	public void admin_receives_created_status_with_response_body_of_post_batch_request(Integer int1) {
		assertEquals(response.getStatusCode(), 201);
	}
	
	@Given("Admin creates GET Request of program batch of all batches")
	public void admin_creates_get_request_of_program_batch_of_all_batches() throws FileNotFoundException {
	    request = given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with endpoint of program batch of all batches")
	public void admin_sends_https_request_with_endpoint_of_program_batch_of_all_batches() {
		response = request.header("Authorization", "Bearer " + IdHolder.token).when()
				.get(routes.getString("Get_AllBatch")).then().log().all().extract().response();
	}

	@Then("Admin receives {int} OK Status with response body of program batch of all batches.")
	public void admin_receives_ok_status_with_response_body_of_program_batch_of_all_batches(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

}
