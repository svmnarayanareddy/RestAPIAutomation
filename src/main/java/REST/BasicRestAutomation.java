package REST;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.testng.Assert;

public class BasicRestAutomation {
	public static void main(String args[]) throws IOException {
		//Given based on Input Details
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		//Reading Payload from Json file with Files class and converting to string as output
		String Response = given().log().all().queryParam("key", "qaclick123").headers("Content-Type", "application/json")
		.body(new String (Files.readAllBytes(Path.of("C:\\Users\\Manohar Reddy\\Downloads\\addPlace.json")))).when().post("maps/api/place/add/json")
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