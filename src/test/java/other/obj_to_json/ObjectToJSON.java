package other.obj_to_json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ObjectToJSON {
    @Test
    public void main() {
        LoginPOJO login = new LoginPOJO();
        login.setPassword("");
        login.setGrantType("password");
        login.setUsername("oleksandr.veresiuk@eleks.com");
        login.setClientId("viovendi_web");

        String loginJSON = Serializator.objectToJSON(login);
        System.out.println(loginJSON);

        Response response = given().header("Content-type", "application/json").when().body(loginJSON)
                .post("https://api.doo.net/v1/oauth").then().statusCode(HttpStatus.SC_CREATED).extract().response();

        LoginResponsePOJO loginResponsePOJO = (LoginResponsePOJO) Serializator.JSONToObject(response, LoginResponsePOJO.class);
        System.out.println(loginResponsePOJO.data.access_token);
    }
}

class Serializator {
    static Gson gson = new GsonBuilder().serializeNulls().create();
    static String objectToJSON(Object object)  {
        return gson.toJson(object);
    }

    static Object JSONToObject(Response response, Class<?> cls)  {
        return gson.fromJson(response.asString(), cls);
    }
}
