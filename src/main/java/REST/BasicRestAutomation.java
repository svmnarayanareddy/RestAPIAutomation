package REST;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Payloads.AddPlace;

public class BasicRestAutomation {
	public static void main(String args[]) {
		//Given based on Input Details
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String Response = given().log().all().queryParam("key", "qaclick123").headers("Content-Type", "application/json")
		.body(AddPlace.AddNewPlace()).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("status", equalTo("OK")).extract().asString();
		System.out.println(Response);
		JsonPath js = new JsonPath(Response);
		String PlaceId = js.getString("place_id");
		System.out.println(PlaceId);
		
		//PUT Method
		given().log().all().queryParam("key", "qaclick123").headers("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+PlaceId+"\",\r\n"
				+ "\"address\":\"70 Summer walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Method
		String AdressData = given().queryParam("key", "qaclick123").queryParam("place_id", PlaceId).when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath Data = ReUsableMethods.rawToJson(AdressData);
		String GetAdress = ((JsonPath) Data).getString("address");
		System.out.println("getData: "+ GetAdress);
		Assert.assertEquals(GetAdress, "70 Summer walk, USA");
	}
}
