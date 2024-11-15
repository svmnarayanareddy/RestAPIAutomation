package REST;

import Payloads.PayloadData;
import io.restassured.path.json.JsonPath;

public class ComplexResponse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath Js = new JsonPath(PayloadData.NestedPayload());

		// Getting Size of courses from Payload
		int Size = Js.getInt("courses.size()");
		System.out.println("Courses Available :" + Size);

		// Getting Total Amount from node
		int TotalAmount = Js.getInt("dashboard.purchaseAmount");
		System.out.println("Courses Total Amount :" + TotalAmount);

		// Getting course name by index
		String TitleCourseFirst = Js.getString("courses[0].title");
		System.out.println("Courses Name:" + TitleCourseFirst);

		// Fetching all details in payload
		for (int i = 0; i < Size; i++) {
			String CourseName = Js.get("courses[" + i + "].title");
			System.out.println("Courses Name: " + CourseName);
			System.out.println("Course Price: " + Js.getInt("courses[" + i + "].price"));
		}
		// Retrieving Dynamic Payload data via condition
		for (int i = 0; i < Size; i++) {
			String CourseName = Js.get("courses[" + i + "].title");
			if (CourseName.equalsIgnoreCase("RPA")) {
				System.out.println("Course Copies: " + Js.getInt("courses[" + i + "].copies"));
			break;
			}
		}
	}

}
