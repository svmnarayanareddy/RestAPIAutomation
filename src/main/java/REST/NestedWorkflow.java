package REST;

import org.testng.Assert;
import org.testng.annotations.Test;

import Payloads.PayloadData;
import io.restassured.path.json.JsonPath;

public class NestedWorkflow {
	@Test
	public void SumofCources() {
		
		JsonPath Js = new JsonPath(PayloadData.NestedPayload());
		int ActualPurchasedAmount = 0;
		// Retrieving Dynamic Payload data via condition
		int Count = Js.getInt("courses.size()");
		for (int i = 0; i < Count; i++) {
			int CoursePrice = Js.getInt("courses[" + i + "].price");
			int CourseCopies = Js.getInt("courses[" + i + "].copies");
			int TotalAmount = CoursePrice * CourseCopies;
			ActualPurchasedAmount = TotalAmount + ActualPurchasedAmount;			
		}
		System.out.println("Total Amount: "+ ActualPurchasedAmount);
		int ExpectedPurchasedAmount = Js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(ActualPurchasedAmount, ExpectedPurchasedAmount);
		
	}
}
