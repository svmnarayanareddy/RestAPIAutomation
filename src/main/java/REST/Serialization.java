package REST;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import Pojo.AddPlace;
import Pojo.Locations;


public class Serialization {

	@Test
	public void serializeTest() {
	
	AddPlace Ap = new AddPlace();
	Ap.setName("SVSLS Travells");
	Ap.setAccuracy(50);
	Ap.setLanguage("English-IN");
	Ap.setWebsite("http://svlstransports.com");
	Ap.setAddress("N.S.P Colony chimakurthy");
	
	List<String> myList = new ArrayList<String>();
	myList.add("Transpotaion");
	myList.add("Materials");
	Ap.setTypes(myList);
	
	Locations l = new Locations();
	l.setLatitude(-38.383494);
	l.setLongitude(33.427362);
	Ap.setLocation(l);
	
	 RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
			 .setContentType(ContentType.JSON).build();
	 ResponseSpecification resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	 RequestSpecification res = given().spec(req)
	 .body(Ap);
	 
	 Response response = res.when().post("/maps/api/place/add/json").
			 then().spec(resspec).extract().response();

	String responseString=response.asString();
	System.out.println(responseString);

	 
	/*RestAssured.baseURI = "https://rahulshettyacademy.com";
	//Reading Payload from Json file with Files class and converting to string as output
	String Response = given().log().all().queryParam("key", "qaclick123").headers("Content-Type", "application/json")
	.body(Ap).when().post("maps/api/place/add/json")
	.then().log().all().assertThat().statusCode(200).extract().asString();
	System.out.println(Response);*/
	
	}
}
