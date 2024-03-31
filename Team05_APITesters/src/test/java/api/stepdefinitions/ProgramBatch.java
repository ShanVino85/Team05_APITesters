package api.stepdefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.JSONObject;

import api.resourses.CreateBatchData;
import api.utils.IdHolder;
import api.utils.RestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ProgramBatch extends RestUtils{

	CreateBatchData cbd = new CreateBatchData();
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	JSONObject requestBody;
	
	@Given("Admin creates POST Request for batch with valid data in request body")
	public void admin_creates_post_request_for_batch_with_valid_data_in_request_body() throws IOException {
		// Build the request body including the programId attribute
				requestBody = new JSONObject(cbd.dataBuild());
		        requestBody.put("programId", IdHolder.programId);
		        // Set the request body as a string and create the request
		        request=given().spec(requestSpecification()).body(requestBody.toString());
	}

	@When("Admin sends POST HTTPS Request for batch with endpoint of program batch")
	public void admin_sends_post_https_request_for_batch_with_endpoint_of_program_batch() {
		response = request.header("Authorization", "Bearer " + IdHolder.bearerToken).when()
				.post(routes.getString("Post_CreateBatch")).then().log().all().extract().response();
		int i = Integer.parseInt(UserKeyJson(response, "batchId"));
		IdHolder.batchId = i;
		IdHolder.batchName = UserKeyJson(response, "batchName");
	}

	
}
