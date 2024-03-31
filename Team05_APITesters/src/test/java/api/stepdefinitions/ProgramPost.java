package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import java.io.FileNotFoundException;
import java.io.IOException;
import api.pojo.CreateProgramPojo;
import api.resourses.CreateProgramData;
import api.utils.IdHolder;
import api.utils.RestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ProgramPost extends RestUtils{

	CreateProgramData cpd = new CreateProgramData();
    CreateProgramPojo cp = new CreateProgramPojo();
    RequestSpecification request;
    ResponseSpecification responseSpec;
    Response response;
    @Given("Admin creates POST Request for the LMS with request body")
    public void admin_creates_post_request_for_the_lms_with_request_body() throws FileNotFoundException, IOException
    {
            request=given().spec(requestSpecification()).body(cpd.dataBuild());
    }
    @When("Admin sends HTTPS Request and  request Body with endpoint")
    public void admin_sends_https_request_and_request_body_with_endpoint() throws IOException
    {
            response = request.header("Authorization", "Bearer " +IdHolder.bearerToken)
                            .when().post(routes.getString("Post_Createprogram")).then().log().all().extract().response();
            
            int i = Integer.parseInt(UserKeyJson(response,"programId"));
            System.out.println(i);
            IdHolder.programId = i;
            IdHolder.programName = UserKeyJson(response,"programName");
    }
    @Then("Admin receives {int} Created Status with response body")
    public void admin_receives_created_status_with_response_body(Integer int1)
    {
            assertEquals(response.getStatusCode(),201);
            
            response
            .then()
        .assertThat().body(matchesJsonSchemaInClasspath("Schema/ProgramModule/Createprogram.json"));
            
    }
}
