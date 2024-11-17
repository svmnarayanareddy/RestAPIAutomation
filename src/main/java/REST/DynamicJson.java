package REST;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Payloads.PayloadData;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DynamicJson {

	@Test(dataProvider="BooksData")
	public void addBook(String isbl, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		Response Res = given().body(PayloadData.AddBook(isbl, aisle)).
		when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response();
		
		/*
		  JsonPath js = ReUsableMethods.rawToJson(Res); 
		  String BookId = js.get("ID");
		  System.out.println("Book ID: " + BookId);
		 */
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData(){
		return new Object[][] {{"Ssr","909jje"}, {"Bnder","kj9898"}, {"Nsdc","90032nn"}};
	}
}
