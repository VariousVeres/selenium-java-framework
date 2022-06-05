package other.obj_to_json;

public class LoginResponsePOJO {
    public int response_status;
    public String developer_message;
    public String user_message;
    public Data data;
    public Links _links;
}

class Data {
    public String access_token;
    public String refresh_token;
}

class Links {
    public Self self;
}

class Self {
    public String href;
}

