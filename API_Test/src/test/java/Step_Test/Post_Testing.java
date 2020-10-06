package Step_Test;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Util.BasePage;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Post_Testing extends BasePage {

	
		String Url = "";
		String urlAndEndPoint = "";
		String post_endPoint = "";

		@BeforeTest
		public void requestAssured() {
			Url = propertiesReader("Url");
			RestAssured.baseURI = Url;
			post_endPoint = propertiesReader("postEndPoint");
			urlAndEndPoint = Url + propertiesReader("postEndPoint");
		}

		@Test (priority = 1)
		public void createUser() {
			
			RequestSpecification requestSpecification = RestAssured.given();//we are passig value we need request

			Map<String, String> requestParams = new HashMap<String, String>();
			requestParams.put("name", "Arzjun");
			requestParams.put("job", "leader");
			
			
			Response response = requestSpecification.body(requestParams).post(post_endPoint);
			//Response response = requestSpecification.body(requestParams).put(post_endPoint);
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			//https://www.youtube.com/watch?v=i1tQ1pjEFWw

			response.getBody().prettyPrint();
			
//			String result = RestAssured.given().get("/api/users?page=2").jsonPath().get("data[0].first_name");
	//
//			System.out.println(result);
		}
		
	}

	
	
	
	
	
	
	
	

