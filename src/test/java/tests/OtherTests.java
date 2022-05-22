package tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;
import org.testng.annotations.Test;
import utils.Helper;

import static utils.Logging.LOGGER;


public class OtherTests {
    @Test
    public void jsonParse() {
        String str = "{\"status_msg\":\"Success\",\"status_code\":\"0\",\"ticketlist\":[{\"term_no\":\"3001\",\"sale_no\":\"1\",\"start_validity\":\"20210825113202\",\"end_validity\":\"20210825133202\",\"trx_date\":\"20210825113158\",\"activation_time\":\"20210825113202\",\"deactivation_time\":\"20210825133202\",\"mas_activation_id\":\"1000000756\",\"ticket_version\":\"1000036209\"}],\"activation_details\":{\"activation_colors\":[{\"mas_activation_id\":\"1000000756\",\"calendar_id\":\"82683\"}],\"barcodes\":[{\"barcode_id\":\"1000000756\",\"barcode\":\"1102030001DzRXF0rGSQMnra4ZhXVTZpXeH1RtaasTBxmOuXNQV5OOs6D54FsLXUwoy4AweNOBdmdTi9iGQdbnsFmPIeiZDO4nWp9mLMRZ9RUpoL9SuycEYxSwzQBUAtAjBnIXoGjxMLCA+eN75JiyQ0uw0VcWs7IlvUTK2GCcvWRaIPxlN7o=\"}],\"color_calendars\":[{\"calendar_id\":\"82683\",\"color_band\":\"a75f0\",\"color_band_id\":\"1\",\"color_code\":\"#0066FF\",\"color_code_id\":\"4\",\"start\":\"20210825000000\",\"end\":\"20210825235959\"}]}}";
        JsonStreamParser jsonParser = new JsonStreamParser(str);
        JsonObject payload = jsonParser.next().getAsJsonObject();
        JsonElement result = payload.get("ticketlist").getAsJsonArray().get(0).getAsJsonObject().get("mas_activation_id");
        System.out.println(result);
    }

    @Test
    public void testingSystemPropertiesFromMavenSurefire()  {
        LOGGER.info("URL from pom: "+ System.getProperty("url"));
        LOGGER.info("URL from pom: "+ System.getProperty("host.url"));
        LOGGER.info("URL from properties: "+ Helper.getProperty("host.url"));
        LOGGER.info("URL from properties: "+ System.getProperty("propertiesFile"));
    }
}
