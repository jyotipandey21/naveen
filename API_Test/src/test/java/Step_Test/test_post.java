package Step_Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class test_post {

	
	public void getrequestautomation() {
		RestAssured.baseURI="https://reqres.in/api/users";
		//RestAssured.given().param("page", "2")
		  Response response = RestAssured.given().param("page","2").param("id", "12").when().get();
		 System.out.println(response.getBody().asString());
			
			
			
			  System.out.println("getStatuscode=====\n"+response.getStatusCode());
			  System.out.println("contentType=====\n"+ response.contentType());
			  System.out.println("Time=====\n"+response.getTime());
			  System.out.println("Cookies=======\n"+response.getCookies());
			  System.out.println(response.prettyPrint());
			  
			 
		  	    
		  
			/*
			 * .when() .get() .then() .assertThat() .log() .all() .statusCode(200);
			 */
	}
}


	
	
	
	
	
	
	
	

