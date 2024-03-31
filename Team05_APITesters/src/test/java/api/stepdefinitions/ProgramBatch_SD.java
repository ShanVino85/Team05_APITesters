package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONObject;

import api.resourses.CreateBatchData;
import api.utils.IdandNameHolder;
import api.utils.RestUtils;
import api.utils.TokenHolder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * ProgramBatch Step definition
 */
public class ProgramBatch_SD extends RestUtils {
	CreateBatchData cbd = new CreateBatchData();
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	JSONObject requestBody;

	// Set Background
	@Given("Admin sets Authorization to Bearer Token.")
	public void admin_sets_authorization_to_bearer_token() throws FileNotFoundException, IOException {
		UserLogin user = new UserLogin();
		user.add_user_login_payload();
		user.admin_calls_post_https_method_with_valid_endpoint();
	}

	// POST Method
	@Given("Admin creates POST Request  with valid data in request body")
	public void admin_creates_post_request_with_valid_data_in_request_body() throws FileNotFoundException, IOException {
		// Build the request body including the programId attribute
		requestBody = new JSONObject(cbd.dataBuild());
		requestBody.put("programId", IdandNameHolder.programId);
		// Set the request body as a string and create the request
		request = given().spec(requestSpecification()).body(requestBody.toString());
	}

	@When("Admin sends POST HTTPS Request with endpoint of program batch")
	public void admin_sends_https_request_with_endpoint() {
		response = request.header("Authorization", "Bearer " + TokenHolder.token).when()
				.post(routes.getString("Post_CreateBatch")).then().log().all().extract().response();
		int i = Integer.parseInt(UserKeyJson(response, "batchId"));
		TokenHolder.batchId = i;
		TokenHolder.batchName = UserKeyJson(response, "batchName");
	}

	@Then("Admin receives {int} Created Status with response body of Post Batch request")
	public void admin_receives_created_status_with_response_body_of_post_batch_request(int actualStatusCode) {
		assertEquals(response.getStatusCode(), actualStatusCode);		
	}
	
	@Then("Validate the Schema after post batch request")
	public void validate_the_schema_after_post_batch_request() {
		// schema Validation
	   response.then().assertThat().body(matchesJsonSchemaInClasspath("Schemas/ProgramBatchModule/createBatch.json"));
	}

	@Then("Validate the header after post batch request")
	public void validate_the_header_after_post_batch_request() {
		assert response.getHeader("Content-Type") != null && response.getHeader("Content-Type").contains("application/json");
	}


	// Get ALL BATCHES
	@Given("Admin creates GET Request of program batch of all batches")
	public void admin_creates_get_request_of_program_batch_of_all_batches() throws FileNotFoundException {
		request = given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with endpoint of program batch of all batches")
	public void admin_sends_https_request_with_endpoint_of_program_batch_of_all_batches() {
		response = request.header("Authorization", "Bearer " + TokenHolder.token).when()
				.get(routes.getString("Get_AllBatch")).then().log().all().extract().response();
	}

	@Then("Admin receives {int} OK Status with response body of program batch of all batches.")
	public void admin_receives_ok_status_with_response_body_of_program_batch_of_all_batches(int int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	// Get ALL BATCHES - search string
	@Given("Admin creates GET Request with search string of program batch")
	public void admin_creates_get_request_with_search_string_of_program_batch() throws FileNotFoundException {
		request = given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with endpoint of program batch of search string")
	public void admin_sends_https_request_with_endpoint_of_program_batch() {
		String batchName = TokenHolder.batchName;
		String endpoint = routes.getString("Get_AllBatch") + "?batchName=" + batchName;
		response = request.header("Authorization", "Bearer " + TokenHolder.token).when().get(endpoint).then().log()
				.all().extract().response();
	}

	@Then("Admin receives {int} Ok status with response body of program batch of search string")
	public void admin_receives_ok_status_with_response_body_of_program_batch_of_search_string(int actualStatusCode) {
		assertEquals(response.getStatusCode(), actualStatusCode);
	}

	// Get BATCH BY VALID Batch ID
	@Given("Admin creates GET Request with valid Batch ID")
	public void admin_creates_get_request_with_valid_batch_id() throws FileNotFoundException {
		request = given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with endpoint of program batch with batch id")
	public void get_batch_by_batchID() {
		String endpoint = routes.getString("Get_BatchByBatchId");
		response = request.header("Authorization", "Bearer " + TokenHolder.token)
				.pathParam("batchId", TokenHolder.batchId).get(endpoint).then().log().all().extract().response();
	}

	@Then("Admin receives {int} Ok status with response body of program batch of batch id")
	public void admin_receives_ok_status_with_response_body_of_program_batch_of_batch_id(int actualStatusCode) {
		assertEquals(response.getStatusCode(), actualStatusCode);
	}

	// Get BATCH BY VALID Batch ID after Deleting batch
	@Given("Admin creates GET Request with valid Batch ID after deleting batch")
	public void admin_creates_get_request_with_valid_batch_id_after_deleting_batch() throws FileNotFoundException {
		request = given().spec(requestSpecification());
		delete_batch_by_batchID();
		request = given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with endpoint of program batch with batch id after deleting batch")
	public void admin_sends_https_request_with_endpoint_of_program_batch_with_batch_id_after_deleting_batch() {
		get_batch_by_batchID();
	}

	@Then("Admin receives {int} OK Status with batchStatus field {string} in the response body.")
	public void admin_receives_ok_status_with_response_body_of_program_batch_of_batch_id_after_deleting_batch(
			int actualStatusCode, String str) {
		assertEquals(response.getStatusCode(), actualStatusCode);
	}

	// Get BATCH BY VALID Batch Name
	@Given("Admin creates GET Request with valid batch name")
	public void admin_creates_get_request_with_valid_batch_name() throws FileNotFoundException {
		request = given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with endpoint with batch name")
	public void admin_sends_https_request_with_endpoint_with_batch_name() {
		String endpoint = routes.getString("Get_BatchByBatchName");
		response = request.header("Authorization", "Bearer " + TokenHolder.token)
				.pathParam("batchName", TokenHolder.batchName).get(endpoint).then().log().all().extract().response();
	}

	@Then("Admin receives {int} OK Status with response body of batch name.")
	public void admin_receives_ok_status_with_response_body_of_batch_name(int actualStatusCode) {
		assertEquals(response.getStatusCode(), actualStatusCode);
	}

	// Get BATCH BY VALID Batch Name after Deleting batch
	@Given("Admin creates GET Request with batch Name after deleting batch")
	public void admin_creates_get_request_with_batch_name_after_deleting_batch() throws FileNotFoundException {
		request = given().spec(requestSpecification());
		delete_batch_by_batchID();
		request = given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with endpoint of program batch with batch name after deleting batch")
	public void admin_sends_https_request_with_endpoint_of_program_batch_with_batch_name_after_deleting_batch() {
		get_batch_by_batchID();
	}

	@Then("Admin receives {int} OK Status with  batchStatus field {string} in the response body using batchName.")
	public void admin_receives_ok_status_with_batch_status_field_in_the_response_body_using_batch_name(
			int actualStatusCode, String string) {
		assertEquals(response.getStatusCode(), actualStatusCode);
	}

	// Get BATCH BY VALID Program Id
	@Given("Admin creates GET Request with valid Program Id")
	public void admin_creates_get_request_with_valid_program_id() throws FileNotFoundException {
		request = given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with endpoint with valid program id")
	public void admin_sends_https_request_with_endpoint_with_valid_program_id() {
		String endpoint = routes.getString("Get_BatchByProgramId");
		response = request.header("Authorization", "Bearer " + TokenHolder.token)
				.pathParam("programId", IdandNameHolder.programId).get(endpoint).then().log().all().extract()
				.response();
	}

	@Then("Admin receives {int} OK Status with response body with valid program id.")
	public void admin_receives_ok_status_with_response_body_with_valid_program_id(int actualStatusCode) {
		assertEquals(response.getStatusCode(), actualStatusCode);
	}

	// PUT Request BY VALID Batch Id and Data
	@Given("Admin creates PUT Request with valid BatchId and Data")
	public void admin_creates_put_request_with_valid_batch_id_and_data() throws FileNotFoundException, IOException {
		// Build the request body including the programId attribute
		requestBody = new JSONObject(cbd.put_dataBuild());
		requestBody.put("programId", IdandNameHolder.programId);
		// Set the request body as a string and create the request
		request = given().spec(requestSpecification()).body(requestBody.toString());
	}

	@When("Admin sends HTTPS Request with endpoint with PUT in request body")
	public void admin_sends_https_request_with_endpoint_with_put_in_request_body() {
		response = request.header("Authorization", "Bearer " + TokenHolder.token)
				.pathParam("batchId", TokenHolder.batchId).when().put(routes.getString("Put_Batch")).then().log().all()
				.extract().response();
		int i = Integer.parseInt(UserKeyJson(response, "batchId"));
		TokenHolder.batchId = i;
		TokenHolder.batchName = UserKeyJson(response, "batchName");
	}

	@Then("Admin receives {int} OK Status with updated value in response body with PUT.")
	public void admin_receives_ok_status_with_updated_value_in_response_body_with_put(int actualStatusCode) {
		assertEquals(response.getStatusCode(), actualStatusCode);
	}

	// DELETE Batch by valid BatchId
	@Given("Admin creates DELETE Request with valid BatchId")
	public void admin_creates_delete_request_with_valid_batch_id() throws FileNotFoundException {
		request = given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request  with endpoint for delete batch")
	public void delete_batch_by_batchID() {
		String endpoint = routes.getString("Delete_Batch");
		response = request.header("Authorization", "Bearer " + TokenHolder.token).pathParam("id", TokenHolder.batchId)
				.delete(endpoint).then().log().all().extract().response();
	}

	@Then("Admin receives {int} Ok status with message for delete batch.")
	public void admin_receives_ok_status_with_message_for_delete_batch(int actualStatusCode) {
		assertEquals(response.getStatusCode(), actualStatusCode);
	}
}
