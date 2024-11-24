package REST;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pojo.Api;
import Pojo.WebAutomation;
import Pojo.getCourse;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.restassured.path.json.JsonPath;

public class OAuth {

	@Test
	public void Authorization() {
		String Response = given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials")
				.formParam("scope", "trust").when()
				.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").then().log().all()
				.assertThat().statusCode(200).extract().asString();

		JsonPath Js = ReUsableMethods.rawToJson(Response);
		String TokenId = Js.get("access_token");
		System.out.println("Token ID: " + TokenId);

		getCourse gc = given().queryParams("access_token", TokenId).when().log().all()
		.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(getCourse.class);
		System.out.println(gc.getLinkedIn());

		List<Api> apiCourses = gc.getCourses().getApi();
		for (int i = 0; i < apiCourses.size(); i++) {
			if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(apiCourses.get(i).getPrice());
			}
		}
		
		ArrayList<String> a= new ArrayList<String>();
		List<WebAutomation> webAutomationCourses = gc.getCourses().getWebAutomation();
		for (int j = 0; j < webAutomationCourses.size(); j++) {
			a.add(webAutomationCourses.get(j).getCourseTitle());
		}
		String[] courseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};
		List<String> expectedList= Arrays.asList(courseTitles);
		
		Assert.assertTrue(a.equals(expectedList));
	}
}
