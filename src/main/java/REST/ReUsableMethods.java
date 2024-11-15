package REST;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {

		// TODO Auto-generated method stub
		public static JsonPath rawToJson(String resposne) {
			JsonPath Js = new JsonPath(resposne);
			return Js;
		}

}
