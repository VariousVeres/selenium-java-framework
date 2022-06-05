package other.obj_to_json;

import com.google.gson.annotations.SerializedName;

public class LoginPOJO {
    @SerializedName("grant_type")
    private String grantType=null;
    private String password=null;
    private String username=null;
    @SerializedName("client_id")
    private String clientId=null;

    public void setGrantType(String grant_type) {
        this.grantType = grant_type;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setClientId(String client_id) {
        this.clientId = client_id;
    }
}
