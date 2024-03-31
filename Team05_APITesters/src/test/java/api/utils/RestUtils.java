package api.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ResourceBundle;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class RestUtils {
	
	public static RequestSpecification req;
	public static Response response;
	public static String token;
	public static String BearerToken="0";
	

	public static ResourceBundle routes = ResourceBundle.getBundle("Routes");
	
	public RequestSpecification requestSpecification() throws FileNotFoundException {
		
		if(req==null)
		{
		PrintStream log=new PrintStream (new FileOutputStream("logging.txt"));
		 req=new RequestSpecBuilder().setBaseUri(routes.getString("base_url"))
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).build();
		 return req;
		}
		return req;
	}
	
	public ResponseSpecification resSpecBuilder() {
		ResponseSpecification resSpec = new ResponseSpecBuilder()
				.expectStatusCode(200).expectContentType(ContentType.JSON)
				.build().then().log().all();
		return resSpec;
	}
	public String UserKeyJson(Response response, String key) {
		String getResponse = response.asString();
		JsonPath js = new JsonPath(getResponse);
		String value = js.get(key);
		return value;
	}
	
}
