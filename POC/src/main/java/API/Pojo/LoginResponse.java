package API.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class LoginResponse {
    private int statusCode;
    private String message;
    @JsonIgnoreProperties(ignoreUnknown = true)

    private Data data;
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String error;
    @JsonIgnoreProperties(ignoreUnknown = true)

    private String path;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    public static class Data {
        private String access_token;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }
    }
}

