package REST;

import org.testng.annotations.Test;

import Payloads.PayloadData;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {

	@Test
	public void addBook() {
		RestAssured.baseURI = "http://216.10.245.166";
		String Res = given().body(PayloadData.AddBook("Sfcc", "29380ad")).
		when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().toString();
		
		JsonPath js = ReUsableMethods.rawToJson(Res);
		String BookId = js.get("ID");
		System.out.println("Book ID: " + BookId);
	}
}
