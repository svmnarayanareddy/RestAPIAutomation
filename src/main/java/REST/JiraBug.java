package REST;

import org.testng.annotations.Test;

import Payloads.PayloadData;

import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class JiraBug {

	@Test
	public static void CreateBug() {

		RestAssured.baseURI = "https://svmnarayanareddy-1731850710686.atlassian.net/";
		String BugResponse = given().header("content-type","application/json").header("Authorization", "Basic c3ZtbmFyYXlhbmFyZWRkeUBnbWFpbC5jb206QVRBVFQzeEZmR0YwRTJ4YjBpQTZFeTZRMl9may05ak1yaFZlUzdlek83blFscG9ESENaWDJueE1wVlJtT2swQ29WZ1VkVlBaZVdhZ3hwbzJXRWhCcjZ4akdMcXl1MXVtdTM2bmxtT0N6TUlXdjZ2YVM0UmNMWFN5YjAwYWpqUnFJbzlUcTlmT0NQM0FNWmJYemltZHJvOE56MTE3bXN1bVBwLVJtdHF1OFUwNk1aOExlV21NWDFJPTI2RTMzRTI5").
		body(PayloadData.CreateBug()).when().post("rest/api/3/issue").then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(BugResponse);
		String BugId = js.get("id");
		System.out.println(BugId);
		
		given().pathParam("key", BugId).header("X-Atlassian-Token","no-check").header("Authorization","Basic c3ZtbmFyYXlhbmFyZWRkeUBnbWFpbC5jb206QVRBVFQzeEZmR0YwRTJ4YjBpQTZFeTZRMl9may05ak1yaFZlUzdlek83blFscG9ESENaWDJueE1wVlJtT2swQ29WZ1VkVlBaZVdhZ3hwbzJXRWhCcjZ4akdMcXl1MXVtdTM2bmxtT0N6TUlXdjZ2YVM0UmNMWFN5YjAwYWpqUnFJbzlUcTlmT0NQM0FNWmJYemltZHJvOE56MTE3bXN1bVBwLVJtdHF1OFUwNk1aOExlV21NWDFJPTI2RTMzRTI5")
		.multiPart("file",new File("\"C:\\Users\\Manohar Reddy\\Downloads\\Sri Krishna Wallpapers Hd Wallpaper For Pc.jpg\"")).log().all()
		.post("rest/api/3/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
	}
}
