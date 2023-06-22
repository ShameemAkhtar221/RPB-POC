package API.Pojo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class GetEmployeeResponse {

    private boolean status;
    private int statusCode;
    private Object message;
    private Data data;
    private String error;
    private String path;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
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

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public static class Data {
        private List<Employee> employees;
        private int totalPages;
        private int currentPage;

        public List<Employee> getEmployees() {
            return employees;
        }

        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }
    }
}
