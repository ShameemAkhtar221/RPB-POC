package API.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class PostEmployeeResponse {

    @JsonIgnoreProperties(ignoreUnknown = true)
    private Boolean status;
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Integer statusCode;
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Object message;
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Employee data;
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String error;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private String path;


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public void setData(Employee data) {
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public Object getMessage() {
        if (message instanceof String) {
            return (String) message;
        } else if (message instanceof List<?>) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.convertValue(message, new TypeReference<List<String>>() {});
        } else {
            return null;
        }
    }

    public Employee getData() {
        return data;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
