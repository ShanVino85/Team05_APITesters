package api.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ResourceBundle;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class RestUtils {
	
	public static RequestSpecification req;
	public static Response response;
	
	

	public static ResourceBundle routes = ResourceBundle.getBundle("Routes");
	
	public RequestSpecification requestSpecification() throws FileNotFoundException {
		
		if(req==null)
		{
		PrintStream log=new PrintStream (new FileOutputStream("logging.txt"));
		 req=new RequestSpecBuilder().setBaseUri(routes.getString("base_url")).addQueryParam("Key", "qaclick123")
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).build();
		 return req;
		}
		return req;
	}
public String getJsonPath(Response response,String Key) {
	
	String resp=response.asString();
    JsonPath js=new JsonPath(resp);
 
  return js.get(Key).toString();
}
	
}
