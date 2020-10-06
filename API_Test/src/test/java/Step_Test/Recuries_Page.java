package Step_Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Util.BasePage;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Recuries_Page extends BasePage {

	String Url = "";
	String urlAndEndPoint ="";// all headers
	String get_endPoint = "";

	@BeforeTest
	public void requestAssured() {
		Url = propertiesReader("Url");// url
		RestAssured.baseURI = Url;
		get_endPoint = propertiesReader("getEndPoint");
		urlAndEndPoint = Url + propertiesReader("getEndPoint");
	}

	@Test(priority = 1)
	public void getrequestBody() {
		// Url = propertiesReader("Url");
		// String getEndPoint="api/users?page=2";
//		String rs = RestAssured.get(Url).asString();
//		System.out.println("string value of url\n" + rs);

		RestAssured.given().get(get_endPoint).prettyPrint();
		String name = propertiesReader("first_name");
		Assert.assertTrue(RestAssured.given().get(get_endPoint).asString().contains(name));
		System.out.println("Name assertion passed");
	}

	@Test(priority = 2)
	public void get_statuscode() {

		int expectedcode = 200;
		int statuscode = RestAssured.given().get(get_endPoint).getStatusCode();
		System.out.println("body response\n" + statuscode);
		Assert.assertEquals(statuscode, expectedcode);
		System.out.println("passed again");
		// .when() .get() .then() .assertThat() .log() .all() .statusCode(200);
	}

	@Test(priority = 3)
	public void get_ContenetType() {
		String Actual_content = RestAssured.given().get(get_endPoint).contentType();
		String Expected_content = "application/json; charset=utf-8";
		System.out.println("get content\n" + Actual_content);
		Assert.assertEquals(Actual_content, Expected_content);
		System.out.println(header_date(Url, "Date"));
		System.out.println("content passed");

	}

	// @Test(priority = 4)
	public void Header_varification() {

		String actual_date = header_date(urlAndEndPoint, "Date").substring(0, 16);// we are using string because we
																					// remove
		// timestamp , we need to change date in property file
//Assert.assertEquals(actual_date, expected);

		Assert.assertEquals(actual_date, propertiesReader("Date"), "date didnt match");
		System.out.println("date assertion passed");

		Assert.assertEquals(header_date(urlAndEndPoint, "Content-Type"), propertiesReader("Content-Type"),
				"content didnt match");
		System.out.println("Content type passed");

		Assert.assertEquals(header_date(urlAndEndPoint, "Transfer-Encoding"), propertiesReader("Transfer-Encoding"),
				"transfer didnt match");
		System.out.println("Transfer Encoding passed");

		Assert.assertEquals(header_date(urlAndEndPoint, "Connection"), propertiesReader("Connection"),
				"Connection  didnt match");
		System.out.println("Connection passed");

		Assert.assertEquals(header_date(urlAndEndPoint, "X-Powered-By"), propertiesReader("X-Powered-By"),
				"XPowerd didnt match");
		System.out.println("X-Powered passed");

		Assert.assertEquals(header_date(urlAndEndPoint, "Access-Control-Allow-Origin"),
				propertiesReader("Access-Control-Allow-Origin"), "Access Control didnt match");
		System.out.println("Access-Control-Allow-Origin passed");

		Assert.assertEquals(header_date(urlAndEndPoint, "Etag"), propertiesReader("Etag"), " Etag didnt match");
		System.out.println("Etag passed");

		Assert.assertEquals(header_date(urlAndEndPoint, "Via"), propertiesReader("Via"), "Via  didnt match");
		System.out.println("Via passed");

		Assert.assertEquals(header_date(urlAndEndPoint, "Cache-Control"), propertiesReader("Cache-Control"),
				"Cache-Control  didnt match");
		System.out.println("Cache-Control passed");

		Assert.assertEquals(header_date(urlAndEndPoint, "CF-Cache-Status"), propertiesReader("CF-Cache-Status"),
				"CF-Cache-Status  didnt match");
		System.out.println("CF-Cache-Status passed");

//	 Assert.assertEquals(header_date(Url,"Age"),propertiesReader("Age")," Age didnt match" );
//	 System.out.println("Age passed");

//	 Assert.assertEquals(header_date(Url,"cf-request-id"),propertiesReader("cf-request-id"),"cf-request-id  didnt match" );
//	 System.out.println("cf-request-id passed");

		Assert.assertEquals(header_date(urlAndEndPoint, "Expect-CT"), propertiesReader("Expect-CT"),
				"Expect-CT  didnt match");
		System.out.println("Expect-CT passed");

		Assert.assertEquals(header_date(urlAndEndPoint, "Vary"), propertiesReader("Vary"), "Vary  didnt match");
		System.out.println("Vary");

		Assert.assertEquals(header_date(urlAndEndPoint, "Server"), propertiesReader("Server"), "Server  didnt match");
		System.out.println("Server passed");

//	 Assert.assertEquals(header_date(Url,"CF-RAY"),propertiesReader("CF-RAY"),"CF-RAY  didnt match" );
//	 System.out.println("CF-RAY passed");// for  dynamic we cant test//
//	 
		Assert.assertEquals(header_date(urlAndEndPoint, "Content-Encoding"), propertiesReader("Content-Encoding"),
				"Content-Encoding  didnt match");
		System.out.println("Content-Encoding passed");

	}

	@Test(priority = 5)
	public void body_Value() {

		 RestAssured.given().param("id", "1").when().get(get_endPoint).prettyPrint();	
		 
		 // for param we have to write param first
		//System.out.println(response.prettyPrint());// print in jason formatt
		// Response response = RestAssured.given().

		// params("id", "1").
		// when().
		// get();
		// System.out.println(response.asString());
		// System.out.println(response.prettyPrint()); // print in json format as it is.
		// if we want verify cookie()method;it will ask for string//
	}

	@Test(priority = 6)
	public void testing_cooking() {
		Cookies cookie = RestAssured.given().get(get_endPoint).getDetailedCookies();
		for (Cookie cookie1 : cookie) {
			System.out.println(cookie1);
			// Assert.assertEquals(cookie, cookie1);
		}
	}

	@Test(priority = 7)
	public void getBodywithId() {
		JsonPath jasonpath = new JsonPath(RestAssured.given().get(get_endPoint).asString());
		String customerName = jasonpath.get("data[1].first_name").toString();
		// we got the path from formatt
		System.out.println(customerName);

	}

	@Test(priority = 8)
	public void getbody_all() {
		String responseResult = RestAssured.given().get(get_endPoint).then().extract().asString();
		JsonPath jasonpath = new JsonPath(responseResult);
		List<String> customername = jasonpath.getList("data.year");// $..book[?(@.price<10)]
		for (String string : customername) {
			System.out.println(string);// $..book[?(@.author=10)] data[1].year["Indo-European"]["Indo-Iranian"]
		
		}
	}

	public void getItemNameForPriceLessThanSix() {
		String responseResult = RestAssured.given().get(get_endPoint).then().extract().asString();
		JsonPath jsonPath = new JsonPath(responseResult);
		for (int i = 0; i < 50; i++) {
			String strItemPrice = jsonPath.get("records[" + i + "].price");
			int itemPrice = Integer.parseInt(strItemPrice);
			if (itemPrice <= 6) {
				String itemName = jsonPath.get("records[" + i + "].name");
				String item_Price = jsonPath.get("records[" + i + "].price");
				System.out.printf("Item name:%s \t\t Item Price:%s\n", itemName, item_Price);
			}
		}
	}

	@Test(priority = 9)
	public void validationJasonSchema() {// need jason schema validator
		RestAssured.given().get(get_endPoint).then().assertThat()
				.body(matchesJsonSchemaInClasspath("jasonSchema.jason"));// file name
	}

}
